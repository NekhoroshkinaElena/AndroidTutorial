plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("com.google.devtools.ksp")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.androidtutorial2"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.androidtutorial2"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
    }

    kapt {
        generateStubs = true
        correctErrorTypes = true
    }
}

dependencies {
    implementation(libs.androidx.legacy.support.v4)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.fragment.ktx)
    // room
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    implementation(libs.androidx.preference)
    ksp(libs.room.compiler)
    implementation(libs.androidx.ui.desktop)

    // retrofit
    implementation(libs.retrofit.retrofit)
    implementation(libs.retrofit.gson)

    // fragment navigation
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)

    //dagger
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)

    //glide
    implementation (libs.glide)
    annotationProcessor (libs.compiler)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
