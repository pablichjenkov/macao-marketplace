plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose)
    alias(libs.plugins.kotlinx.serialization)
}

kotlin {
    androidTarget()
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "AuthFirebaseGitLive"
            isStatic = true
        }
    }

    js(IR) {
        browser()
    }

    jvm()

    sourceSets {
        commonMain.dependencies {
            // Macao Libs
            implementation(project(":macao-sdk-mirror"))
            implementation(libs.component.toolkit)

            // Firebase GitLive
            api("dev.gitlive:firebase-auth:1.10.4")
            api("dev.gitlive:firebase-database:1.10.4")
        }
        commonTest.dependencies {
            // implementation(libs.kotlin.test)
        }
        androidMain.dependencies {
            implementation(libs.kotlinx.coroutines.android)
        }
        jvmMain.dependencies {
            implementation(libs.kotlinx.coroutines.swing)
        }
        jsMain.dependencies {
            implementation(libs.kotlinx.coroutines.core.js)
        }
    }
}

android {
    namespace = "com.macaosoftware.plugin.auth.firebase_gitlive"
    compileSdk = (findProperty("android.compileSdk") as String).toInt()
    defaultConfig {
        minSdk = (findProperty("android.minSdk") as String).toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

