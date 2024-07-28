package com.example.androidtutorial2.di

import com.example.androidtutorial2.sub_themes.data.SubThemeRepositoryImpl
import com.example.androidtutorial2.sub_themes.domain.SubThemesRepository
import com.example.androidtutorial2.themes.data.ThemesRepositoryImpl
import com.example.androidtutorial2.themes.domain.ThemesRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideThemesRepository(themesRepository: ThemesRepositoryImpl): ThemesRepository

    @Binds
    abstract fun provideSubThemesRepository(subThemesRepository: SubThemeRepositoryImpl): SubThemesRepository
}
