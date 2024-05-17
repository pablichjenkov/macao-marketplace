plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose)
    // alias(libs.plugins.kotlinx.serialization)
}

kotlin {
    androidTarget()
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "MacaoAccountSupabase"
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

            // Supabase
            implementation("io.github.jan-tennert.supabase:gotrue-kt:2.0.4")
            // implementation("io.github.jan-tennert.supabase:compose-auth:2.0.4")
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
    namespace = "com.macaosoftware.plugin.auth.supabase"
    compileSdk = libs.versions.androidCompileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.androidMinSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

compose {
    kotlinCompilerPlugin.set(dependencies.compiler.forKotlin("1.9.23"))
    kotlinCompilerPluginArgs.add("suppressKotlinVersionCompatibilityCheck=1.9.24")
    //kotlinCompilerPluginArgs.add("suppressKotlinVersionCompatibilityCheck=1.9.24-spm")
}