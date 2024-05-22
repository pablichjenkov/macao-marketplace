import SwiftUI
import ComposeApp
import FirebaseAuthKmp
import iOSDemoAppPackage

@main
struct MacaoDemoApp: App {
    
    let accountPlugin: AccountPlugin
    let iosBridge: IosBridge
    
    // register app delegate for Firebase setup
    // @UIApplicationDelegateAdaptor(MacaoDemoAppDelegate.self) var delegate
    
    init() {
        // If using @UIApplicationDelegateAdaptor then comment out bellow line
        FirebaseAuthKmpInitializer().applicationStart()
        
        accountPlugin = FirebaseAccountPlugin(
            firebaseAuthKmpWrapper: FirebaseAuthKmpWrapperImpl()
        )
        iosBridge = BindingsKt.createPlatformBridge(accountPlugin: accountPlugin)
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
