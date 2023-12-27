plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
}

apply ("../shared_dependencies.gradle")

android {
    namespace = "com.patriciafiona.mario_world.core"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation ("androidx.room:room-runtime:${rootProject.extra["room_version"]}")
    implementation ("androidx.room:room-ktx:${rootProject.extra["room_version"]}")
    kapt ("androidx.room:room-compiler:${rootProject.extra["room_version"]}")
    androidTestImplementation ("androidx.room:room-testing:${rootProject.extra["room_version"]}")

    implementation ("com.squareup.retrofit2:retrofit:${rootProject.extra["retrofit_version"]}")
    implementation ("com.squareup.retrofit2:converter-gson:${rootProject.extra["retrofit_version"]}")
    implementation ("com.squareup.okhttp3:logging-interceptor:${rootProject.extra["logging_interceptor_version"]}")

    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:${rootProject.extra["kotlin_coroutines_version"]}")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:${rootProject.extra["kotlin_coroutines_version"]}")
    api ("androidx.lifecycle:lifecycle-livedata-ktx:${rootProject.extra["lifecycle_version"]}")
}