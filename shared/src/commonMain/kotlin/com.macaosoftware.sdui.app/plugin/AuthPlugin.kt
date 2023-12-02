package com.macaosoftware.sdui.app.plugin

import com.macaosoftware.sdui.app.util.MacaoError
import com.macaosoftware.sdui.app.util.MacaoResult

interface AuthPlugin : MacaoUiPlugin {
    fun initialize()
    fun signup(signupRequest: SignupRequest)
    fun login(loginRequest: LoginRequest)
}

data class SignupRequest(
    val email: String,
    val password: String,
    val onResult: (MacaoResult<MacaoUser>) -> Unit
)

data class LoginRequest(
    val email: String,
    val password: String,
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
