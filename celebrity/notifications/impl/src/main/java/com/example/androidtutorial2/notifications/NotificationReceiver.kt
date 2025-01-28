package com.example.androidtutorial2.notifications

import android.app.AlarmManager
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.SystemClock
import androidx.core.app.NotificationCompat
import com.example.androidtutorial2.di.DaggerNotificationComponent
import com.example.androidtutorial2.model.NotificationData
import javax.inject.Inject

class NotificationReceiver : BroadcastReceiver() {

    @Inject
    lateinit var alarmManager: AlarmManager

    @Inject
    lateinit var notificationManager: NotificationManager

    override fun onReceive(context: Context, intent: Intent?) {

        val notificationComponent = DaggerNotificationComponent.factory().create(context)
        notificationComponent.inject(this)

        val notificationData: NotificationData =
            intent?.getParcelableExtra("notification_data")
                ?: return // Если данные отсутствуют, прерываем выполнение


        val (topicId, topicName, message, remainingTimes) = notificationData

        if (remainingTimes <= 0) {
            cancelPendingIntent(context, topicId)
            return
        }

        //На Android 8.0 (API 26) и выше требуется создать канал уведомлений (NotificationChannel),
        // чтобы пользователи могли управлять поведением уведомлений.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Уведомления для повторения тем",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }

        //Создаем уведомление с помощью NotificationCompat.Builder
        val notification: Notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("Напоминание: $topicName")
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()


        //На Android 13 (API 33) и выше проверяет, предоставлено ли приложению разрешение
        // на отправку уведомлений.
        //Если разрешение отсутствует, выполнение метода прекращается.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (context.checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                return
            }
        }

        //Показываем уведомление пользователю
        notificationManager.notify(topicId, notification)

        //Создаем новый Intent для следующего срабатывания BroadcastReceiver.
        //Уменьшаем remaining_times на 1
        val nextIntent = Intent(context, NotificationReceiver::class.java).apply {
            putExtra(
                "notification_data",
                notificationData.copy(remainingTimes = remainingTimes - 1) // Уменьшаем счётчик
            )
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            topicId,
            nextIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        val triggerTime = SystemClock.elapsedRealtime() + 10 * 1000 // через 10 секунд

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.ELAPSED_REALTIME_WAKEUP,
                triggerTime,
                pendingIntent
            )
        } else {
            alarmManager.setExact(
                AlarmManager.ELAPSED_REALTIME_WAKEUP,
                triggerTime,
                pendingIntent
            )
        }
    }

    private fun cancelPendingIntent(context: Context, topicId: Int) {
        val intent = Intent(context, NotificationReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            topicId, // Используем topicId как уникальный requestCode
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        alarmManager.cancel(pendingIntent) // Отмена запланированного будильника

        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.cancel(topicId) // Отмена активного уведомления
    }

    companion object {
        const val CHANNEL_ID = "notification_channel"
    }
}
