package com.example.androidtutorial2

import android.app.Application
import com.example.androidtutorial2.di.DaggerAppComponent

class TutorialApplication : Application() {
    val appComponent = DaggerAppComponent.create()

    override fun onCreate() {
        super.onCreate()
    }
}
