import SwiftUI
import ComposeApp
import FirebaseAuthKmp
import iOSDemoAppPackage

@main
struct MacaoDemoApp: App {
    
    let accountPlugin: AccountPlugin
    let iosBridge: IosBridge
    
//    let iob = IosBridge(
//        accountPlugin: FirebaseAccountPlugin(
//            firebaseAuthKmpWrapper: FirebaseAuthKmpWrapperImpl()
//        )
//    )
    
    // register app delegate for Firebase setup
    // @UIApplicationDelegateAdaptor(MacaoDemoAppDelegate.self) var delegate
    
    init() {
        // If using @UIApplicationDelegateAdaptor then comment out bellow line
        FirebaseAuthKmpInitializer().applicationStart()
        
        accountPlugin = FirebaseAccountPlugin(
            firebaseAuthKmpWrapper: FirebaseAuthKmpWrapperImpl()
        )
        //iosBridge = BindingsKt.createPlatformBridge(accountPlugin: accountPlugin)
        iosBridge = BindingsKt.createPlatformBridge2(accountPluginWrapperBase: FirebaseAuthKmpWrapperImpl()
        )
    }
    
    var body: some Scene {
        WindowGroup {
            ZStack {
                Color.white.ignoresSafeArea(.all) // status bar color
                ContentView(iosBridge: iosBridge)
            }.preferredColorScheme(.light)
        }
    }
}
