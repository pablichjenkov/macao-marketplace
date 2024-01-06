
import shared
import FirebaseAuth

public class FirebaseAccountSwiftAdapterImpl : MacaoFirebaseAccountSwiftAdapter {
    
    public init(){}
    
    public func createUserWithEmailAndPassword(email: String, password: String, onResult: @escaping (MacaoUser?, String?) -> Void) {
    
        Auth.auth().createUser(withEmail: email, password: password) { authResult, error in
          
            print("createUserWithEmailAndPassword -> AuthResult = \(authResult), Error = \(error)")
            
            if (error == nil) {
                onResult(nil, error?.localizedDescription)
                return
            }
            
            onResult(
                MacaoUser(email: authResult?.user.email ?? ""),
                nil
            )
        }
        
    }
    
    public func signInWithEmailAndPassword(email: String, password: String, onResult: @escaping (MacaoUser?, String?) -> Void) {
        Auth.auth().signIn(withEmail: email, password: password) { authResult, error in
          
            print("signInWithEmailAndPassword -> AuthResult = \(authResult), Error = \(error)")
            
            guard let authResult = authResult else {
                onResult(nil, error?.localizedDescription)
                return
            }
            
            onResult(
                MacaoUser(email: authResult.user.email ?? ""),
                nil
            )
        }
    }
    
    public func signInWithEmailLink(email: String, magicLink: String, onResult: @escaping (MacaoUser?, String?) -> Void) {
        
        Auth.auth().signIn(withEmail: email, link: magicLink) { authResult, error in
          
            print("signInWithEmailLink -> AuthResult = \(authResult), Error = \(error)")
            
            if (error == nil) {
                onResult(nil, error?.localizedDescription)
                return
            }
            
            onResult(
                MacaoUser(email: authResult?.user.email ?? ""),
                nil
            )
        }
    }

}
