package com.macaosoftware.sdui.app.marketplace.amadeus.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.macaosoftware.component.core.collectAsStateWithLifecycle
import com.macaosoftware.component.viewmodel.StateComponent

val AmadeusHomeView: @Composable StateComponent<AmadeusHomeViewModel>.(
    modifier: Modifier,
    viewModel: AmadeusHomeViewModel
) -> Unit = { modifier: Modifier, viewModel: AmadeusHomeViewModel ->

    val hotelState by viewModel
        .hotelByCityCode
        .collectAsStateWithLifecycle(this)

    cafe.adriel.voyager.navigator.Navigator(HomeScreen(hotelState))
}
