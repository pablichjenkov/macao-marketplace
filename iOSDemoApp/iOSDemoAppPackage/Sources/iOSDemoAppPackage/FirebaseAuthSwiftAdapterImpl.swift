
import shared
import FirebaseAuth

public class FirebaseAccountSwiftAdapterImpl : MacaoFirebaseAccountSwiftAdapter {
    
    public init(){}
    
    public func createUserWithEmailAndPassword(
        email: String,
        password: String,
        onResult: @escaping (MacaoUser) -> Void,
        onError: @escaping (String) -> Void
    ) {
    
        Auth.auth().createUser(withEmail: email, password: password) { authResult, error in
          
            print("createUserWithEmailAndPassword -> AuthResult = \(String(describing: authResult)), Error = \(String(describing: error))")
            
            guard let authResult = authResult else {
                onError(error?.localizedDescription ?? "No error info")
                return
            }
            
            onResult(MacaoUser(email: authResult.user.email ?? "Empty Email"))
        }
    }
    
    public func signInWithEmailAndPassword(
        email: String,
        password: String,
        onResult: @escaping (MacaoUser) -> Void,
        onError: @escaping (String) -> Void
    ) {
        Auth.auth().signIn(withEmail: email, password: password) { authResult, error in
          
            print("signInWithEmailAndPassword -> AuthResult = \(String(describing: authResult)), Error = \(String(describing: error))")
            
            guard let authResult = authResult else {
                onError(error?.localizedDescription ?? "No error info")
                return
            }
            
            onResult(MacaoUser(email: authResult.user.email ?? "Empty Email"))
        }
    }
    
    public func signInWithEmailLink(
        email: String,
        magicLink: String,
        onResult: @escaping (MacaoUser) -> Void,
        onError: @escaping (String) -> Void
    ) {
        
        Auth.auth().signIn(withEmail: email, link: magicLink) { authResult, error in
          
            print("signInWithEmailLink -> AuthResult = \(String(describing: authResult)), Error = \(String(describing: error))")
            
            guard let authResult = authResult else {
                onError(error?.localizedDescription ?? "No error info")
                return
            }
            
            onResult(MacaoUser(email: authResult.user.email ?? "Empty Email"))
        }
    }

}
