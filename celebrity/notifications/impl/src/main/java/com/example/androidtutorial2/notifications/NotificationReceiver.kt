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
import androidx.core.app.NotificationCompat
import com.example.androidtutorial2.di.DaggerNotificationComponent
import com.example.androidtutorial2.model.NotificationData
import com.example.notifications.impl.R
import javax.inject.Inject

class NotificationReceiver : BroadcastReceiver() {

    @Inject
    lateinit var alarmManager: AlarmManager

    @Inject
    lateinit var notificationManager: NotificationManager

    override fun onReceive(context: Context, intent: Intent?) {
        injectDependencies(context)

        val notificationData: NotificationData =
            intent?.getParcelableExtra(NOTIFICATION_DATA_KEY) ?: return

        if (notificationData.currentRepetition >= 6) {
            cancelNotification(context, notificationData.topicId)
            return
        }

        createNotificationChannel(context)

        if (hasNotificationPermission(context)) {
            showNotification(context, notificationData)
        }
    }

    private fun injectDependencies(context: Context) {
        val notificationComponent = DaggerNotificationComponent.factory().create(context)
        notificationComponent.inject(this)
    }

    private fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                context.getString(R.string.notification_channel_name),
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun showNotification(context: Context, notificationData: NotificationData) {
        val pendingIntent = createDeepLinkPendingIntent(context, notificationData.topicId)
        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle(
                context.getString(
                    R.string.notification_title,
                    notificationData.topicName
                )
            )
            .setContentText(notificationData.message)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .build()

        notificationManager.notify(notificationData.topicId, notification)
    }

    private fun hasNotificationPermission(context: Context): Boolean {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU ||
                context.checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED
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
