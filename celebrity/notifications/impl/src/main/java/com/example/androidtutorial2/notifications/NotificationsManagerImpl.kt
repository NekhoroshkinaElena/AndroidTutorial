package com.example.androidtutorial2.notifications

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import com.example.androidtutorial2.model.NotificationData
import com.example.androidtutorial2.notifications.NotificationReceiver.Companion.NOTIFICATION_DATA_KEY
import javax.inject.Inject

internal class NotificationsManagerImpl @Inject constructor(
    private val alarmManager: AlarmManager,
    private val notificationManager: NotificationManager,
    private val context: Context
) : NotificationsManager {

    @SuppressLint("InlinedApi")
    override fun scheduleTopicRepeatNotifications(
        topicId: Int,
        topicName: String,
        message: String
    ) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            // Если разрешение на точные будильники не предоставлено, запрашиваем его
            if (!alarmManager.canScheduleExactAlarms()) {
                val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
                context.startActivity(intent)
                return
            }
        }

        val requestCode = topicId

        val notificationData = NotificationData(
            topicId = topicId,
            topicName = topicName,
            message = message,
            remainingTimes = 5 // начальное количество повторений
        )

        // Создаём Intent для вызова BroadcastReceiver (NotificationReceiver),
        // который будет обрабатывать уведомление
        val intent = Intent(context, NotificationReceiver::class.java).apply {
            putExtra(NOTIFICATION_DATA_KEY, notificationData)
        }

        //Используется для передачи Intent в AlarmManager и гарантирует, что один и тот же будильник
        // будет заменён, если он уже существует (FLAG_UPDATE_CURRENT).
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            requestCode,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val currentTime = System.currentTimeMillis()
        val intervalMillis: Long = 10 * 1000
        // Время первого срабатывания (через 10 секунд от текущего времени)
        val triggerTime = currentTime + intervalMillis

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    triggerTime,
                    pendingIntent
                )
            } else {
                alarmManager.setExact(
                    AlarmManager.RTC_WAKEUP,
                    triggerTime,
                    pendingIntent
                )
            }
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
    }

    override fun cancelNotifications(topicId: Int, topicName: String, message: String) {

        val notificationData = NotificationData(
            topicId = topicId,
            topicName = topicName,
            message = message,
            remainingTimes = 0
        )

        val intent = Intent(context, NotificationReceiver::class.java).apply {
            putExtra(NOTIFICATION_DATA_KEY, notificationData)
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            topicId,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        alarmManager.cancel(pendingIntent)

        // Отмена активного уведомления
        notificationManager.cancel(topicId)
    }
}
