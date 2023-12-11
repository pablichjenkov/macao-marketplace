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
            baseName = "PermissionMacao"
            isStatic = true
        }
    }

    js(IR) {
        browser()
    }

    jvm()

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
            // Macao Libs
            implementation(libs.component.toolkit)
        }
        commonTest.dependencies {
            // implementation(libs.kotlin.test)
        }
        androidMain.dependencies {
            implementation(libs.kotlinx.coroutines.android)
            // ktor
            implementation(libs.ktor.core)
            implementation(libs.io.ktor.ktor.client.serialization)
            implementation(libs.io.ktor.client.content.negotiation)
            implementation(libs.io.ktor.ktor.client.serialization)
            implementation(libs.ktor.client.logging)

            api(libs.permissions.compose)
            api(libs.moko.mvvm)
            // Stripe Android SDK
            implementation("com.stripe:stripe-java:24.0.0")
            implementation("com.stripe:stripe-android:20.35.1")
        }
        iosMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
            api(libs.permissions.compose)
            api(libs.moko.mvvm)
        }
    }
}

android {
    namespace = "com.macaosoftware.plugin.permission.macao"
    compileSdk = (findProperty("android.compileSdk") as String).toInt()
    defaultConfig {
        minSdk = (findProperty("android.minSdk") as String).toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
