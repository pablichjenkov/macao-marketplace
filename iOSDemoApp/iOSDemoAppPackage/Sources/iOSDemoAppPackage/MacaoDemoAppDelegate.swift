import SwiftUI
import FirebaseAuthKmp

public class MacaoDemoAppDelegate: NSObject, UIApplicationDelegate {
    
    let firebaseAuthKmpInitializer = FirebaseAuthKmpInitializer()
    
    public func application(_ application: UIApplication,
                            didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil) -> Bool {
        
        print("MacaoAppDelegate application is starting up. ApplicationDelegate didFinishLaunchingWithOptions.")
        
        firebaseAuthKmpInitializer.applicationStart()
        return true
    }
}
