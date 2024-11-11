package com.example.androidtutorial2.feature_toggle

interface FeatureToggleManager {
    fun isEnabled(feature: FeatureToggle): Boolean
}
