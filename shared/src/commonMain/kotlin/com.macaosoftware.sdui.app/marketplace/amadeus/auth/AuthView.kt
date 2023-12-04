package com.macaosoftware.sdui.app.marketplace.amadeus.auth

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import com.macaosoftware.component.viewmodel.StateComponent
import com.macaosoftware.sdui.app.marketplace.amadeus.auth.login.LoginScreen

val AuthComponentView: @Composable StateComponent<AuthViewModel>.(
    modifier: Modifier,
    authViewModel: AuthViewModel
) -> Unit = { modifier: Modifier, authViewModel: AuthViewModel ->
    Navigator(
        LoginScreen(authViewModel)
    )
}