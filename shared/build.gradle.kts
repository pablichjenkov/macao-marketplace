plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.gmazzo)
}

version = "1.0.0"

kotlin {

    // ANDROID
    androidTarget {
        publishLibraryVariants("release")
    }

    // IOS
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries {
            framework {
                baseName = "MacaoSuiDemoKt"
                isStatic = true
            }
        }
    }

    // JS
    js(IR) {
        browser()
    }

    // JVM
    jvm()

    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.ui)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.materialIconsExtended)
            @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
            implementation(compose.components.resources)

            //Awesome Icons
            implementation(libs.font.awesome)

            //Moko MVVM
            implementation(libs.mvvm.core)

            //Kamel
            implementation(libs.kamel.image)

            //Voyager
            implementation(libs.voyager.navigator)

            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.components.resources)
            implementation(libs.kotlinx.datetime)
            implementation(libs.koin.core)

            // ktor
            implementation(libs.ktor.core)
            implementation(libs.io.ktor.ktor.client.serialization)
            implementation(libs.io.ktor.client.content.negotiation)
            implementation(libs.io.ktor.ktor.client.serialization)
            implementation(libs.ktor.client.logging)

            // Macao Libs
            implementation(libs.amadeus.api)
            implementation(libs.component.toolkit)
            implementation(project(":macao-sdk-mirror"))
            implementation(project(":permission-macao"))
            implementation(project(":notifications-macao"))

            // Auth Plugins
            // implementation(project(":auth-firebase-gitlive")) // Not working good
            implementation(project(":auth-firebase-macao"))
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation(libs.koin.test)
        }
        androidMain.dependencies {
            implementation(libs.kotlinx.coroutines.android)
            implementation(project(":notifications-macao"))
            implementation(project(":permission-macao"))
            implementation("dev.icerock.moko:permissions-compose:0.17.0")
        }
        jvmMain.dependencies {
            implementation(libs.kotlinx.coroutines.swing)
        }
        jsMain.dependencies {
            implementation(libs.kotlinx.coroutines.core.js)
        }
    }

}

buildConfig {
    useKotlinOutput { internalVisibility = true }
    packageName("com.pablichj.incubator.amadeus.demo")

    val amadeusApiKey = extra["amadeus.apiKey"] as String
    require(amadeusApiKey.isNotEmpty()) {
        "Register your api key from amadeus and place it in local.properties as `amadeus.apiKey`"
    }

    val amadeusApiSecret = extra["amadeus.apiSecret"] as String
    require(amadeusApiKey.isNotEmpty()) {
        "Register your api secret from amadeus and place it in local.properties as `amadeus.apiSecret`"
    }

    buildConfigField(
        "String",
        "AMADEUS_API_KEY", amadeusApiKey
    )

    buildConfigField(
        "String",
        "AMADEUS_API_SECRET", amadeusApiSecret
    )

}

android {
    namespace = "com.macaosoftware.sdui.app"
    compileSdk = (findProperty("android.compileSdk") as String).toInt()
    sourceSets {
        named("main") {
            manifest.srcFile("src/androidMain/AndroidManifest.xml")
            res.srcDirs("src/androidMain/res")
            resources.srcDir("src/commonMain/resources")
        }
    }
    defaultConfig {
        minSdk = (findProperty("android.minSdk") as String).toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.4"
    }
}
