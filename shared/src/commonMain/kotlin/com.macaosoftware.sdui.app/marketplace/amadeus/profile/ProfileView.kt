package com.macaosoftware.sdui.app.marketplace.amadeus.profile

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import com.macaosoftware.component.viewmodel.StateComponent
import com.macaosoftware.sdui.app.marketplace.amadeus.ui.screen.profile.ProfileScreen

val ProfileComponentView: @Composable StateComponent<ProfileViewModel>.(
    modifier: Modifier,
    profileViewModel: ProfileViewModel
) -> Unit = { modifier: Modifier, profileViewModel: ProfileViewModel ->
    Navigator(ProfileScreen())
}