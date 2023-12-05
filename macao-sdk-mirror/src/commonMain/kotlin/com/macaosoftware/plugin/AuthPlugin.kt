package com.macaosoftware.plugin

import com.macaosoftware.plugin.util.MacaoError
import com.macaosoftware.plugin.util.MacaoResult
import kotlinx.serialization.Serializable


interface AuthPlugin : MacaoPlugin {
    fun initialize()
    suspend fun  signup(signupRequest: SignupRequest)
    suspend fun login(loginRequest: LoginRequest)
    suspend fun loginEmailAndLink(loginRequest: LoginRequestForEmailWithLink)
    suspend fun sendEmailLink(loginRequest: LoginRequestForLink)
}

/**
 * An empty implementation for those platforms that don't have Firebase.
 * */
class AuthPluginEmpty : AuthPlugin {

    override fun initialize() {
        println(" AuthPluginEmpty::initialize() has been called")
    }

    override suspend fun signup(signupRequest: SignupRequest) {
        println(" AuthPluginEmpty::signup() has been called")
    }

    override suspend fun login(loginRequest: LoginRequest) {
        println(" AuthPluginEmpty::login() has been called")
    }

    override suspend fun loginEmailAndLink(loginRequest: LoginRequestForEmailWithLink) {
        TODO("Not yet implemented")
    }

    override suspend fun sendEmailLink(loginRequest: LoginRequestForLink) {
        TODO("Not yet implemented")
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

data class LoginRequestForLink(
    val email: String,
    val onResult: (MacaoResult<MacaoUser>) -> Unit
)

data class LoginRequestForEmailWithLink(
    val email: String,
    val link: String,
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
