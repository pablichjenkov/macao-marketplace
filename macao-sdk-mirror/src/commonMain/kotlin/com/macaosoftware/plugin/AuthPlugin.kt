package com.macaosoftware.plugin

import com.macaosoftware.plugin.util.MacaoError
import com.macaosoftware.plugin.util.MacaoResult
import kotlinx.serialization.Serializable


interface AuthPlugin : MacaoPlugin {
    suspend fun initialize(): Boolean
    suspend fun signup(signupRequest: SignupRequest): MacaoResult<MacaoUser>
    suspend fun login(loginRequest: LoginRequest): MacaoResult<MacaoUser>
    suspend fun loginEmailAndLink(loginRequest: LoginRequestForEmailWithLink)
    suspend fun sendEmailLink(loginRequest: LoginRequestForLink)
}

/**
 * An empty implementation for those platforms that don't have Firebase.
 * */
class AuthPluginEmpty : AuthPlugin {
    override suspend fun initialize(): Boolean {
        println(" AuthPluginEmpty::initialize() has been called")
        return true
    }

    override suspend fun signup(signupRequest: SignupRequest): MacaoResult<MacaoUser> {
        println(" AuthPluginEmpty::signup() has been called")
        return MacaoResult.Success(
            MacaoUser("test@gmail.com")
        )
    }

    override suspend fun login(loginRequest: LoginRequest): MacaoResult<MacaoUser> {
        println(" AuthPluginEmpty::login() has been called")
        return MacaoResult.Success(
            MacaoUser("test@gmail.com")
        )
    }

    override suspend fun loginEmailAndLink(loginRequest: LoginRequestForEmailWithLink) {
        // TODO("Not yet implemented")
    }

    override suspend fun sendEmailLink(loginRequest: LoginRequestForLink) {
        // TODO("Not yet implemented")
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
    val phoneNo: String
)

data class LoginRequest(
    val email: String,
    val password: String
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
