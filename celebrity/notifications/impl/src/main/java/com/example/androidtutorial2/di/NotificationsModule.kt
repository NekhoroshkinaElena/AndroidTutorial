package com.example.androidtutorial2.di

import com.example.androidtutorial2.notifications.NotificationsManager
import com.example.androidtutorial2.notifications.NotificationsManagerImpl
import dagger.Binds
import dagger.Module

@Module
abstract class NotificationsModule {

    @Binds
    internal abstract fun provideNotificationScheduler(
        notificationManagerImpl: NotificationsManagerImpl
    ): NotificationsManager
}
