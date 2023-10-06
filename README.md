## KMP client for Amadeus Self Service API
A kotlin multiplatform library to access Amadeus System APIs.

<H3>amadeus-api is on Maven Central</H3>

```
// your-project-root-path/shared/build.gradle

val commonMain by getting {
    dependencies {
        implementation("io.github.pablichjenkov:amadeus-api:0.3.2")
    }
}
```

<H3>Amadeus API Key</H3>

In order to be able to access Amadeus public API you need to create an Account in the following site:

https://developers.amadeus.com/self-service

In the portal you will get an Api key which you can use to access the free self service API.

<H3>Xcode Setup</H3>

In the project's `Build Settings` make sure the following properties contain bellow values. It ensures passing the linker flags to the objective-c compiler so it links AmadeusDemoKt and sqlite3 frameworks to the build.

**Framework Search Path**

Add the following snippet to the Framework Search Paths under the Search Paths section:
```
$(SRCROOT)/../shared/build/xcode-frameworks/$(CONFIGURATION)/$(SDK_NAME)
```

**Other Linker Flags**

Add the following snippet to the Other Linker flags under the Linking section:
```
$(inherited) -framework shared
```

<H3>Examples</H3>

This is a project using it:<BR>
https://github.com/pablichjenkov/amadeus-hotel-app

Check the shared module within this repo and run the different platform Apps:<BR>
https://github.com/pablichjenkov/kmp-amadeus-api/tree/main/shared
