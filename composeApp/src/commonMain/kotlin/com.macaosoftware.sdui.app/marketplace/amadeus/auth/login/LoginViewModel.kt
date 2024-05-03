package com.macaosoftware.sdui.app.marketplace.amadeus.auth.login

import com.macaosoftware.component.viewmodel.ComponentViewModel
import com.macaosoftware.component.viewmodel.StateComponent
import com.macaosoftware.plugin.account.AccountPlugin
import com.macaosoftware.plugin.account.EmailLinkData
import com.macaosoftware.plugin.account.MacaoUser
import com.macaosoftware.plugin.account.SignInRequest
import com.macaosoftware.plugin.account.SignInRequestForEmailLink
import com.macaosoftware.util.MacaoResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginComponent: StateComponent<LoginViewModel>,
    private val accountPlugin: AccountPlugin
) : ComponentViewModel() {

    private val viewModelScope = CoroutineScope(Dispatchers.Main)

    override fun onAttach() {
        println("LoginViewModel -  onAttach() : ")
    }

    override fun onDetach() {
        println("LoginViewModel -  onDetach() : ")
    }

    override fun onStart() {
        println("LoginViewModel -  onStart() : ")
    }

    override fun onStop() {
        println("LoginViewModel -  onStop() : ")
    }

    fun login(
        email: String,
        password: String
    ) = viewModelScope.launch(Dispatchers.Unconfined) {

        val result = accountPlugin.signInWithEmailAndPassword(
            SignInRequest(email, password)
        )
        when (result) {
            is MacaoResult.Success -> {
                println("Login successful!")

                // Navigate to the Profile screen
                // navigator?.push(/*ProfileScreen(authViewModel)*/)
            }

            is MacaoResult.Error -> {
                println("Login failed: ${result.error}")
                // Handle error cases if needed
            }
        }
    }

    fun isValidEmail(email: String): Boolean {
        // Add your email validation logic here
        return email.isNotEmpty() // Placeholder logic
    }

    fun isValidCredentials(username: String, password: String): Boolean {
        println("Validating credentials - username: $username, password: $password")
        return username.isNotEmpty() && password.isNotEmpty()
    }

    fun loginWithEmailAndLink(
        email: String,
        link: String
    ) = viewModelScope.launch {

        accountPlugin.signInWithEmailLink(
            SignInRequestForEmailLink(email, link)
        )
    }

    fun sendEmailLink(email: String) = viewModelScope.launch {
        accountPlugin.sendSignInLinkToEmail(EmailLinkData(email))
    }

    fun onSignupClick() {
        //navigator?.push(SignUpScreen(authViewModel))
    }

    fun onForgetCredentialsClick() {
        // navigator?.push(ForgetScreen(authViewModel))
    }

    fun onLoginWithEmailLinkClick() {
        // navigator?.push(LoginWithEmailLinkScreen(authViewModel))
    }
}
