import SwiftUI
import UIKit
import ComposeApp

struct ComposeView : UIViewControllerRepresentable {
    
    var swiftWrappersFactory: SwiftWrappersFactory
    
    func makeUIViewController(context: Context) -> UIViewController {
        
        let mainViewController = BindingsKt.buildDemoMacaoApplication(
            swiftWrappersFactory: swiftWrappersFactory
        )
        
        return mainViewController
    }
    
    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
    
    var swiftWrappersFactory: SwiftWrappersFactory
    
    var body: some View {
        ComposeView(swiftWrappersFactory: swiftWrappersFactory)
        //.ignoresSafeArea(.keyboard) // Compose has own keyboard handler
        .ignoresSafeArea(.all, edges: .all) // If prefered to handle this in compose land
        
    }
}

struct ContentView_Previews: PreviewProvider {
    
    static var previews: some View {
        
        let swiftWrappersFactory = SwiftWrappersFactory()
        
        
        ContentView(swiftWrappersFactory: swiftWrappersFactory)
    }
}
