package com.macaosoftware.plugin.account

import com.macaosoftware.util.MacaoResult
import com.macaosoftware.util.ifNotNull
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class FirebaseAccountPlugin(
    private val firebaseAccountSwiftAdapter: FirebaseAccountSwiftAdapter
) : AccountPlugin {

    private val TAG = "FirebaseAuthPlugin"

    override suspend fun initialize(): Boolean {
        return true
    }

    override suspend fun createUserWithEmailAndPassword(signUpRequest: SignUpRequest): MacaoResult<MacaoUser> {

        return suspendCoroutine { continuation ->
            firebaseAccountSwiftAdapter.createUserWithEmailAndPassword(
                signUpRequest.email, signUpRequest.password
            ) { user, error ->
                println("FirebaseAccountPlugin::createUserWithEmailAndPassword -> User = $user and Error = $error")
                error.ifNotNull {
                    continuation.resume(MacaoResult.Error(LoginError(errorDescription = it)))
                    return@createUserWithEmailAndPassword
                }
                user.ifNotNull {
                    continuation.resume(MacaoResult.Success(it))
                }
            }
        }
    }

    override suspend fun signInWithEmailAndPassword(signInRequest: SignInRequest): MacaoResult<MacaoUser> {

        return suspendCoroutine { continuation ->
            firebaseAccountSwiftAdapter.signInWithEmailAndPassword(
                signInRequest.email, signInRequest.password
            ) { user, error ->
                println("FirebaseAccountPlugin::signInWithEmailAndPassword -> User = $user and Error = $error")
                user.ifNotNull {
                    continuation.resume(MacaoResult.Success(it))
                    return@signInWithEmailAndPassword
                }
                error.ifNotNull {
                    continuation.resume(MacaoResult.Error(LoginError(errorDescription = it)))
                }
            }
        }
    }

    override suspend fun signInWithEmailLink(signInRequest: SignInRequestForEmailLink): MacaoResult<MacaoUser> {

        return suspendCoroutine { continuation ->
            firebaseAccountSwiftAdapter.signInWithEmailLink(
                signInRequest.email, signInRequest.magicLink
            ) { user, error ->
                println("FirebaseAccountPlugin::signInWithEmailLink -> User = $user and Error = $error")
                user.ifNotNull {
                    continuation.resume(MacaoResult.Success(it))
                    return@signInWithEmailLink
                }
                error.ifNotNull {
                    continuation.resume(MacaoResult.Error(LoginError(errorDescription = it)))
                }
            }
        }
    }

    override suspend fun sendSignInLinkToEmail(emailLinkData: EmailLinkData): MacaoResult<Unit> {
        println("IosFirebase_sendSignInLinkToEmail")
        return MacaoResult.Success(Unit)
    }

    override suspend fun getCurrentUser(): MacaoResult<MacaoUser> {
        println("IosFirebase_getCurrentUser")
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

    override suspend fun signOut(): MacaoResult<Unit> {
        println("IosFirebase_logoutUser")
        return MacaoResult.Error(SignupError(errorDescription = ""))
    }

}
