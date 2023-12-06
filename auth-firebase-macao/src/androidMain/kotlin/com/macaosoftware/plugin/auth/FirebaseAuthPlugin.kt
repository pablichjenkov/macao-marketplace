package com.macaosoftware.plugin.auth

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.macaosoftware.plugin.AuthPlugin
import com.macaosoftware.plugin.LoginError
import com.macaosoftware.plugin.LoginRequest
import com.macaosoftware.plugin.LoginRequestForEmailWithLink
import com.macaosoftware.plugin.LoginRequestForLink
import com.macaosoftware.plugin.MacaoUser
import com.macaosoftware.plugin.SignupError
import com.macaosoftware.plugin.SignupRequest
import com.macaosoftware.plugin.util.MacaoResult
import kotlinx.coroutines.tasks.await

class FirebaseAuthPlugin : AuthPlugin {

    private val TAG = "FirebaseAuthPlugin"

    override suspend fun initialize(): Boolean {
        return true
    }

    override suspend fun signup(signupRequest: SignupRequest): MacaoResult<MacaoUser> {

        return try {
            val taskResult = Firebase
                .auth
                .createUserWithEmailAndPassword(signupRequest.email, signupRequest.password)
                .await()
            taskResult.user?.let {
                MacaoResult.Success(it.toMacaoUser())
            } ?: run {
                MacaoResult.Error(SignupError(errorDescription = "createUserWithEmailAndPassword:success but unexpected use null"))
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            MacaoResult.Error(SignupError(errorDescription = ex.message.orEmpty()))
        }
    }

    override suspend fun login(loginRequest: LoginRequest): MacaoResult<MacaoUser> {

        return try {
            val taskResult = Firebase
                .auth
                .signInWithEmailAndPassword(loginRequest.email, loginRequest.password)
                .await()

            return taskResult.user?.let {
                MacaoResult.Success(it.toMacaoUser())
            } ?: run {
                MacaoResult.Error(LoginError(errorDescription = "signInWithEmailAndPassword:success but unexpected use null"))
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            MacaoResult.Error(LoginError(errorDescription = ex.message.orEmpty()))
        }
    }

    override suspend fun loginEmailAndLink(loginRequest: LoginRequestForEmailWithLink) {
        // TODO("Not yet implemented")
    }

    override suspend fun sendEmailLink(loginRequest: LoginRequestForLink) {
        // TODO("Not yet implemented")
    }

    private fun FirebaseUser.toMacaoUser(): MacaoUser {
        return MacaoUser(this.email ?: "no_email")
    }
}
