plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs.kotlin")
   // id("androidx.room")
   // id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.iav.contestdataprovider"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.iav.contestdataprovider"
        minSdk = 24
        targetSdk = 33
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
    buildFeatures{
        viewBinding = true
    }
//    room {
//        schemaDirectory("$projectDir/schemas")
//    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //lifrcycle & Livedata
    implementation (libs.androidx.lifecycle.viewmodel.ktx)
    implementation (libs.androidx.lifecycle.livedata.ktx)

    //hitl di
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    // Navigation
    implementation(libs.androidx.navigation.fragment)
   // implementation(libs.androidx.navigation.ui)
   // implementation ("androidx.navigation:navigation-fragment-ktx:2.8.5")
    implementation (libs.androidx.navigation.ui.ktx)
    implementation (libs.androidx.hilt.navigation.fragment)

    //Coroutine
    implementation(libs.kotlinx.coroutines.android)

   // implementation ("com.iav.contestdataprovider:provider:1.0.0")

    implementation (libs.androidx.recyclerview)
    implementation (libs.gson)

    //Room db
//    implementation("androidx.room:room-runtime:2.6.1")
//    kapt ("androidx.room:room-compiler:2.6.1")

}

kapt {
    correctErrorTypes = true
}