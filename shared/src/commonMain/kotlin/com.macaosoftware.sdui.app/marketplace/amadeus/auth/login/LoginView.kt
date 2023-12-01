package com.macaosoftware.sdui.app.marketplace.amadeus.auth.login

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import com.macaosoftware.component.viewmodel.StateComponent

val LoginComponentView: @Composable StateComponent<LoginViewModel>.(
    modifier: Modifier,
    loginViewModel: LoginViewModel
) -> Unit = { modifier: Modifier, loginViewModel: LoginViewModel ->
    Navigator(
        LoginScreen(loginViewModel)
    )
}