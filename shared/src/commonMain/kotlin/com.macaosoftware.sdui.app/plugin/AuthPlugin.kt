package com.macaosoftware.sdui.app.plugin

import com.macaosoftware.plugin.MacaoPlugin
import com.macaosoftware.sdui.app.util.MacaoError
import com.macaosoftware.sdui.app.util.MacaoResult
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.initialize

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

    override fun initialize() {
        Firebase.initialize()
        println(" AuthPluginEmpty::initialize() has been called")
    }

    override suspend fun signup(signupRequest: SignupRequest) {
        firebaseAuth.createUserWithEmailAndPassword(signupRequest.email, signupRequest.password)
        println(" AuthPluginEmpty::signup() has been called")
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

data class SignupRequest(
    val email: String,
    val password: String,
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
