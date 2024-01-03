// import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.compose)
}

kotlin {
    js(IR) {
        browser() /*{ // todo: report error when uncommenting the block bellow
            commonWebpackConfig(
                Action<KotlinWebpackConfig> {
                    outputFileName = "sdui-demo-app.js"
                }
            )
        }*/
        binaries.executable()
    }
    sourceSets {
        jsMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.ui)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.coroutines.core.js)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.koin.core)
            implementation(devNpm("copy-webpack-plugin", "9.1.0"))
            implementation(npm("@cashapp/sqldelight-sqljs-worker", "2.0.0"))
            implementation(npm("sql.js", "1.8.0"))

            // Macao Libs
            implementation(project(":shared"))
            implementation(libs.amadeus.api)
            implementation(libs.component.toolkit)
            implementation(libs.macao.sdk.di.koin)
        }
    }
}

compose.experimental {
    web.application {}
}
