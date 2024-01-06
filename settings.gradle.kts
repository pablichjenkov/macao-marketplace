rootProject.name = "macao-sdui-app"
include(":shared")
include(":androidApp")
include(":jsApp")
include(":jvmApp")
include(":macao-sdk-mirror")

// Auth Plugin implementations
include(":auth-firebase")
include(":auth-supabase")

//include(":component")
//project(":component").projectDir = File("../component/component")

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        mavenLocal()
    }
    plugins {
        val kotlinVersion = extra["kotlin.version"] as String
        val agpVersion = extra["agp.version"] as String
        val composeVersion = extra["compose.version"] as String

        kotlin("multiplatform").version(kotlinVersion)
        kotlin("jvm").version(kotlinVersion)
        kotlin("android").version(kotlinVersion)
        kotlin("plugin.serialization").version(kotlinVersion)

        id("com.android.application").version(agpVersion)
        id("com.android.library").version(agpVersion)
        id("org.jetbrains.compose").version(composeVersion)
        id("com.github.gmazzo.buildconfig").version("4.0.4")
        id("org.jetbrains.dokka").version("1.8.10")

        // Realm
        id("io.realm.kotlin") version "1.11.1"
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://maven.pkg.jetbrains.space/public/p/compose/dev")
        mavenLocal()
    }
}
