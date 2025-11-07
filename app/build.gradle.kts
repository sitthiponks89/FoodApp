plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.googleServices)
    id("kotlin-parcelize")
}

android {
    namespace = "com.skdev.foodapp"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.skdev.foodapp"
        minSdk = 30
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    //androidX
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    //coil
    implementation(libs.coil.compose)
    //livedata
    implementation(libs.runtime.livedata)
    //gson
    implementation(libs.gson)
    //constraintlayout
    implementation(libs.constraintlayout.compose)
    //foundation
    implementation(libs.compose.foundation)
    //navigation
    implementation(libs.navigation.compose)
    //viewmodel
    implementation(libs.lifecycle.viewmodel.compose)
    //material
    implementation(libs.compose.material)
    //firebase
    implementation (platform(libs.firebase.bom))
    implementation (libs.firebase.analytics.ktx)
    implementation(libs.firebase.firestore.ktx)
    implementation (libs.firebase.database)
    implementation( libs.firebase.storage)
    implementation(libs.firebase.dynamic.links)
    //moshi
    implementation (libs.converter.moshi)
    //ui
    implementation(libs.ui)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}