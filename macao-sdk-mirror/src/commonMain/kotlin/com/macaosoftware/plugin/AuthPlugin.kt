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
    suspend fun checkCurrentUser(): MacaoResult<MacaoUser>
    suspend fun getUserProfile(): MacaoResult<MacaoUser>
    suspend fun getProviderData(): MacaoResult<ProviderData>
    suspend fun updateProfile(displayName: String, photoUrl: String): MacaoResult<MacaoUser>
    suspend fun updateFullProfile(displayName: String?, country: String?, photoUrl: String?, phoneNo: String?, facebookLink: String?, linkedIn: String?, github: String?): MacaoResult<UserData>

    suspend fun updateEmail(newEmail: String): MacaoResult<MacaoUser>
    suspend fun updatePassword(newPassword: String): MacaoResult<MacaoUser>
    suspend fun sendEmailVerification(): MacaoResult<MacaoUser>
    suspend fun sendPasswordReset(): MacaoResult<MacaoUser>
    suspend fun deleteUser(): MacaoResult<Unit>
    suspend fun fetchUserData(): MacaoResult<UserData>
    suspend fun checkAndFetchUserData(): MacaoResult<UserData>
    suspend fun logoutUser(): MacaoResult<Unit>
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
        println(" AuthPluginEmpty::loginEmailAndLink() has been called")
    }

    override suspend fun sendEmailLink(loginRequest: LoginRequestForLink) {
        println(" AuthPluginEmpty::sendEmailLink() has been called")
    }

    override suspend fun checkCurrentUser(): MacaoResult<MacaoUser> {
        println(" AuthPluginEmpty::checkCurrentUser() has been called")
        return MacaoResult.Success(
            MacaoUser("empty@gmail.com")
        )
    }

    override suspend fun getUserProfile(): MacaoResult<MacaoUser> {
        println(" AuthPluginEmpty::getUserProfile() has been called")
        return MacaoResult.Success(
            MacaoUser("empty@gmail.com")
        )
    }

    override suspend fun getProviderData(): MacaoResult<ProviderData> {
        println(" AuthPluginEmpty::getProviderData() has been called")
        return MacaoResult.Success(
            ProviderData("", "", "", "")
        )
    }

    override suspend fun updateProfile(
        displayName: String,
        photoUrl: String
    ): MacaoResult<MacaoUser> {
        TODO("Not yet implemented")
    }

    override suspend fun updateFullProfile(
        displayName: String?,
        country: String?,
        photoUrl: String?,
        phoneNo: String?,
        facebookLink: String?,
        linkedIn: String?,
        github: String?
    ): MacaoResult<UserData> {
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

    override suspend fun fetchUserData(): MacaoResult<UserData> {
        TODO("Not yet implemented")
    }

    override suspend fun checkAndFetchUserData(): MacaoResult<UserData> {
        println(" AuthPluginEmpty::checkAndFetchUserData() has been called")
        return MacaoResult.Success(
            UserData()
        )
    }

    override suspend fun logoutUser(): MacaoResult<Unit> {
        println(" AuthPluginEmpty::logoutUser() has been called")
        return MacaoResult.Success(Unit)
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
    val country: String? = "",
    val phoneNo: String? ="",
    val  facebookLink: String? ="",
    val linkedIn: String?= "",
    val github: String?= ""
) {
    // Empty primary constructor required by Firebase
    constructor() : this("", "", "", "", "", "","","","","")
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
