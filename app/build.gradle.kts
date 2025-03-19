plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("dagger.hilt.android.plugin")
    id("com.google.dagger.hilt.android")
    kotlin("plugin.serialization") version "1.9.0"
    id("kotlin-parcelize")
    kotlin("kapt") // Ensure this is added for kapt to work
}

kapt {
    correctErrorTypes = true
}

android {
    namespace = "geeky.saif.composektordi"
    compileSdk = 35

    defaultConfig {
        applicationId = "geeky.saif.composektordi"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation (libs.androidx.navigation.compose)

    //shared preference
    implementation(libs.androidx.datastore.preferences)


    // Ktor dependencies
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.okhttp)
     implementation(libs.kotlinx.serialization.json)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)


    // Kotlin Coroutines
    implementation(libs.kotlinx.coroutines.android)

    // Hilt for Dependency Injection
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    // Jetpack Compose
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    //hilt navigation compose
    implementation(libs.androidx.hilt.navigation.compose)

    // Hilt Core
    implementation(libs.hilt.android.v244)
    kapt(libs.hilt.android.compiler)

  // Kotlin Annotation Processor (if using Kotlin)
    kapt(libs.androidx.hilt.compiler)

    //coil for image loading
    implementation(libs.coil.compose)



}