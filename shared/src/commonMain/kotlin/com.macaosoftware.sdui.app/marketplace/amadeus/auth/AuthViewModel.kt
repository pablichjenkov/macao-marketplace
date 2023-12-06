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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthViewModel(
    private val authComponent: StateComponent<AuthViewModel>,
    private val authPlugin: AuthPlugin
) : ComponentViewModel() {

    val viewModelScope = CoroutineScope(Dispatchers.Default)

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

    fun loginWithEmailAndLink(
        email: String,
        link: String
    ) = viewModelScope.launch {

        authPlugin.loginEmailAndLink(
            LoginRequestForEmailWithLink(
                email, link
            ) { result ->
                handleLoginResult(result)
            }
        )
    }

    fun sendEmailLink(email: String) = viewModelScope.launch {
        authPlugin.sendEmailLink(
            LoginRequestForLink(email) { result ->
                handleLoginResult(result)
            }
        )
    }

    fun login(email: String, password: String) = viewModelScope.launch {
        val result = authPlugin.login(LoginRequest(email, password))
        handleLoginResult(result)
    }

    fun signup(
        email: String,
        password: String,
        username: String,
        phoneNo: String
    ) = viewModelScope.launch {
        val result = authPlugin.signup(SignupRequest(email, password, username, phoneNo) )
        handleSignupResult(result)
    }

    private fun handleLoginResult(result: MacaoResult<MacaoUser>) {
        when(result) {
            is MacaoResult.Error -> {
                val loginError = result.error
                println("Login Failed: $loginError")
                /*loadingState = false
                showMessage = true
                messageText = "Login successful!"*/
            }
            is MacaoResult.Success -> {
                val macaoUser = result.value
                println("Login Successful: $macaoUser")
                /*loadingState = false
                showMessage = true
                messageText = "Error While Login..."*/
            }
        }
    }

    private fun handleSignupResult(result: MacaoResult<MacaoUser>) {
        when(result) {
            is MacaoResult.Error -> {
                val signupError = result.error
                println("Signup Failed: $signupError")
                /*loadingState = false
                showMessage = true
                messageText = "Sign up failed: ${e.message}"*/
            }
            is MacaoResult.Success -> {
                val macaoUser = result.value
                println("Signup Successful: $macaoUser")

                storeData(macaoUser)
                /*loadingState = false
                showMessage = true
                messageText = "Sign up successful!"*/
            }
        }
    }

    fun storeData(user: MacaoUser) = viewModelScope.launch {
        //val firebaseUser = Firebase.auth.currentUser?.uid
        //val database = Firebase.database("https://macao-sdui-app-30-default-rtdb.firebaseio.com/")
        //database.reference().child("Users").child("$firebaseUser").setValue(user)
        println("Data Store Successfully: $user ")
    }

    fun updateData(currentUser: User, updatedUser: User) = viewModelScope.launch {
        //val database = Firebase.database("https://macao-sdui-app-30-default-rtdb.firebaseio.com/")
        //val userRef = database.reference().child("Users").child(currentUser.uid)
        //userRef.setValue(updatedUser)
        println("Data Updated Successfully: $updatedUser ")
    }

    fun resetPassword(email: String) = viewModelScope.launch {
        //val firebase = Firebase.auth
        //firebase.sendPasswordResetEmail(email)
        println("Reset Email Sent")
    }
}
