package com.macaosoftware.sdui.app.marketplace.amadeus.auth.signup

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import com.macaosoftware.component.viewmodel.StateComponent

val LoginComponentView: @Composable StateComponent<SignupViewModel>.(
    modifier: Modifier,
    signupViewModel: SignupViewModel
) -> Unit = { modifier: Modifier, signupViewModel: SignupViewModel ->
    Navigator(SignUpScreen())
}