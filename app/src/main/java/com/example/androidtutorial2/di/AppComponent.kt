package com.example.androidtutorial2.di

import android.content.Context
import com.example.androidtutorial2.MainActivity
import com.example.androidtutorial2.home.ui.HomeFragment
import com.example.androidtutorial2.material_study.ui.MaterialStudyFragment
import com.example.androidtutorial2.material_study_repeat.MaterialStudyRepeatFragment
import com.example.androidtutorial2.repeat.ui.RepeatFragment
import com.example.androidtutorial2.settings.SettingsFragment
import com.example.androidtutorial2.study.ui.StudyFragment
import com.example.androidtutorial2.sub_topics.ui.SubTopicsFragment
import com.example.androidtutorial2.sub_topics_repeat.ui.SubTopicsRepeatFragment
import com.example.androidtutorial2.topic.ui.TopicsFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [RepositoryModule::class,
        InteractorModule::class,
        DataModule::class,
        AppModule::class,
        ResourcesModule::class]
)
interface AppComponent {

    fun inject(activity: MainActivity)

    fun inject(topicsFragment: TopicsFragment)

    fun inject(subTopicsFragment: SubTopicsFragment)

    fun inject(subTopicsRepeatFragment: SubTopicsRepeatFragment)

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
