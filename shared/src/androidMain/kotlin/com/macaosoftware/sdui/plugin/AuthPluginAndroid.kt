import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.macaosoftware.plugin.AuthPlugin
import com.macaosoftware.plugin.LoginError
import com.macaosoftware.plugin.LoginRequest
import com.macaosoftware.plugin.LoginRequestForEmailWithLink
import com.macaosoftware.plugin.LoginRequestForLink
import com.macaosoftware.plugin.MacaoUser
import com.macaosoftware.plugin.SignupError
import com.macaosoftware.plugin.SignupRequest
import com.macaosoftware.plugin.util.MacaoResult
import kotlinx.coroutines.tasks.await

class AuthPluginAndroid : AuthPlugin {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override suspend fun initialize(): Boolean {
        // Initialization logic (if needed)
        return true
    }

    override suspend fun signup(signupRequest: SignupRequest): MacaoResult<MacaoUser> {
        return try {
            val result = firebaseAuth.createUserWithEmailAndPassword(
                signupRequest.email,
                signupRequest.password
            )

            result.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = firebaseAuth.currentUser
                    // updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    // updateUI(null)
                }
            }

            result.await()

            result.result?.user?.let {
                // Sign up success
                MacaoResult.Success(MacaoUser(signupRequest.email))
            } ?: run {
                // Sign up failed
                MacaoResult.Error(SignupError(errorDescription = "Empty User"))
            }
        } catch (e: Exception) {
            Log.e("AuthPluginAndroid", "Signup Failed: ${e.message}", e)
            MacaoResult.Error(SignupError(errorDescription = e.message ?: "Unknown error"))
        }
    }


    override suspend fun login(loginRequest: LoginRequest): MacaoResult<MacaoUser> {
        return try {
            if (isValidEmail(loginRequest.email) && isValidPassword(loginRequest.password)) {
                val result = firebaseAuth.signInWithEmailAndPassword(
                    loginRequest.email,
                    loginRequest.password
                ).await()

                result.user?.let {
                    // Login success
                    MacaoResult.Success(MacaoUser(loginRequest.email))
                } ?: run {
                    // Login failed
                    MacaoResult.Error(LoginError(errorDescription = "Empty User"))
                }
            } else {
                MacaoResult.Error(
                    LoginError(
                        errorDescription = "Invalid email or password format"
                    )
                )
            }
        } catch (e: FirebaseAuthException) {
            Log.e("AuthPluginAndroid", "Login Failed: ${e.message}", e)
            MacaoResult.Error(LoginError(errorDescription = "Login Failed: ${e.message}"))
        }
    }

    override suspend fun loginEmailAndLink(loginRequest: LoginRequestForEmailWithLink) {
        // Implement if needed
        // Note: Firebase on Android usually handles email link login automatically
    }

    override suspend fun sendEmailLink(loginRequest: LoginRequestForLink) {
        // Implement if needed
        // Note: Firebase on Android usually handles email link sending automatically
    }

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\$")
        return email.trim().matches(emailRegex)
    }

    private fun isValidPassword(password: String): Boolean {
        // Implement password validation logic if needed
        return password.length >= 8
    }
}
