package com.macaosoftware.sdui.app.marketplace.amadeus.auth.forget

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import com.macaosoftware.component.viewmodel.StateComponent

val ForgetComponentView: @Composable StateComponent<ForgetViewModel>.(
    modifier: Modifier,
    forgetViewModel: ForgetViewModel
) -> Unit = { modifier: Modifier, forgetViewModel: ForgetViewModel ->
    Navigator(ForgetScreen())
}