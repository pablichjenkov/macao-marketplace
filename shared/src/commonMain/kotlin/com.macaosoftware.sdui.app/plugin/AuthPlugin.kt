package com.macaosoftware.sdui.app.plugin

import com.macaosoftware.plugin.MacaoPlugin
import com.macaosoftware.sdui.app.util.MacaoError
import com.macaosoftware.sdui.app.util.MacaoResult
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.app
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.database.database
import dev.gitlive.firebase.initialize
import kotlinx.serialization.Serializable

interface AuthPlugin : MacaoPlugin {
    fun initialize()
    suspend fun signup(signupRequest: SignupRequest)
    suspend fun login(loginRequest: LoginRequest)
}

/**
 * An empty implementation for those platforms that don't have Firebase.
 * */
class AuthPluginEmpty : AuthPlugin {
    private val firebaseAuth = Firebase.auth
    private val firebaseDatabase = Firebase.database

    override fun initialize() {
        Firebase.initialize()
        println(" AuthPluginEmpty::initialize() has been called")
    }

    override suspend fun signup(signupRequest: SignupRequest) {
        try {
            firebaseAuth.createUserWithEmailAndPassword(signupRequest.email, signupRequest.password)
            println("AuthPluginEmpty::signup() has been called")
            signupRequest.onResult(MacaoResult.Success(MacaoUser(signupRequest.email)))
        } catch (e: Exception) {
            e.printStackTrace()
            signupRequest.onResult(
                MacaoResult.Error(
                    SignupError(
                        errorDescription = "Signup Failed: ${e.message}"
                    )
                )
            )
        }
    }
    override suspend fun login(loginRequest: LoginRequest) {
        try {
            if (isValidEmail(loginRequest.email) && isValidPassword(loginRequest.password)) {
                val userExists = userExistsInDatabase(loginRequest.email, loginRequest.password)

                if (userExists) {
                  // firebaseAuth.signInWithEmailAndPassword(loginRequest.email, loginRequest.password)
                    println("AuthPluginEmpty::login() has been called")
                    loginRequest.onResult(MacaoResult.Success(MacaoUser(loginRequest.email)))
                } else {
                    loginRequest.onResult(
                        MacaoResult.Error(
                            LoginError(
                                errorDescription = "Invalid email or password"
                            )
                        )
                    )
                }
            } else {
                loginRequest.onResult(
                    MacaoResult.Error(
                        LoginError(
                            errorDescription = "Invalid email or password format"
                        )
                    )
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            loginRequest.onResult(
                MacaoResult.Error(
                    LoginError(
                        errorDescription = "Login Failed: ${e.message}"
                    )
                )
            )
        }
    }
    private fun userExistsInDatabase(email: String, password: String): Boolean {
        return firebaseAuth.currentUser!!.equals(email) && firebaseAuth.currentUser!!.isEmailVerified
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

@Serializable
data class User(
    val email: String,
    val password: String,
    val username: String,
    val phoneNo: String,
)

data class SignupRequest(
    val email: String,
    val password: String,
    val username: String,
    val phoneNo: String,
    val onResult: (MacaoResult<MacaoUser>) -> Unit
)

data class LoginRequest(
    val email: String,
    val password: String,
    val onResult: (MacaoResult<MacaoUser>) -> Unit
)

data class MacaoUser(
    val email: String
)

data class SignupError(
    val instanceId: Long = -1L,
    val errorCode: Int = 1,
    val errorDescription: String = "Signup Failed"
) : MacaoError

data class LoginError(
    val instanceId: Long = -1L,
    val errorCode: Int = 2,
    val errorDescription: String = "Login Failed"
) : MacaoError
