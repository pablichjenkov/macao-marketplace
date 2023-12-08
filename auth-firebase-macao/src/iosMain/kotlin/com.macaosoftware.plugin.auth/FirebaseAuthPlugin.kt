package com.macaosoftware.plugin.auth

import com.macaosoftware.plugin.AuthPlugin
import com.macaosoftware.plugin.FirebaseAuth
import com.macaosoftware.plugin.LoginRequest
import com.macaosoftware.plugin.LoginRequestForEmailWithLink
import com.macaosoftware.plugin.LoginRequestForLink
import com.macaosoftware.plugin.MacaoUser
import com.macaosoftware.plugin.ProviderData
import com.macaosoftware.plugin.SignupError
import com.macaosoftware.plugin.SignupRequest
import com.macaosoftware.plugin.UserData
import com.macaosoftware.plugin.util.MacaoResult
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class FirebaseAuthPlugin(
    private val firebaseAuth: FirebaseAuth
) : AuthPlugin {

    private val TAG = "FirebaseAuthPlugin"

    override suspend fun initialize(): Boolean {
        return true
    }

    override suspend fun signup(signupRequest: SignupRequest): MacaoResult<MacaoUser> {

        return suspendCoroutine { continuation ->
            firebaseAuth.createUser(signupRequest.email, signupRequest.password) {
                    continuation.resume(MacaoResult.Success(it))
            }
        }
    }

    override suspend fun login(loginRequest: LoginRequest): MacaoResult<MacaoUser> {

        return suspendCoroutine { continuation ->
            firebaseAuth.createUser(loginRequest.email, loginRequest.password) {
                continuation.resume(MacaoResult.Success(it))
            }
        }
    }

    override suspend fun loginEmailAndLink(loginRequest: LoginRequestForEmailWithLink) {
        println("IosFirebase_loginEmailAndLink")
    }

    override suspend fun sendEmailLink(loginRequest: LoginRequestForLink) {
        println("IosFirebase_sendEmailLink")
    }

    override suspend fun checkCurrentUser(): MacaoResult<MacaoUser> {
        return MacaoResult.Error(SignupError(errorDescription = ""))
    }

    override suspend fun getUserProfile(): MacaoResult<MacaoUser> {
        println("IosFirebase_getUserProfile")
        return MacaoResult.Error(SignupError(errorDescription = ""))
    }

    override suspend fun getProviderData(): MacaoResult<ProviderData> {
        println("IosFirebase_getProviderData")
        return MacaoResult.Error(SignupError(errorDescription = ""))
    }

    override suspend fun updateProfile(
        displayName: String,
        photoUrl: String
    ): MacaoResult<MacaoUser> {
        println("IosFirebase_updateProfile")
        return MacaoResult.Error(SignupError(errorDescription = ""))
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
        println("IosFirebase_updateFullProfile")
        return MacaoResult.Error(SignupError(errorDescription = ""))
    }

    override suspend fun updateEmail(newEmail: String): MacaoResult<MacaoUser> {
        println("IosFirebase_updateEmail")
        return MacaoResult.Error(SignupError(errorDescription = ""))
    }

    override suspend fun updatePassword(newPassword: String): MacaoResult<MacaoUser> {
        println("IosFirebase_updatePassword")
        return MacaoResult.Error(SignupError(errorDescription = ""))
    }

    override suspend fun sendEmailVerification(): MacaoResult<MacaoUser> {
        println("IosFirebase_sendEmailVerification")
        return MacaoResult.Error(SignupError(errorDescription = ""))
    }

    override suspend fun sendPasswordReset(): MacaoResult<MacaoUser> {
        println("IosFirebase_sendPasswordReset")
        return MacaoResult.Error(SignupError(errorDescription = ""))
    }

    override suspend fun deleteUser(): MacaoResult<Unit> {
        println("IosFirebase_deleteUser")
        return MacaoResult.Error(SignupError(errorDescription = ""))
    }

    override suspend fun fetchUserData(): MacaoResult<UserData> {
        println("IosFirebase_fetchUserData")
        return MacaoResult.Error(SignupError(errorDescription = ""))
    }

    override suspend fun checkAndFetchUserData(): MacaoResult<UserData> {
        println("IosFirebase_checkAndFetchUserData")
        return MacaoResult.Error(SignupError(errorDescription = ""))
    }

    override suspend fun logoutUser(): MacaoResult<Unit> {
        println("IosFirebase_logoutUser")
        return MacaoResult.Error(SignupError(errorDescription = ""))
    }

}
