package com.example.androidtutorial2.di

import com.example.androidtutorial2.material_study.domain.MaterialStudyInteractor
import com.example.androidtutorial2.material_study.domain.MaterialStudyInteractorImpl
import com.example.androidtutorial2.sub_topics.domain.SubTopicsInteractor
import com.example.androidtutorial2.sub_topics.domain.SubTopicsInteractorImpl
import com.example.androidtutorial2.topic.domain.TopicsInteractor
import com.example.androidtutorial2.topic.domain.TopicsInteractorImpl
import dagger.Binds
import dagger.Module

@Module
abstract class InteractorModule {

    @Binds
    abstract fun provideTopicsInteractor(topicsInteractor: TopicsInteractorImpl): TopicsInteractor

    @Binds
    abstract fun provideSubTopicsInteractor(subTopicsInteractorImpl: SubTopicsInteractorImpl): SubTopicsInteractor

    @Binds
    abstract fun provideMaterialStudyInteractor(materialStudyInteractor: MaterialStudyInteractorImpl): MaterialStudyInteractor
}
