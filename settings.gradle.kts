rootProject.name = "macao-sdui-app"
include(":flavor-theme-a")
include(":flavor-theme-b")
include(":macao-sdk-koin")
include(":composeApp")

// Auth Plugin implementations
include(":auth-firebase")
include(":auth-supabase")

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://packages.jetbrains.team/maven/p/mpp/dev")
        mavenLocal()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven(url = "https://maven.pkg.jetbrains.space/kotlin/p/wasm/experimental")
        maven("https://packages.jetbrains.team/maven/p/mpp/dev")
        mavenLocal()
    }
}
