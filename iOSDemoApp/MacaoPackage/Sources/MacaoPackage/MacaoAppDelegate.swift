import SwiftUI
import FirebaseCore

public class MacaoAppDelegate: NSObject, UIApplicationDelegate {
  public func application(_ application: UIApplication,
                   didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey : Any]? = nil) -> Bool {
    print("MacaoAppDelegate application is starting up. ApplicationDelegate didFinishLaunchingWithOptions.")
    FirebaseApp.configure()
    return true
  }
}
