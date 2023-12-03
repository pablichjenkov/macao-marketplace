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
            val ktorVersion = "2.3.6"
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.ui)
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

            //Voygar
            implementation(libs.voyager.navigator)

            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.components.resources)
            implementation(libs.kotlinx.datetime)
            implementation(libs.koin.core)

            // Third Party
            implementation(libs.amadeus.api)
            implementation(libs.component.toolkit)

            // ktor
            implementation(libs.ktor.core)
            implementation(libs.io.ktor.ktor.client.serialization)
            implementation(libs.io.ktor.client.content.negotiation)
            implementation(libs.io.ktor.ktor.client.serialization)
            implementation(libs.ktor.client.logging)
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation(libs.koin.test)
        }
        androidMain.dependencies {
            // Firebase
            implementation(platform("com.google.firebase:firebase-bom:32.6.0")) // This line to add the firebase bom
            implementation("com.google.firebase:firebase-auth-ktx")

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
            manifest.srcFile("src/androidMain/AndroidManifest.xml") //res.srcDirs("src/androidMain/res", "src/commonMain/resources")
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
