
import shared
import FirebaseAuth

class FirebaseAuthPluginSf : Macao_sdk_mirrorFirebaseAuth {
    
    func createUser(email: String, password: String, onResult: @escaping (Macao_sdk_mirrorMacaoUser) -> Void) {
    
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
    
    func signIn(email: String, password: String, onResult: @escaping (KotlinBoolean) -> Void) {
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

