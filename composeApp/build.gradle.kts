import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.android.application)
    alias(libs.plugins.compose)
    alias(libs.plugins.kotlinx.serialization)
    alias(libs.plugins.gmazzo)
    id("com.google.gms.google-services")
}

version = "1.0.0"

kotlin {

    // ANDROID
    androidTarget()

    // IOS
    val xcf = XCFramework()
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            //baseName = "MacaoSuiDemoKt"
            export(project(":auth-firebase"))
            baseName = "composeApp"
            isStatic = true
            xcf.add(this)
        }
    }

    // JS
    js(IR) {
        browser()
        binaries.executable()
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
            implementation(compose.components.resources)

            //Awesome Icons
            implementation(libs.font.awesome)

            //Kamel
            implementation(libs.kamel.image)

            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.kotlinx.datetime)

            // ktor
            implementation(libs.ktor.core)
            implementation(libs.io.ktor.ktor.client.serialization)
            implementation(libs.io.ktor.client.content.negotiation)
            implementation(libs.io.ktor.ktor.client.serialization)
            implementation(libs.ktor.client.logging)

            // Macao Libs
            implementation(libs.koin.core)
            implementation(project(":macao-sdk-koin"))
            implementation(libs.component.toolkit)
            implementation(libs.amadeus.api)
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation(libs.koin.test)
        }
        iosMain.dependencies {
            // Macao Swift Plugins
            implementation(project(":auth-supabase"))
            api(project(":auth-firebase"))
        }
        androidMain.dependencies {
            implementation(libs.androidx.activityCompose)
            implementation(libs.kotlinx.coroutines.android)

            // Macao Swift Plugins
            implementation(project(":auth-supabase"))
            api(project(":auth-firebase"))
        }
        jvmMain.dependencies {
            implementation(compose.desktop.common)
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)

            // Supabase
            implementation(project(":auth-supabase"))
        }
        jsMain.dependencies {
            implementation(libs.kotlinx.coroutines.core.js)
            implementation(devNpm("copy-webpack-plugin", "9.1.0"))
            implementation(npm("@cashapp/sqldelight-sqljs-worker", "2.0.0"))
            implementation(npm("sql.js", "1.8.0"))
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

compose {
    desktop {
        application {
            mainClass = "com.macaosoftware.sdui.app.MainKt"
            nativeDistributions {
                targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
                packageName = "Amadeus Demo"
                packageVersion = "1.0.0"
                modules("java.sql")
                modules("java.net.http")

                // val iconsRoot = project.file("../common/src/desktopMain/resources/images")
                windows {
                    menuGroup = "Macao SDUI"
                    // see https://wixtoolset.org/documentation/manual/v3/howtos/general/generate_guids.html
                    upgradeUuid = "BF9CDA6A-1391-46D5-9ED5-383D6E68CCEB"
                }
                macOS {
                    // Use -Pcompose.desktop.mac.sign=true to sign and notarize.
                    bundleID = "com.pablichj.incubator.amadeus.demo"
                    // iconFile.set(iconsRoot.resolve("icon-mac.icns"))
                }
                linux {
                    // iconFile.set(iconsRoot.resolve("icon-linux.png"))
                }
                buildTypes.release {
                    proguard {
                        configurationFiles.from(project.file("compose-desktop.pro"))
                    }
                }
            }
        }
    }
    experimental {
        web.application {}
    }
}

/*compose.desktop {
    application {
        mainClass = "com.pablichj.incubator.amadeus.demo.MainKt"

        nativeDistributions {
            targetFormats(org.jetbrains.compose.desktop.application.dsl.TargetFormat.Dmg,
                org.jetbrains.compose.desktop.application.dsl.TargetFormat.Msi,
                org.jetbrains.compose.desktop.application.dsl.TargetFormat.Deb)
            packageName = "Hello World"
            packageVersion = "1.0.0"

            windows {
                menuGroup = "UiState3 Examples"
                // see https://wixtoolset.org/documentation/manual/v3/howtos/general/generate_guids.html
                upgradeUuid = "18159995-d967-4CD2-8885-77BFA97CFA9F"
            }
        }
    }
}*/
