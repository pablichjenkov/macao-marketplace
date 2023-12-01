package com.macaosoftware.sdui.app.marketplace.amadeus.auth.signup

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import com.macaosoftware.component.viewmodel.StateComponent
import com.macaosoftware.sdui.app.marketplace.amadeus.auth.plugin.AuthRepository
import com.macaosoftware.sdui.app.marketplace.amadeus.auth.plugin.AuthViewModel

val LoginComponentView: @Composable StateComponent<SignupViewModel>.(
    modifier: Modifier,
    signupViewModel: SignupViewModel
) -> Unit = { modifier: Modifier, signupViewModel: SignupViewModel ->
    val authRepository = remember { AuthRepository() }
    val viewModel = remember { AuthViewModel(authRepository) }
    Navigator(SignUpScreen(viewModel))
}