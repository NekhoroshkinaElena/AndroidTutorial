package com.example.androidtutorial2.di

import com.example.androidtutorial2.themes.ui.ThemesFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class, InteractorModule::class])
interface AppComponent {

    fun inject(themesFragment: ThemesFragment)
}
