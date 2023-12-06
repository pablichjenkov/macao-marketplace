package com.macaosoftware.plugin.auth

import com.macaosoftware.plugin.AuthPlugin
import com.macaosoftware.plugin.LoginError
import com.macaosoftware.plugin.LoginRequest
import com.macaosoftware.plugin.LoginRequestForEmailWithLink
import com.macaosoftware.plugin.LoginRequestForLink
import com.macaosoftware.plugin.MacaoUser
import com.macaosoftware.plugin.SignupError
import com.macaosoftware.plugin.SignupRequest
import com.macaosoftware.plugin.util.MacaoResult

class SupabaseAuthPlugin : AuthPlugin {

    private val TAG = "SupabaseAuthPlugin"

    override suspend fun initialize(): Boolean {
        return true
    }

    override suspend fun signup(signupRequest: SignupRequest): MacaoResult<MacaoUser> {

        return try {
            val taskResult = Supabase
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
            val taskResult = Supabase
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

    private fun SupabaseUser.toMacaoUser(): MacaoUser {
        return MacaoUser(this.email ?: "no_email")
    }
}
