package com.example.androidtutorial2.di

import com.example.androidtutorial2.notifications.NotificationManager
import com.example.androidtutorial2.notifications.NotificationManagerImpl
import dagger.Binds
import dagger.Module

@Module
abstract class NotificationModule {

    @Binds
    abstract fun provideNotificationScheduler(
        notificationManagerImpl: NotificationManagerImpl
    ): NotificationManager
}
