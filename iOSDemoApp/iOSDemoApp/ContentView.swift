import SwiftUI
import UIKit
import composeApp

struct ComposeView : UIViewControllerRepresentable {

    var iosBridge: IosBridge

    func makeUIViewController(context: Context) -> UIViewController {
        // let amadeusDemoRootComponent = BindingsKt.getSduiRootComponent()

        /*
        let mainViewController = BindingsKt.buildDemoViewController(
            rootComponent: amadeusDemoRootComponent,
            iosBridge: iosBridge,
            onBackPress: {
                exit(0)
            }
        )
        */

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
                .ignoresSafeArea(.keyboard) // Compose has own keyboard handler
                //.ignoresSafeArea(.all, edges: .bottom) // If prefered to handle this in compose land

    }
}
/*
struct ContentView: View {
    var body: some View {
        VStack {
            Image(systemName: "globe")
                .imageScale(.large)
                .foregroundColor(.accentColor)
            Text("Hello, world!")
        }
        .padding()
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

*/
