plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    kotlin("multiplatform") apply false
    kotlin("jvm") apply false
    kotlin("android") apply false
    kotlin("plugin.serialization") apply false

    id("com.android.application") apply false
    id("com.android.library") apply false
    id("org.jetbrains.compose") apply false
    id("com.github.gmazzo.buildconfig") apply false
    id("app.cash.sqldelight").version("2.0.0").apply(false)
    id("org.jetbrains.dokka") apply false
    id("io.realm.kotlin") apply false
}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://maven.pkg.jetbrains.space/public/p/compose/dev")
        mavenLocal()
    }
}
