package com.macaosoftware.sdui.app.plugin

import com.macaosoftware.plugin.AuthPlugin
import com.macaosoftware.plugin.LoginRequest
import com.macaosoftware.plugin.LoginRequestForEmailWithLink
import com.macaosoftware.plugin.LoginRequestForLink
import com.macaosoftware.plugin.MacaoUser
import com.macaosoftware.plugin.ProviderData
import com.macaosoftware.plugin.SignupRequest
import com.macaosoftware.plugin.util.MacaoResult

class AuthPluginJVM: AuthPlugin {
    override suspend fun initialize(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun signup(signupRequest: SignupRequest): MacaoResult<MacaoUser> {
        TODO("Not yet implemented")
    }

    override suspend fun login(loginRequest: LoginRequest): MacaoResult<MacaoUser> {
        TODO("Not yet implemented")
    }

    override suspend fun loginEmailAndLink(loginRequest: LoginRequestForEmailWithLink) {
        TODO("Not yet implemented")
    }

    override suspend fun sendEmailLink(loginRequest: LoginRequestForLink) {
        TODO("Not yet implemented")
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
}