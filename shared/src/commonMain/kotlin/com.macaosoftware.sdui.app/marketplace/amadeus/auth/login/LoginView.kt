package com.macaosoftware.sdui.app.marketplace.amadeus.auth.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import com.macaosoftware.component.viewmodel.StateComponent
import com.macaosoftware.sdui.app.marketplace.amadeus.auth.plugin.AuthRepository
import com.macaosoftware.sdui.app.marketplace.amadeus.auth.plugin.AuthViewModel

val LoginComponentView: @Composable StateComponent<LoginViewModel>.(
    modifier: Modifier,
    loginViewModel: LoginViewModel
) -> Unit = { modifier: Modifier, loginViewModel: LoginViewModel ->
    val authRepository = remember { AuthRepository() }
    val viewModel = remember { AuthViewModel(authRepository) }
    Navigator(LoginScreen(viewModel))
}