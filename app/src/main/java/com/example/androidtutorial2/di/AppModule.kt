package com.example.androidtutorial2.di

import com.example.androidtutorial2.feature_toggle.FeatureToggleManager
import com.example.androidtutorial2.feature_toggle.FeatureToggleManagerImpl
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {

    @Binds
    abstract fun provideFeatureToggleManager(
        featureToggleManagerImpl: FeatureToggleManagerImpl
    ): FeatureToggleManager
}
