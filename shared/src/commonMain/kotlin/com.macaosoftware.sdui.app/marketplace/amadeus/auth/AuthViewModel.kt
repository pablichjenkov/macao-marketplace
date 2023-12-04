package com.macaosoftware.sdui.app.marketplace.amadeus.auth

import com.macaosoftware.component.viewmodel.ComponentViewModel
import com.macaosoftware.component.viewmodel.StateComponent
import com.macaosoftware.sdui.app.plugin.AuthPlugin
import com.macaosoftware.sdui.app.plugin.LoginRequest
import com.macaosoftware.sdui.app.plugin.MacaoUser
import com.macaosoftware.sdui.app.plugin.SignupRequest
import com.macaosoftware.sdui.app.plugin.User
import com.macaosoftware.sdui.app.util.MacaoResult
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.database.database
import kotlinx.uuid.UUID

class AuthViewModel(
    private val authComponent: StateComponent<AuthViewModel>,
    private val authPlugin: AuthPlugin
) : ComponentViewModel() {

    override fun onAttach() {
        //Initialize
        authPlugin.initialize()
    }

    override fun onDetach() {

    }

    override fun onStart() {

    }

    override fun onStop() {

    }

    suspend fun login(email: String, password: String) {
        authPlugin.login(
            LoginRequest(email, password) { result ->
                handleLoginResult(result)
            }
        )
    }

    suspend fun signup(email: String, password: String, username: String) {
        authPlugin.signup(
            SignupRequest(email, password, username) { result ->
                handleSignupResult(result)
            }
        )
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
                // Signup failed, handle the error
                val signupError = result.error
                println("Signup Failed: $signupError")
            }
        } else {
            // Handle the case where result is null
            println("Signup result is null")
        }
    }

    suspend fun storeData(user: User) {
        val uuid = UUID()
        val database = Firebase.database("https://macao-sdui-app-30-default-rtdb.firebaseio.com/")

        database.reference().child("Users").child("$uuid").setValue(user)
        println("Data Store Successfully: $user ")

    }
}