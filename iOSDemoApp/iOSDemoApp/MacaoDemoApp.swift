import SwiftUI
import shared
import iOSDemoAppPackage
// import FirebaseCore
// import FirebaseAuth

@main
struct MacaoDemoApp: App {

    let iosBridge = BindingsKt.createPlatformBridge(
        firebaseAccountSwiftAdapter: FirebaseAccountSwiftAdapterImpl()
    )
    
    let platformLifecyclePlugin: AppLifecycleDispatcher
    
    // register app delegate for Firebase setup
    @UIApplicationDelegateAdaptor(MacaoDemoAppDelegate.self) var delegate
       
    init() {
        // FirebaseApp.configure()
        platformLifecyclePlugin = iosBridge.platformLifecyclePlugin
    }
       
       var body: some Scene {
           WindowGroup {
               ZStack {
                   Color.white.ignoresSafeArea(.all) // status bar color
                   ContentView(iosBridge: iosBridge)
                       .onReceive(NotificationCenter.default.publisher(for: UIApplication.willEnterForegroundNotification)) { _ in
                           print("application_willEnterForeground")
                       }
                       .onReceive(NotificationCenter.default.publisher(for: UIApplication.didBecomeActiveNotification)) { _ in
                           print("application_didBecomeActive")
                           platformLifecyclePlugin.dispatchAppLifecycleEvent(
                               appLifecycleEvent: .start
                           )
                       }
                       .onReceive(NotificationCenter.default.publisher(for: UIApplication.willResignActiveNotification)) { _ in
                           print("application_willResignActive")
                       }.onReceive(NotificationCenter.default.publisher(for: UIApplication.didEnterBackgroundNotification)) { _ in
                           print("application_didEnterBackground")
                           platformLifecyclePlugin.dispatchAppLifecycleEvent(
                               appLifecycleEvent: .stop
                           )
                       }
               }.preferredColorScheme(.light)
               
           }
       }

}
