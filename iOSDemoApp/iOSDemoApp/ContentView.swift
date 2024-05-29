import SwiftUI
import UIKit
import ComposeApp

struct ComposeView : UIViewControllerRepresentable {
    
    var iosBridge: IosBridge
    
    func makeUIViewController(context: Context) -> UIViewController {
        
        let mainViewController = BindingsKt.buildDemoMacaoApplication(
            iosBridge: iosBridge
        )
        
        return mainViewController
    }
    
    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
    
    var iosBridge: IosBridge
    
    var body: some View {
        ComposeView(iosBridge: iosBridge)
        //.ignoresSafeArea(.keyboard) // Compose has own keyboard handler
        .ignoresSafeArea(.all, edges: .all) // If prefered to handle this in compose land
        
    }
}

struct ContentView_Previews: PreviewProvider {
    
    static var previews: some View {
        
        let accountPlugin = MacaoAccountPluginEmpty()
        
        let iosBridge = BindingsKt.createPlatformBridge(accountPlugin: accountPlugin)
        
        ContentView(iosBridge: iosBridge)
    }
}
