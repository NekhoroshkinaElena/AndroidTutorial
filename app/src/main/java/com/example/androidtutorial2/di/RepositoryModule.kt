package com.example.androidtutorial2.di

import com.example.androidtutorial2.material_study.data.MaterialStudyRepositoryImpl
import com.example.androidtutorial2.material_study.domain.MaterialStudyRepository
import com.example.androidtutorial2.sub_topics.data.SubTopicsRepositoryImpl
import com.example.androidtutorial2.sub_topics.domain.SubTopicsRepository
import com.example.androidtutorial2.topic.data.TopicsRepositoryImpl
import com.example.androidtutorial2.topic.domain.TopicsRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideTopicsRepository(topicsRepository: TopicsRepositoryImpl): TopicsRepository

    @Binds
    abstract fun provideSubTopicsRepository(subTopicsRepository: SubTopicsRepositoryImpl): SubTopicsRepository

    @Binds
    abstract fun provideMaterialStudyRepository(materialStudyRepository: MaterialStudyRepositoryImpl): MaterialStudyRepository
}
