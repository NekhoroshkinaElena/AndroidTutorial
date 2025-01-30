package com.example.androidtutorial2.notifications

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
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
        injectDependencies(context)
        val notificationData: NotificationData = intent?.getParcelableExtra(NOTIFICATION_DATA_KEY) ?: return

        if (notificationData.remainingTimes <= 0) {
            cancelNotification(context, notificationData.topicId)
            return
        }

        if (notificationData.currentRepetition >= 6) {
            cancelNotification(context, notificationData.topicId)
            return
        }

        createNotificationChannel()
        showNotification(context, notificationData)
    }

    private fun injectDependencies(context: Context) {
        val notificationComponent = DaggerNotificationComponent.factory().create(context)
        notificationComponent.inject(this)
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Уведомления для повторения тем",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun showNotification(context: Context, notificationData: NotificationData) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
            context.checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        val pendingIntent = createDeepLinkPendingIntent(context, notificationData.topicId)
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("Напоминание: ${notificationData.topicName}")
            .setContentText(notificationData.message)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .build()

        notificationManager.notify(notificationData.topicId, notification)
    }

    private fun createDeepLinkPendingIntent(context: Context, topicId: Int): PendingIntent {
        val deepLinkIntent =
            Intent(Intent.ACTION_VIEW, Uri.parse("app://studyRepeat/$topicId")).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
        return PendingIntent.getActivity(
            context,
            topicId,
            deepLinkIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    private fun scheduleNextNotification(context: Context, notificationData: NotificationData) {
        val nextIntent = Intent(context, NotificationReceiver::class.java).apply {
            putExtra(
                NOTIFICATION_DATA_KEY,
                notificationData.copy(remainingTimes = notificationData.remainingTimes - 1)
            )
        }

        val nextPendingIntent = PendingIntent.getBroadcast(
            context,
            notificationData.topicId,
            nextIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val triggerTime = SystemClock.elapsedRealtime() + 10 * 1000

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.ELAPSED_REALTIME_WAKEUP,
                triggerTime,
                nextPendingIntent
            )
        } else {
            alarmManager.setExact(
                AlarmManager.ELAPSED_REALTIME_WAKEUP,
                triggerTime,
                nextPendingIntent
            )
        }
    }

    private fun cancelNotification(context: Context, topicId: Int) {
        val intent = Intent(context, NotificationReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            topicId,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        alarmManager.cancel(pendingIntent)
        notificationManager.cancel(topicId)
    }

    companion object {
        const val NOTIFICATION_DATA_KEY = "notification_data"
        const val CHANNEL_ID = "notification_channel"
    }
}
