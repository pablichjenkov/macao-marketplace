package com.macaosoftware.sdui.app.marketplace.amadeus.auth.signup

import com.macaosoftware.component.viewmodel.ComponentViewModel
import com.macaosoftware.component.viewmodel.StateComponent
import com.macaosoftware.plugin.account.AccountPlugin
import com.macaosoftware.plugin.account.MacaoUser
import com.macaosoftware.plugin.account.SignUpRequest
import com.macaosoftware.util.MacaoResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SignupViewModel(
    private val signupComponent: StateComponent<SignupViewModel>,
    private val accountPlugin: AccountPlugin
) : ComponentViewModel() {

    private val viewModelScope = CoroutineScope(Dispatchers.Default)

    override fun onAttach() {
        println("SignupViewModel -  onAttach() : ")
    }

    override fun onDetach() {
        println("SignupViewModel -  onDetach() : ")
    }

    override fun onStart() {
        println("SignupViewModel -  onStart() : ")
    }

    override fun onStop() {
        println("SignupViewModel -  onStop() : ")
    }

    fun isValidInput(
        username: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        // Perform your sign-up validation logic here
        // For simplicity, just checking if the fields are not empty and passwords match
        return username.isNotEmpty() && email.isNotEmpty() &&
                password.isNotEmpty() && password == confirmPassword
    }

    fun onSignupClick(
        email: String,
        password: String,
        username: String,
        phoneNo: String
    ) = viewModelScope.launch {

        val result = accountPlugin.createUserWithEmailAndPassword(
            SignUpRequest(email, password, username, phoneNo)
        )
        when (result) {
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

    fun onAlreadyHaveAnAccountClick() {
        // navigator!!.push(LoginScreen(authViewModel))
    }

}
