import SwiftUI
import ComposeApp
import FirebaseAuthKmp
import iOSDemoAppPackage

@main
struct MacaoDemoApp: App {
    
    let accountPlugin: AccountPlugin
    let swiftWrappersFactory: SwiftWrappersFactory
    
    // register app delegate for Firebase setup
    // @UIApplicationDelegateAdaptor(MacaoDemoAppDelegate.self) var delegate
    
    init() {
        // If using @UIApplicationDelegateAdaptor then comment out bellow line
        FirebaseAuthKmpInitializer().applicationStart()
        
        accountPlugin = FirebaseAccountPlugin(
            firebaseAuthKmpWrapper: FirebaseAuthKmpWrapperImpl()
        )
        
        swiftWrappersFactory = SwiftWrappersFactory()
        swiftWrappersFactory.accountPluginSwiftWrapperBase = FirebaseAuthKmpWrapperImpl()
    }
    
    var body: some Scene {
        WindowGroup {
            ZStack {
                Color.white.ignoresSafeArea(.all) // status bar color
                ContentView(swiftWrappersFactory: swiftWrappersFactory)
            }.preferredColorScheme(.light)
        }
    }
}
