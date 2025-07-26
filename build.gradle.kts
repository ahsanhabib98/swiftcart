buildscript {
    repositories {
        google()
        mavenCentral()
        jcenter()  // for br.com.simplepass & stepview
        maven { url = uri("https://verve.jfrog.io/artifactory/verve-gradle-release/") }  // for viewpagerindicator
        maven { url = uri("https://jitpack.io") }  // optional, for any GitHub-hosted libs
    }
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.56.2")
        classpath("com.google.gms:google-services:4.4.1")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.7")
    }
}

plugins {
    id("com.android.application") version "8.11.1" apply false
    // ← Upgrade Kotlin plugin to 2.1.21 so it can read metadata 2.1.0 :contentReference[oaicite:0]{index=0}
    id("org.jetbrains.kotlin.android") version "2.1.21" apply false
}
