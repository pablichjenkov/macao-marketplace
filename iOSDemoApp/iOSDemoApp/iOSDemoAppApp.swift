import SwiftUI
import shared
import MacaoPackage
import FirebaseCore
import FirebaseAuth

@main
struct iOSDemoAppApp: App {

    let iosBridge = BindingsKt.createPlatformBridge(
        firebaseAuthSwiftAdapter: FirebaseAuthSwiftAdapterImpl()
    )
    
    // register app delegate for Firebase setup
    @UIApplicationDelegateAdaptor(MacaoAppDelegate.self) var delegate
       
    init() {
        //FirebaseApp.configure()
        let number : Int = TestVisibility().ty
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
                           iosBridge.platformLifecyclePlugin.dispatchAppLifecycleEvent(
                               appLifecycleEvent: .start
                           )
                       }
                       .onReceive(NotificationCenter.default.publisher(for: UIApplication.willResignActiveNotification)) { _ in
                           print("application_willResignActive")
                       }.onReceive(NotificationCenter.default.publisher(for: UIApplication.didEnterBackgroundNotification)) { _ in
                           print("application_didEnterBackground")
                           iosBridge.platformLifecyclePlugin.dispatchAppLifecycleEvent(
                               appLifecycleEvent: .stop
                           )
                       }
               }.preferredColorScheme(.light)
               
           }
       }

}
