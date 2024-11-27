package com.example.androidtutorial2.di

import android.content.Context
import com.example.androidtutorial2.styles.StyleStringsProvider
import com.example.androidtutorial2.styles.StyleStringsProviderImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StyleModule {

    @Provides
    @Singleton
    fun provideStyleStringsProvider(context: Context): StyleStringsProvider {
        return StyleStringsProviderImpl(context)
    }
}
