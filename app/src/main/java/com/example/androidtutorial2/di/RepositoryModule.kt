package com.example.androidtutorial2.di

import com.example.androidtutorial2.themes.data.ThemesRepositoryImpl
import com.example.androidtutorial2.themes.domain.ThemesRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideThemesRepository(themesRepository: ThemesRepositoryImpl): ThemesRepository
}
