package com.example.androidtutorial2.di

import android.content.Context
import com.example.androidtutorial2.material_study.ui.MaterialStudyFragment
import com.example.androidtutorial2.repeat.ui.RepeatFragment
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

    fun inject(materialStudyFragment: MaterialStudyFragment)

    fun inject(repeatFragment: RepeatFragment)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}
