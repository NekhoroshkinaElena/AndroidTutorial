package com.example.androidtutorial2.di

import android.content.Context
import androidx.room.Room
import com.example.androidtutorial2.themes.data.db.TutorialDb
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(context: Context): TutorialDb {
        return Room.databaseBuilder(
            context, TutorialDb::class.java, "tutorial.db"
        )
            .createFromAsset("tutorial3.db")
            .build()
    }
}
