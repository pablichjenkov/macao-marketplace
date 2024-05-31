## Macao Server UI
This repo is an experiment with server driven ui concepts. A remote server sends a json file which describe the hierarchy of components to render. The project relies on the [Macao Components](https://github.com/pablichjenkov/macao-sdk) library. Such a library has the flexibility to extend functionality in existing components but also create custom ones.
<BR/>
The project also showcase an opinionated architecture to inject swift 3rd party library implementations 


### Modules
1. **macao-sdk-koin**: The foundation to build an App that integrates koin and the Macao component-toolkit. It has already implemented an App startup flow including Koin module initializers and the basic scaffolding to inject pure swift implementations of Macao plugins.

2. **composeApp**: The compose application demo using **macao-sdk-koin** arch.

3. **iOSDemoApp**: Where the iOS app lives. This module consumes the **ComposeApp.framework** produced by composeApp as `direct or local framework integration`. Also this project is configured to use **SPM** as the dependency resolver.

4. **flavor-theme-a**: A module that provides specific styles and assets for a demonstration of what would be the KMP equivalent of Android flavors.

5. **flavor-theme-b**: A different module providing styles and assets for the Android flavors equivalent demonstration.

6. **auth-firebase**: This module contains the abstractions to wrap the **MacaoAccountPlugin** with the Firebase swift SDK. The implementation lives in a hosted swift package located here:

   https://github.com/pablichjenkov/firebase-kmp/blob/main/FirebaseAuthKmp/Sources/FirebaseAuthKmpWrapperImpl.swift

   The local swift package **iOSDemoAppPackage** located in **iOSDemoApp** will declare **FirebaseAuthKmp** package as a dependency. So the hosted package will be pull down in the xcode build process to provide the swift actual implementation of **auth-firebase** abstractions.

7. **auth-supabase**: Similar to **auth-firebase**, this module implements the **MacaoAccountPlugin** but in kotlin land. Since supabase supports KMP we don't have to inject swift code, everything is done in kotlin. It is used to demontrate how you can swap Macao Plugins implementations at build time, based on build configurations. The so called opinionated `Plugin Architecture` mentioned before.

To run the App locally it is required an API key and secret from [Amadeus Travel](https://amadeus.com/) company. Place the respective values in a local `gradle.properties` file or supply them as environment variable.
<BR/>
<BR/>
**amadeus.apiKey**=**Your-Given-Amadeus-Api-Key**
<BR/>
**amadeus.apiSecret**=**Your-Given-Amadeus-Api-Secret**
<BR/>
<BR/>
You will also need an account in firebase with **firebase authorization** product enabled. Place the **google-services.json** given by firebase, in the root directory of **composeApp** module.

### Demo App

<image width="500" src="https://github.com/pablichjenkov/macao-marketplace/assets/5303301/99efccca-8f13-4b1b-a7df-29538f26872a"/>

### Contributions
We welcome contributions from the community! If you have ideas for new features, bug fixes, or improvements, please open an issue or submit a pull request.


   







