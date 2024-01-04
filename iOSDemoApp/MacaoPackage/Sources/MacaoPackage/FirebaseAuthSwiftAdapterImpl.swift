
import shared
import FirebaseAuth

public class FirebaseAuthSwiftAdapterImpl : Auth_firebase_macaoFirebaseAuthSwiftAdapter {
    
    public init(){}
    
    public func createUser(email: String, password: String, onResult: @escaping (Macao_sdk_mirrorMacaoUser) -> Void) {
    
        Auth.auth().createUser(withEmail: email, password: password) { authResult, error in
          
            if (error == nil) {
                onResult(
                    Macao_sdk_mirrorMacaoUser(
                        email: "FromIosEmail"
                    )
                )
                return
            }
            onResult(
                Macao_sdk_mirrorMacaoUser(
                    email: "FromIosEmail_error"
                )
            )
        }
        
    }
    
    public func signIn(email: String, password: String, onResult: @escaping (KotlinBoolean) -> Void) {
        Auth.auth().signIn(withEmail: email, password: password) { authResult, error in
          
            if (error == nil) {
                onResult(
                    true
                )
                return
            }
            onResult(
                false
            )
        }
    }

}
