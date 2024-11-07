package com.example.androidtutorial2.feature_toggle

import javax.inject.Inject

class FeatureToggleManagerImpl @Inject constructor() : FeatureToggleManager {

    override fun isEnabled(feature: FeatureToggle): Boolean {
        return feature.default
    }
}
