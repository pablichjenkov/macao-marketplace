package com.macaosoftware.sdui.app.marketplace.amadeus.auth

import androidx.compose.runtime.mutableStateOf
import com.macaosoftware.component.core.Component
import com.macaosoftware.component.stack.StackComponent
import com.macaosoftware.component.stack.StackComponentViewModel
import com.macaosoftware.component.stack.StackStatePresenter
import com.macaosoftware.component.viewmodel.StateComponent
import com.macaosoftware.plugin.account.AccountPlugin
import com.macaosoftware.plugin.account.MacaoUser
import com.macaosoftware.plugin.account.UserData
import com.macaosoftware.sdui.app.marketplace.amadeus.auth.login.LoginComponentView
import com.macaosoftware.sdui.app.marketplace.amadeus.auth.login.LoginViewModel
import com.macaosoftware.sdui.app.marketplace.amadeus.auth.login.LoginViewModelFactory
import com.macaosoftware.sdui.app.marketplace.amadeus.auth.signup.SignupComponentView
import com.macaosoftware.sdui.app.marketplace.amadeus.auth.signup.SignupViewModel
import com.macaosoftware.sdui.app.marketplace.amadeus.auth.signup.SignupViewModelFactory
import com.macaosoftware.util.MacaoResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AuthViewModel(
    stackComponent: StackComponent<AuthViewModel>,
    override val stackStatePresenter: StackStatePresenter,
    val accountPlugin: AccountPlugin
) : StackComponentViewModel(stackComponent) {

    val viewModelScope = CoroutineScope(Dispatchers.Default)
    var userData = mutableStateOf<UserData?>(null)

    private var currentComponent: Component? = null

    private val loginComponent = StateComponent<LoginViewModel>(
        viewModelFactory = LoginViewModelFactory(accountPlugin = accountPlugin),
        content = LoginComponentView
    )

    private val signupComponent = StateComponent<SignupViewModel>(
        viewModelFactory = SignupViewModelFactory(accountPlugin = accountPlugin),
        content = SignupComponentView
    )

    override fun onAttach() {
        //authPlugin.initialize()
        stackComponent.navigator.push(loginComponent)
    }

    override fun onStart() {

    }

    override fun onStop() {

    }

    override fun onDetach() {

    }

    override fun onCheckChildForNextUriFragment(deepLinkPathSegment: String): Component? {
        return loginComponent
    }

    override fun onStackTopUpdate(topComponent: Component) {
        currentComponent = topComponent
    }


    fun checkAndFetchUserData() = viewModelScope.launch {
        val result = accountPlugin.checkAndFetchUserData()
        when (result) {
            is MacaoResult.Success -> {
                println("User Data: $result")
                userData.value = result.value
            }

            is MacaoResult.Error -> {
                println("User not logged in: ${result.error}")
                // Handle error
                println("Error: ${result.error}")
            }
        }
    }

    fun fetchUserDataAndHandleResult() {
        viewModelScope.launch {
            val userDataResult = accountPlugin.fetchUserData()

            when (userDataResult) {
                is MacaoResult.Success -> {
                    val userData = userDataResult.value
                    // Handle user data
                    println("User Data: $userData")
                }

                is MacaoResult.Error -> {
                    val error = userDataResult.error
                    // Handle error
                    println("Error: $error")
                }
            }
        }
    }

    fun updateData(currentUser: MacaoUser, updatedUser: MacaoUser) = viewModelScope.launch {
        //val database = Firebase.database("https://macao-sdui-app-30-default-rtdb.firebaseio.com/")
        //val userRef = database.reference().child("Users").child(currentUser.uid)
        //userRef.setValue(updatedUser)
        println("Data Updated Successfully: $updatedUser ")
    }

}
