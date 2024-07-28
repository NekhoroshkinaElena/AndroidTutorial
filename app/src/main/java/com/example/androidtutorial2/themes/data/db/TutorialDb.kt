package com.example.androidtutorial2.themes.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import javax.inject.Inject
import javax.inject.Singleton

@Database(entities = [ThemeEntity::class], version = 1)
abstract class TutorialDb : RoomDatabase() {

    abstract fun getThemeDao(): ThemeDao
}
