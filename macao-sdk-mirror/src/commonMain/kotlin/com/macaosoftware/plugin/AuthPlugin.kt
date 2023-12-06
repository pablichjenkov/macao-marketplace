package com.macaosoftware.plugin

import com.macaosoftware.plugin.util.MacaoError
import com.macaosoftware.plugin.util.MacaoResult
import kotlinx.serialization.Serializable
import kotlin.jvm.JvmOverloads


interface AuthPlugin : MacaoPlugin {
    suspend fun initialize(): Boolean
    suspend fun signup(signupRequest: SignupRequest): MacaoResult<MacaoUser>
    suspend fun login(loginRequest: LoginRequest): MacaoResult<MacaoUser>
    suspend fun loginEmailAndLink(loginRequest: LoginRequestForEmailWithLink)
    suspend fun sendEmailLink(loginRequest: LoginRequestForLink)
    suspend fun checkCurrentUser(): MacaoResult<MacaoUser>
    suspend fun getUserProfile(): MacaoResult<MacaoUser>
    suspend fun getProviderData(): MacaoResult<ProviderData>
    suspend fun updateProfile(displayName: String,photoUrl: String): MacaoResult<MacaoUser>
    suspend fun updateEmail(newEmail: String): MacaoResult<MacaoUser>
    suspend fun updatePassword(newPassword: String): MacaoResult<MacaoUser>
    suspend fun sendEmailVerification(): MacaoResult<MacaoUser>
    suspend fun sendPasswordReset(): MacaoResult<MacaoUser>
    suspend fun deleteUser(): MacaoResult<Unit>
    suspend fun fetchUserData(userId: String): MacaoResult<UserData>
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

    override suspend fun checkCurrentUser(): MacaoResult<MacaoUser> {
        TODO("Not yet implemented")
    }

    override suspend fun getUserProfile(): MacaoResult<MacaoUser> {
        TODO("Not yet implemented")
    }

    override suspend fun getProviderData(): MacaoResult<ProviderData> {
        TODO("Not yet implemented")
    }

    override suspend fun updateProfile(
        displayName: String,
        photoUrl: String
    ): MacaoResult<MacaoUser> {
        TODO("Not yet implemented")
    }

    override suspend fun updateEmail(newEmail: String): MacaoResult<MacaoUser> {
        TODO("Not yet implemented")
    }

    override suspend fun updatePassword(newPassword: String): MacaoResult<MacaoUser> {
        TODO("Not yet implemented")
    }

    override suspend fun sendEmailVerification(): MacaoResult<MacaoUser> {
        TODO("Not yet implemented")
    }

    override suspend fun sendPasswordReset(): MacaoResult<MacaoUser> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUser(): MacaoResult<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun fetchUserData(userId: String): MacaoResult<UserData> {
        TODO("Not yet implemented")
    }


}

@Serializable
data class ProviderData(
    val email: String?,
    val displayName: String?,
    val phoneNumber: String?,
    val photoUrl: String?,
    // Add more fields as needed
)

@Serializable
data class User(
    val email: String,
    val password: String,
    val username: String,
    val phoneNo: String,
)
@Serializable
data class UserData(
    val uid: String? = "",
    val email: String? = "",
    val displayName: String? = "",
    val password: String? = "",
    val photoUrl: String? = "",
    val country: String? = ""
) {
    // Empty primary constructor required by Firebase
    constructor() : this("", "", "", "", "", "")
}


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
