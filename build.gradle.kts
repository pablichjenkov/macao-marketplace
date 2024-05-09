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
    alias(libs.plugins.sqldelight).apply(false)
    alias(libs.plugins.dokka).apply(false)
    // alias(libs.plugins.realm).apply(false)
    id("com.google.gms.google-services").version("4.4.0").apply(false)
}

allprojects {
    afterEvaluate {
        tasks.withType<KotlinCompilationTask<*>>().configureEach {
            compilerOptions {
                with(optIn) {
                    add("kotlin.experimental.ExperimentalObjCName")
                    add("androidx.compose.ui.ExperimentalComposeUiApi")
                    add("androidx.compose.material3.ExperimentalMaterial3Api")
                    add("androidx.compose.foundation.layout.ExperimentalLayoutApi")
                    add("org.jetbrains.compose.resources.ExperimentalResourceApi")
                    add("kotlinx.serialization.ExperimentalSerializationApi")
                }
            }
        }
    }
}
