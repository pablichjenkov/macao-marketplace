import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

kotlin {
    js(IR) {
        browser {
            commonWebpackConfig(
                Action<KotlinWebpackConfig> {
                    outputFileName = "sdui-demo-app.js"
                }
            )
        }
        binaries.executable()
    }
    sourceSets {
        val jsMain by getting  {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.ui)
                implementation(compose.material3)
                implementation(project(":shared"))
                implementation(project(":amadeus-api"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:1.7.3")
                implementation(devNpm("copy-webpack-plugin", "9.1.0"))
                implementation(npm("@cashapp/sqldelight-sqljs-worker", "2.0.0"))
                implementation(npm("sql.js", "1.8.0"))
            }
        }
    }
}

compose.experimental {
    web.application {}
}
