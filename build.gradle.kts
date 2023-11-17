// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.3" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.android.library") version "8.1.3" apply false
}

object LibrariesVersions {
    const val minSdk = 28
    const val compileSdk = 34
    const val coreCtx = "androidx.core:core-ktx:1.9.0"
    const val appcompat = "androidx.core:core-ktx:1.9.0"
    const val material = "com.google.android.material:material:1.10.0"
    const val constraint = "androidx.constraintlayout:constraintlayout:2.1.4"
    const val retrofitGson = "com.squareup.retrofit2:converter-gson:2.9.0"
    const val retrofit = "com.squareup.retrofit2:retrofit:2.9.0"
    const val daggerVersion = "2.48.1"
    const val dagger = "com.google.dagger:dagger:$daggerVersion"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:$daggerVersion"
    const val glide = "com.github.bumptech.glide:glide:4.16.0"
    const val gson = "com.google.code.gson:gson:2.10.1"
    const val kotlinCoroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3"
    const val javaxInject = "javax.inject:javax.inject:1"
    const val activityKtx = "androidx.activity:activity-ktx:1.8.0"
}

mapOf(
    "coreCtx" to LibrariesVersions.coreCtx,
    "appcompat" to LibrariesVersions.appcompat,
    "material" to  LibrariesVersions.material,
    "constraint" to LibrariesVersions.constraint,
    "minSdk" to LibrariesVersions.minSdk,
    "compileSdk" to LibrariesVersions.compileSdk,
    "javaVersion" to  JavaVersion.VERSION_17,
    "jvmTarget" to "17",
    "retrofitGson" to LibrariesVersions.retrofitGson,
    "retrofit" to LibrariesVersions.retrofit,
    "dagger" to LibrariesVersions.dagger,
    "daggerCompiler" to LibrariesVersions.daggerCompiler,
    "glide" to LibrariesVersions.glide,
    "gson"  to LibrariesVersions.gson,
    "kotlinCoroutines" to LibrariesVersions.kotlinCoroutines,
    "javaxInject" to LibrariesVersions.javaxInject,
    "activityKtx" to LibrariesVersions.activityKtx
).forEach{ (name, value) ->
    rootProject.extra.set(name, value)
}