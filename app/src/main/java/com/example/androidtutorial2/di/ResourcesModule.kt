package com.example.androidtutorial2.di

import com.example.androidtutorial2.resources.StringProvider
import com.example.androidtutorial2.resources.StringProviderImpl
import com.example.androidtutorial2.resources.StyleStringsProvider
import com.example.androidtutorial2.resources.StyleStringsProviderImpl
import dagger.Binds
import dagger.Module

@Module
abstract class ResourcesModule {

    @Binds
    abstract fun provideStyleStringsProvider(styleStringsProvider: StyleStringsProviderImpl): StyleStringsProvider

    @Binds
    abstract fun provideStringsProvider(stringsProvider: StringProviderImpl): StringProvider
}
