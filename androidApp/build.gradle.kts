plugins {
    alias(libs.plugins.multiplatform).apply(true)
    alias(libs.plugins.android.application).apply(true)
    alias(libs.plugins.compose).apply(true)
    id("com.google.gms.google-services")
}

kotlin {
    androidTarget()
    sourceSets {
        androidMain.dependencies {
            implementation(project(":shared"))
            implementation(project(":macao-sdk-mirror"))
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.ui)
            implementation(compose.material3)
            implementation(libs.androidx.activityCompose)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.coroutines.android)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.koin.core)

            // Macao Libs
            implementation(libs.amadeus.api)
            implementation(libs.component.toolkit)

            //Firebase Anddroid
            implementation(platform("com.google.firebase:firebase-bom:32.6.0"))
            implementation("com.google.firebase:firebase-auth")
            implementation("com.google.firebase:firebase-database")

        }
    }
}

android {
    namespace = "com.macaosoftware.sdui.app"
    compileSdk = (findProperty("android.compileSdk") as String).toInt()
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        applicationId = "com.macaosoftware.sdui.app"
        minSdk = (findProperty("android.minSdk") as String).toInt()
        targetSdk = (findProperty("android.targetSdk") as String).toInt()
        versionCode = 1
        versionName = "1.0"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    packaging {
        resources {
            // excludes += "/META-INF/{AL2.0,LGPL2.1}"
            pickFirsts.apply {
                add("META-INF/kotlinx_coroutines_core.version")
                add("META-INF/INDEX.LIST")
                add("META-INF/versions/9/previous-compilation-data.bin")
            }
        }
    }
}
// This Lines to add firebase-common
dependencies {
    implementation("com.google.firebase:firebase-common-ktx:20.3.3")
}
