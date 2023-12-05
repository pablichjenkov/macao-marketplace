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
    override fun initialize() {
        Firebase.initialize()
        println(" AuthPluginGitLive::initialize() has been called")
    }

    override suspend fun signup(signupRequest: SignupRequest) {
        try {
            firebaseAuth.createUserWithEmailAndPassword(signupRequest.email, signupRequest.password)
            println("AuthPluginEmpty::signup() has been called")
            signupRequest.onResult(MacaoResult.Success(MacaoUser(signupRequest.email)))
        } catch (e: Exception) {
            e.printStackTrace()
            signupRequest.onResult(
                MacaoResult.Error(
                    SignupError(
                        errorDescription = "Signup Failed: ${e.message}"
                    )
                )
            )
        }
        println(" AuthPluginGitLive::signup() has been called")
    }

    override suspend fun login(loginRequest: LoginRequest) {
        try {
            if (isValidEmail(loginRequest.email) && isValidPassword(loginRequest.password)) {
                val userExists = userExistsInDatabase(loginRequest.email, loginRequest.password)

                if (userExists) {
                    firebaseAuth.signInWithEmailAndPassword(
                        loginRequest.email,
                        loginRequest.password
                    )
                    println("AuthPluginEmpty::login() has been called")
                    loginRequest.onResult(MacaoResult.Success(MacaoUser(loginRequest.email)))
                } else {
                    loginRequest.onResult(
                        MacaoResult.Error(
                            LoginError(
                                errorDescription = "Invalid email or password"
                            )
                        )
                    )
                }
            } else {
                loginRequest.onResult(
                    MacaoResult.Error(
                        LoginError(
                            errorDescription = "Invalid email or password format"
                        )
                    )
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            loginRequest.onResult(
                MacaoResult.Error(
                    LoginError(
                        errorDescription = "Login Failed: ${e.message}"
                    )
                )
            )
        }
        println(" AuthPluginGitLive::login() has been called")
    }

    override suspend fun loginEmailAndLink(loginRequest: LoginRequestForEmailWithLink) {
        firebaseAuth.signInWithEmailLink(loginRequest.email, loginRequest.link)
        println("Login with Link AuthPluginGitLive::login() has been called")

    }

    override suspend fun sendEmailLink(loginRequest: LoginRequestForLink) {
        firebaseAuth.sendSignInLinkToEmail(loginRequest.email, actionCodeSettings = ActionCodeSettings(url = "https://macao-sdui-app-30-default-rtdb.firebaseio.com/", canHandleCodeInApp = true))
    }

    private fun userExistsInDatabase(email: String, password: String): Boolean {
        return firebaseAuth.currentUser!!.equals(email) && firebaseAuth.currentUser!!.isEmailVerified
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