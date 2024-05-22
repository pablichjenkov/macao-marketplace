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
val flavorProvider = extra["flavorProvider"] as String

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

            // All swift packages kmp modules have to be exported through the umbrella
            // framework so the swift side can see it.
            export(project(":auth-firebase"))
            export(libs.component.toolkit)

            // Macao convention requires iOS umbrella framework to be named "ComposeApp".

            // DON'T
            // baseName = "MacaoServerUiDemoKt"

            // OK
            baseName = "ComposeApp"

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

            // Desktop Previews (uses different Jetbrains library)
            implementation(compose.components.uiToolingPreview)

            // Awesome Icons
            implementation(libs.font.awesome)

            // Kamel
            implementation(libs.kamel.image)

            // Kotlin Utils
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.kotlinx.datetime)

            // ktor
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.client.logging)

            // Macao Libs
            implementation(libs.koin.core)
            implementation(project(":macao-sdk-koin"))
            api(libs.component.toolkit)
            implementation(libs.amadeus.api)

            // Decide which flavor to use based on a build environment variable
            when (flavorProvider) {
                "A" -> {
                    implementation(project(":flavor-theme-a"))
                }

                "B" -> {
                    implementation(project(":flavor-theme-b"))
                }

                else -> { // Default if not provided
                    implementation(project(":flavor-theme-a"))
                }
            }
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation(libs.koin.test)
        }
        iosMain.dependencies {
            // Macao Swift Plugins
            implementation(project(":auth-supabase"))
            api(project(":auth-firebase"))

            // Ktor
            implementation(libs.ktor.client.darwin)
        }
        androidMain.dependencies {
            implementation(libs.activity.compose)
            implementation(libs.kotlinx.coroutines.android)

            // Previews
            implementation(libs.compose.ui.tooling.preview)

            // Ktor
            implementation(libs.ktor.client.okhttp)

            // Macao Swift Plugins
            implementation(project(":auth-supabase"))
            api(project(":auth-firebase"))
        }
        jvmMain.dependencies {
            implementation(compose.desktop.common)
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutines.swing)

            // Ktor
            implementation(libs.ktor.client.java)

            // Supabase
            implementation(project(":auth-supabase"))
        }
        jsMain.dependencies {
            implementation(libs.kotlinx.coroutines.core.js)
            implementation(devNpm("copy-webpack-plugin", "9.1.0"))
            implementation(npm("@cashapp/sqldelight-sqljs-worker", "2.0.0"))
            implementation(npm("sql.js", "1.8.0"))

            // Ktor
            implementation(libs.ktor.client.js)
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
    compileSdk = libs.versions.androidCompileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "com.macaosoftware.sdui.app"
        minSdk = libs.versions.androidMinSdk.get().toInt()
        targetSdk = libs.versions.androidTargetSdk.get().toInt()
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
    dependencies {
        // Previews
        debugImplementation(libs.compose.ui.tooling)
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
