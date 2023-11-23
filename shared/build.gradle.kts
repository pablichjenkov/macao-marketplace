plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    kotlin("plugin.serialization")
    id("com.github.gmazzo.buildconfig")
}

version = "1.0.0"

kotlin {
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

            //Awesome Icons
            implementation("br.com.devsrsouza.compose.icons:font-awesome:1.1.0")

            //Moko MVVM
            implementation("dev.icerock.moko:mvvm-core:0.16.1")

            //Kamel
            implementation("media.kamel:kamel-image:0.8.3")

            //Voygar
            implementation("cafe.adriel.voyager:voyager-navigator:1.0.0-rc10")

            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
            implementation("org.jetbrains.compose.components:components-resources:1.5.10")
            implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.1")
            implementation("io.insert-koin:koin-core:3.5.0")

            // Third Party
            implementation("io.github.pablichjenkov:amadeus-api:0.3.4")
            implementation("io.github.pablichjenkov:component-toolkit:0.5.10")

            // ktor
            implementation("io.ktor:ktor-client-core:$ktorVersion")
            implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
            implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
            implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
            implementation("io.ktor:ktor-client-logging:$ktorVersion")
        }
        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation("io.insert-koin:koin-test:3.5.0")
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