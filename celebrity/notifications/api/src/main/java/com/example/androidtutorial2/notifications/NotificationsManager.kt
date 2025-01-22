package com.example.androidtutorial2.notifications

interface NotificationManager {

    fun scheduleTopicRepeatNotifications(topicId: Int, topicName: String, message: String)

    fun cancelNotifications(topicId: Int, topicName: String, message: String)
}
