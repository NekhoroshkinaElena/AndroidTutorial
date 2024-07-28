package com.example.androidtutorial2.di

import android.content.Context
import com.example.androidtutorial2.sub_themes.ui.SubThemesFragment
import com.example.androidtutorial2.themes.ui.ThemesFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class, InteractorModule::class, DataModule::class])
interface AppComponent {

    fun inject(themesFragment: ThemesFragment)

    fun inject(subThemesFragment: SubThemesFragment)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}
