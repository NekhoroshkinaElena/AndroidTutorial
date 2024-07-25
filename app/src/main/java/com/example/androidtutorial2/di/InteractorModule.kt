package com.example.androidtutorial2.di

import com.example.androidtutorial2.themes.domain.ThemesInteractor
import com.example.androidtutorial2.themes.domain.ThemesInteractorImpl
import dagger.Binds
import dagger.Module

@Module
abstract class InteractorModule {

    @Binds
    abstract fun provideThemesInteractor(themesInteractor: ThemesInteractorImpl): ThemesInteractor
}
