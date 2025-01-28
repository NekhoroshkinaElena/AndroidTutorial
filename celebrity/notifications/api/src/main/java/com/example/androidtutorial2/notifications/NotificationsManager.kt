package com.example.androidtutorial2.notifications

interface NotificationsManager {

    fun scheduleTopicRepeatNotifications(topicId: Int, topicName: String, message: String)

    fun cancelNotifications(topicId: Int, topicName: String, message: String)
}
