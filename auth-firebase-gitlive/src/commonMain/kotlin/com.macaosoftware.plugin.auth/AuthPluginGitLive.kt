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
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.ActionCodeSettings
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.database.database
import dev.gitlive.firebase.initialize

/**
 * An empty implementation for those platforms that don't have Firebase.
 * */
class AuthPluginGitLive : AuthPlugin {

    private val firebaseAuth = Firebase.auth
    private val firebaseDatabase = Firebase.database

    override suspend fun initialize(): Boolean {
        Firebase.initialize()
        return true
    }

    override suspend fun signup(signupRequest: SignupRequest): MacaoResult<MacaoUser> {
        return try {
            val result = firebaseAuth
                .createUserWithEmailAndPassword(signupRequest.email, signupRequest.password)

            result.user?.let {
                MacaoResult.Success(MacaoUser(signupRequest.email))
            } ?: MacaoResult.Error(
                SignupError(
                    errorDescription = "Empty User"
                )
            )
        } catch (e: Exception) {
            e.printStackTrace()
            MacaoResult.Error(
                SignupError(
                    errorDescription = "Signup Failed: ${e.message}"
                )
            )
        }
    }

    override suspend fun login(loginRequest: LoginRequest): MacaoResult<MacaoUser>  {
        return try {
            if (isValidEmail(loginRequest.email) && isValidPassword(loginRequest.password)) {
                val userExists = userExistsInDatabase(loginRequest.email)

                if (userExists) {
                    val result  = firebaseAuth.signInWithEmailAndPassword(
                        loginRequest.email,
                        loginRequest.password
                    )
                    result.user?.let {
                        MacaoResult.Success(MacaoUser(loginRequest.email))
                    } ?: run {
                        MacaoResult.Error(
                            LoginError(errorDescription = "Empty User")
                        )
                    }

                } else {
                    MacaoResult.Error(
                        LoginError(
                            errorDescription = "No email found in our database"
                        )
                    )
                }
            } else {
                MacaoResult.Error(
                    LoginError(
                        errorDescription = "Invalid email or password format"
                    )
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            MacaoResult.Error(
                LoginError(
                    errorDescription = "Login Failed: ${e.message}"
                )
            )
        }
    }

    override suspend fun loginEmailAndLink(loginRequest: LoginRequestForEmailWithLink) {
        firebaseAuth.signInWithEmailLink(loginRequest.email, loginRequest.link)
        println("Login with Link AuthPluginGitLive::login() has been called")

    }

    override suspend fun sendEmailLink(loginRequest: LoginRequestForLink) {
        firebaseAuth.sendSignInLinkToEmail(loginRequest.email, actionCodeSettings = ActionCodeSettings(url = "https://macao-sdui-app-30-default-rtdb.firebaseio.com/", canHandleCodeInApp = true))
    }

    private fun userExistsInDatabase(email: String): Boolean {
        return firebaseAuth.currentUser?.email.equals(email) && firebaseAuth.currentUser?.isEmailVerified == true
    }

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\$")
        return email.trim().matches(emailRegex)
    }

    private fun isValidPassword(password: String): Boolean {
        // Implement password validation logic if needed
        return password.length >= 8
    }


}