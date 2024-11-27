package com.example.androidtutorial2.di

import android.content.Context
import com.example.androidtutorial2.MainActivity
import com.example.androidtutorial2.home.ui.HomeFragment
import com.example.androidtutorial2.material_study.ui.MaterialStudyFragment
import com.example.androidtutorial2.material_study_repeat.MaterialStudyRepeatFragment
import com.example.androidtutorial2.repeat.ui.RepeatFragment
import com.example.androidtutorial2.settings.SettingsFragment
import com.example.androidtutorial2.study.ui.StudyFragment
import com.example.androidtutorial2.sub_themes.ui.SubThemesFragment
import com.example.androidtutorial2.sub_themes_repeat.ui.SubThemesRepeatFragment
import com.example.androidtutorial2.themes.ui.ThemesFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [RepositoryModule::class,
        InteractorModule::class,
        DataModule::class,
        AppModule::class,
        StyleModule::class]
)
interface AppComponent {

    fun inject(activity: MainActivity)

    fun inject(themesFragment: ThemesFragment)

    fun inject(subThemesFragment: SubThemesFragment)

    fun inject(subThemesRepeatFragment: SubThemesRepeatFragment)

    fun inject(materialStudyFragment: MaterialStudyFragment)

    fun inject(materialStudyRepeatFragment: MaterialStudyRepeatFragment)

    fun inject(repeatFragment: RepeatFragment)

    fun inject(homeFragment: HomeFragment)

    fun inject(settingsFragment: SettingsFragment)

    fun inject(studyFragment: StudyFragment)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}
