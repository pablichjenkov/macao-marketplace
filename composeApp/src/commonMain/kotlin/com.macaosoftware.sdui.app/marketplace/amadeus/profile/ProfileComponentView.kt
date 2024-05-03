package com.macaosoftware.sdui.app.marketplace.amadeus.profile

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.macaosoftware.component.viewmodel.StateComponent

val ProfileComponentView: @Composable StateComponent<ProfileViewModel>.(
    modifier: Modifier,
    profileViewModel: ProfileViewModel
) -> Unit = { modifier: Modifier, profileViewModel: ProfileViewModel ->

    ProfileScreen()
}