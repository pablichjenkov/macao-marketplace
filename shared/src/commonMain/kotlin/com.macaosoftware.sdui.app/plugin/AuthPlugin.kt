package com.macaosoftware.sdui.app.plugin

import com.macaosoftware.plugin.MacaoPlugin
import com.macaosoftware.sdui.app.util.MacaoError
import com.macaosoftware.sdui.app.util.MacaoResult
import dev.gitlive.firebase.Firebase
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
            firebaseAuth.signInWithEmailAndPassword(loginRequest.email, loginRequest.password)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        println(" AuthPluginEmpty::login() has been called")
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
