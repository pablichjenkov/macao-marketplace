package com.macaosoftware.sdui.app.marketplace.amadeus.auth

import com.macaosoftware.component.viewmodel.ComponentViewModel
import com.macaosoftware.component.viewmodel.StateComponent
import com.macaosoftware.plugin.AuthPlugin
import com.macaosoftware.plugin.LoginRequest
import com.macaosoftware.plugin.LoginRequestForEmailWithLink
import com.macaosoftware.plugin.LoginRequestForLink
import com.macaosoftware.plugin.MacaoUser
import com.macaosoftware.plugin.SignupRequest
import com.macaosoftware.plugin.User
import com.macaosoftware.plugin.util.MacaoResult
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseUser
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.database.database

class AuthViewModel(
    private val authComponent: StateComponent<AuthViewModel>,
    private val authPlugin: AuthPlugin
) : ComponentViewModel() {

    override fun onAttach() {
        //Initialize
        //authPlugin.initialize()
    }

    override fun onDetach() {

    }

    override fun onStart() {

    }

    override fun onStop() {

    }

    suspend fun loginWithEmailAndLink(email: String, link: String) {
        authPlugin.loginEmailAndLink(
            LoginRequestForEmailWithLink(
                email, link
            ) { result ->
                handleLoginResult(result)
            }
        )
    }

    suspend fun sendEmailLink(email: String) {
        authPlugin.sendEmailLink(
            LoginRequestForLink(email) { result ->
                handleLoginResult(result)
            }
        )
    }

    suspend fun login(email: String, password: String) {
        if (isValidEmail(email) && isValidPassword(password)) {
            authPlugin.login(
                LoginRequest(email, password) { result ->
                    handleLoginResult(result)
                }
            )
        } else {
            println("Invalid email or password")
        }
    }

    suspend fun signup(email: String, password: String, username: String, phoneNo: String) {
        if (isValidEmail(email) && isValidPassword(password)) {
            authPlugin.signup(
                SignupRequest(email, password, username, phoneNo) { result ->
                    handleSignupResult(result)
                }
            )
        } else {
            println("Invalid email or password")
        }
    }

    private fun handleLoginResult(result: MacaoResult<MacaoUser>) {
        if (result != null) {
            if (result is MacaoResult.Success) {
                val macaoUser = result.value
                println("Login Successful: $macaoUser")
            } else if (result is MacaoResult.Error) {
                val loginError = result.error
                println("Login Failed: $loginError")
            }
        } else {
            println("Login result is null")
        }
    }

    private fun handleSignupResult(result: MacaoResult<MacaoUser>) {
        if (result != null) {
            if (result is MacaoResult.Success) {
                val macaoUser = result.value
                println("Signup Successful: $macaoUser ")
            } else if (result is MacaoResult.Error) {
                val signupError = result.error
                println("Signup Failed: $signupError")
            }
        } else {
            println("Signup result is null")
        }
    }

    suspend fun storeData(user: User) {
        val firebaseUser = Firebase.auth.currentUser?.uid
        val database = Firebase.database("https://macao-sdui-app-30-default-rtdb.firebaseio.com/")
        database.reference().child("Users").child("$firebaseUser").setValue(user)
        println("Data Store Successfully: $user ")
    }

    suspend fun updateData(currentUser: FirebaseUser, updatedUser: User) {
        val database = Firebase.database("https://macao-sdui-app-30-default-rtdb.firebaseio.com/")
        val userRef = database.reference().child("Users").child(currentUser.uid)
        userRef.setValue(updatedUser)
        println("Data Updated Successfully: $updatedUser ")
    }

    suspend fun resetPassword(email: String) {
        val firebase = Firebase.auth
        firebase.sendPasswordResetEmail(email)
        println("Reset Email Sent")
    }

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")
        return emailRegex.matches(email)
    }

    private fun isValidPassword(password: String): Boolean {
        // Placeholder logic for password validation
        // You should replace this with your actual password validation implementation
        return password.length >= 8
    }
}