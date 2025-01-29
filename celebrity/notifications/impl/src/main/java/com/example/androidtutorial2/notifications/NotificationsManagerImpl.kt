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
    private val context: Context,
) : NotificationsManager {

    @SuppressLint("InlinedApi")
    override fun scheduleTopicRepeatNotifications(
        topicId: Int,
        topicName: String,
        message: String
    ) {
        if (!checkAndRequestExactAlarmPermission()) return

        val notificationData = createNotificationData(topicId, topicName, message)
        val pendingIntent = createNotificationPendingIntent(topicId, notificationData)
        scheduleAlarm(pendingIntent, 10 * 1000)
    }

    private fun checkAndRequestExactAlarmPermission(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && !alarmManager.canScheduleExactAlarms()) {
            requestExactAlarmPermission()
            return false
        }
        return true
    }

    private fun requestExactAlarmPermission() {
        val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
    }

    private fun createNotificationData(topicId: Int, topicName: String, message: String) = NotificationData(
        topicId = topicId,
        topicName = topicName,
        message = message,
        remainingTimes = 5 // начальное количество повторений
    )

    private fun createNotificationPendingIntent(topicId: Int, notificationData: NotificationData): PendingIntent {
        val intent = Intent(context, NotificationReceiver::class.java).apply {
            putExtra(NOTIFICATION_DATA_KEY, notificationData)
        }
        return PendingIntent.getBroadcast(
            context,
            topicId,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    private fun scheduleAlarm(pendingIntent: PendingIntent, delayMillis: Long) {
        val triggerTime = System.currentTimeMillis() + delayMillis
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
        val pendingIntent = createNotificationPendingIntent(
            topicId,
            NotificationData(topicId, topicName, message, remainingTimes = 0)
        )

        alarmManager.cancel(pendingIntent)

        // Отмена активного уведомления
        notificationManager.cancel(topicId)
    }
}
