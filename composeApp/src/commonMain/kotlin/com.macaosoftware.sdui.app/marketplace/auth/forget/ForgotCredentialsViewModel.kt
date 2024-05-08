package com.macaosoftware.sdui.app.marketplace.auth.forget

import com.macaosoftware.component.viewmodel.ComponentViewModel
import com.macaosoftware.component.viewmodel.StateComponent
import com.macaosoftware.plugin.account.AccountPlugin
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ForgotCredentialsViewModel(
    private val stateComponent: StateComponent<ForgotCredentialsViewModel>,
    private val accountPlugin: AccountPlugin,
    private val viewModelMessageHandler: (ForgotCredentialsViewModelMsg) -> Unit
) : ComponentViewModel() {

    val viewModelScope = CoroutineScope(Dispatchers.Default)

    override fun onAttach() {
        println("ForgetViewModel -  onAttach() : ")
    }

    override fun onDetach() {
        println("ForgetViewModel -  onDetach() : ")
    }

    override fun onStart() {
        println("ForgetViewModel -  onStart() : ")
    }

    override fun onStop() {
        println("ForgetViewModel -  onStop() : ")
    }

    fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\$")
        return email.trim().matches(emailRegex)
    }

    fun resetPassword(email: String) = viewModelScope.launch {
        //val firebase = Firebase.auth
        //firebase.sendPasswordResetEmail(email)
        println("Reset Email Sent")
        viewModelMessageHandler.invoke(
            ForgotCredentialsViewModelMsg.OnSuccess()
        )
    }
}
