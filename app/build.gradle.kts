
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
}

android {
    namespace = "com.roman.irtov.weathersampel"
    compileSdk = rootProject.extra["compileSdk"] as Int

    defaultConfig {
        applicationId = "com.roman.irtov.weathersampel"
        minSdk = rootProject.extra["minSdk"] as Int
        targetSdk = rootProject.extra["compileSdk"] as Int
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    viewBinding {
        android.buildFeatures.viewBinding = true
    }
}



dependencies {
    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(rootProject.extra["coreCtx"] as String)
    implementation(rootProject.extra["appcompat"] as String)
    implementation(rootProject.extra["material"] as String)
    implementation(rootProject.extra["constraint"] as String)
    implementation(rootProject.extra["dagger"] as String)
    kapt(rootProject.extra["daggerCompiler"] as String)
    implementation(rootProject.extra["gson"] as String)
    implementation(rootProject.extra["retrofit"] as String)
    implementation(rootProject.extra["retrofitGson"] as String)
    implementation(rootProject.extra["javaxInject"] as String)
    implementation(rootProject.extra["activityKtx"] as String)
    implementation(rootProject.extra["glide"] as String)

}