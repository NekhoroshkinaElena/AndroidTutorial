plugins {
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.android.library)
    id("kotlin-kapt")
}

android {
    namespace = "com.example.notifications.impl"
    compileSdk = 34

    defaultConfig {
        minSdk = 28
        targetSdk = 34
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    //dagger
    implementation(libs.dagger)
    implementation(libs.androidx.navigation.runtime.ktx)
    kapt(libs.dagger.compiler)

    implementation(project(":celebrity:notifications:api"))
}
