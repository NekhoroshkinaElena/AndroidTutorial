package com.example.androidtutorial2.di

import android.content.Context
import com.example.androidtutorial2.notifications.NotificationReceiver
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [SystemServicesModule::class]
)
interface NotificationComponent {

    fun inject(receiver: NotificationReceiver)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): NotificationComponent
    }
}
