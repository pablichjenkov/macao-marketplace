package com.macaosoftware.plugin.account

import com.macaosoftware.plugin.MacaoPlugin
import com.macaosoftware.util.MacaoError
import com.macaosoftware.util.MacaoResult
import kotlinx.serialization.Serializable
import kotlin.native.ObjCName

interface AccountPlugin : MacaoPlugin {
    suspend fun initialize(): Boolean
    suspend fun createUserWithEmailAndPassword(signUpRequest: SignUpRequest): MacaoResult<MacaoUser>
    suspend fun signInWithEmailAndPassword(signInRequest: SignInRequest): MacaoResult<MacaoUser>
    suspend fun signInWithEmailLink(signInRequest: SignInRequestForEmailLink): MacaoResult<MacaoUser>
    suspend fun sendSignInLinkToEmail(emailLinkData: EmailLinkData): MacaoResult<Unit>
    suspend fun getCurrentUser(): MacaoResult<MacaoUser>

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
    suspend fun signOut(): MacaoResult<Unit>
}

/**
 * An empty implementation for those platforms that don't have Firebase.
 * */
class AccountPluginEmpty : AccountPlugin {
    override suspend fun initialize(): Boolean {
        println(" AuthPluginEmpty::initialize() has been called")
        return true
    }

    override suspend fun createUserWithEmailAndPassword(signUpRequest: SignUpRequest): MacaoResult<MacaoUser> {
        println(" AuthPluginEmpty::createUserWithEmailAndPassword() has been called")
        return MacaoResult.Success(
            MacaoUser("test@gmail.com")
        )
    }

    override suspend fun signInWithEmailAndPassword(signInRequest: SignInRequest): MacaoResult<MacaoUser> {
        println(" AuthPluginEmpty::signInWithEmailAndPassword() has been called")
        return MacaoResult.Success(
            MacaoUser("test@gmail.com")
        )
    }

    override suspend fun signInWithEmailLink(signInRequest: SignInRequestForEmailLink): MacaoResult<MacaoUser> {
        println(" AuthPluginEmpty::signInWithEmailLink() has been called")
        return MacaoResult.Success(MacaoUser("empty@gmail.com"))
    }

    override suspend fun sendSignInLinkToEmail(emailLinkData: EmailLinkData): MacaoResult<Unit> {
        println(" AuthPluginEmpty::sendSignInLinkToEmail() has been called")
        return MacaoResult.Success(Unit)
    }

    override suspend fun getCurrentUser(): MacaoResult<MacaoUser> {
        println(" AuthPluginEmpty::getCurrentUser() has been called")
        return MacaoResult.Success(MacaoUser("empty@gmail.com"))
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

    override suspend fun signOut(): MacaoResult<Unit> {
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
data class UserData(
    val uid: String? = "",
    val email: String? = "",
    val displayName: String? = "",
    val password: String? = "",
    val photoUrl: String? = "",
    val country: String? = "",
    val phoneNo: String? = "",
    val facebookLink: String? = "",
    val linkedIn: String? = "",
    val github: String? = ""
)

@ObjCName(name = "MacaoSignUpRequest", exact = true)
data class SignUpRequest(
    val email: String,
    val password: String,
    val username: String,
    val phoneNo: String
)

@ObjCName(name = "MacaoSignInRequest", exact = true)
data class SignInRequest(
    val email: String,
    val password: String
)

@ObjCName(name = "MacaoSignInRequestForEmailLink", exact = true)
data class SignInRequestForEmailLink(
    val email: String,
    val magicLink: String
)

@ObjCName(name = "MacaoEmailLinkData", exact = true)
data class EmailLinkData(
    val email: String
)

@ObjCName(name = "MacaoUser", exact = true)
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
