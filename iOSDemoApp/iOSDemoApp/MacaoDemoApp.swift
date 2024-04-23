import SwiftUI
import composeApp
import FirebaseAuthKmp
import iOSDemoAppPackage

@main
struct MacaoDemoApp: App {
    
    let iosBridge = BindingsKt.createPlatformBridge(
        firebaseAuthKmpWrapper: FirebaseAuthKmpWrapperImpl()
    )
    
    // register app delegate for Firebase setup
    @UIApplicationDelegateAdaptor(MacaoDemoAppDelegate.self) var delegate
    
    init() {
        // FirebaseAuthKmpInitializer().applicationStart()
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
