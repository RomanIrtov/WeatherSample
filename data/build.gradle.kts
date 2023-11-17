plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.roman.irtov.weathersampel.data"
    compileSdk = rootProject.extra["compileSdk"] as Int

    defaultConfig {
        minSdk = rootProject.extra["minSdk"] as Int

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = rootProject.extra["javaVersion"] as JavaVersion
        targetCompatibility = rootProject.extra["javaVersion"] as JavaVersion
    }

    kotlinOptions {
        jvmTarget = rootProject.extra["jvmTarget"] as String
    }
}

dependencies {
    implementation(project(":domain"))
    implementation(rootProject.extra["coreCtx"] as String)
    implementation(rootProject.extra["appcompat"] as String)
    implementation(rootProject.extra["kotlinCoroutines"] as String)
    implementation(rootProject.extra["gson"] as String)
    implementation(rootProject.extra["retrofit"] as String)
    implementation(rootProject.extra["retrofitGson"] as String)
    implementation(rootProject.extra["javaxInject"] as String)
}