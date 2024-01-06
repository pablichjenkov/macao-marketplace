import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.multiplatform).apply(false)
    alias(libs.plugins.compose).apply(false)
    alias(libs.plugins.android.application).apply(false)
    alias(libs.plugins.android.library).apply(false)
    alias(libs.plugins.kotlinx.serialization).apply(false)
    alias(libs.plugins.gmazzo).apply(false)
    alias(libs.plugins.sqldeligh).apply(false)
    alias(libs.plugins.dokka).apply(false)
    alias(libs.plugins.realm).apply(false)
    id("com.google.gms.google-services").version("4.4.0").apply(false)
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://maven.pkg.jetbrains.space/public/p/compose/dev")
        mavenLocal()
    }
    afterEvaluate {
        tasks.withType<KotlinCompilationTask<*>>().configureEach {
            compilerOptions {
                with(freeCompilerArgs) {
                    add("-opt-in=kotlin.experimental.ExperimentalObjCName")
                    add("-opt-in=androidx.compose.ui.ExperimentalComposeUiApi")
                    add("-opt-in=androidx.compose.material3.ExperimentalMaterial3Api")
                    add("-opt-in=androidx.compose.foundation.layout.ExperimentalLayoutApi")
                    add("-opt-in=org.jetbrains.compose.resources.ExperimentalResourceApi")
                    add("-opt-in=kotlinx.serialization.ExperimentalSerializationApi")
                }
            }
        }
    }
}
