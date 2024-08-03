package com.example.androidtutorial2.di

import com.example.androidtutorial2.material_study.domain.MaterialStudyInteractor
import com.example.androidtutorial2.material_study.domain.MaterialStudyInteractorImpl
import com.example.androidtutorial2.sub_themes.domain.SubThemeInteractorImpl
import com.example.androidtutorial2.sub_themes.domain.SubThemesInteractor
import com.example.androidtutorial2.themes.domain.ThemesInteractor
import com.example.androidtutorial2.themes.domain.ThemesInteractorImpl
import dagger.Binds
import dagger.Module

@Module
abstract class InteractorModule {

    @Binds
    abstract fun provideThemesInteractor(themesInteractor: ThemesInteractorImpl): ThemesInteractor

    @Binds
    abstract fun provideSubThemesInteractor(subThemeInteractorImpl: SubThemeInteractorImpl): SubThemesInteractor

    @Binds
    abstract fun provideMaterialStudyInteractor(materialStudyInteractor: MaterialStudyInteractorImpl): MaterialStudyInteractor
}
