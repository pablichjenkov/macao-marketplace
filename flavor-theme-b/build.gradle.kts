import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl

plugins {
    kotlin("multiplatform")
    alias(libs.plugins.compose)
    id("com.android.library")
}

kotlin {
    androidTarget()
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "FlavorBModule"
            isStatic = true
        }
    }

    js(IR) {
        browser()
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        moduleName = "FlavorBModule"
        browser()
        binaries.library()
    }

    jvm()

    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.ui)
            implementation(compose.foundation)
            implementation(compose.material3)
        }
        commonTest.dependencies {
        }
        androidMain.dependencies {
        }
        jvmMain.dependencies {
        }
        jsMain.dependencies {
        }
    }
}

android {
    namespace = "com.macaosoftware.plugin.flavorb"
    compileSdk = libs.versions.androidCompileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.androidMinSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
