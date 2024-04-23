package com.macaosoftware.sdui.app.marketplace.settings.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.macaosoftware.component.viewmodel.StateComponent
import com.pablichj.incubator.amadeus.endpoint.offers.hotel.model.HotelOfferSearch
import kotlinx.coroutines.launch

@Composable
fun CustomItem(hotel: HotelOfferSearch.Hotel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(text = "Hotel: ${hotel.name}")
            Text(text = "Address : ${hotel.address}")
            Text(text = "Type: ${hotel.type}")
            Text(text = "City Code: ${hotel.cityCode}")
        }
    }
}

val HomeComponentView: @Composable StateComponent<HomeViewModel>.(
    modifier: Modifier,
    homeViewModel: HomeViewModel
) -> Unit = { modifier: Modifier, homeViewModel: HomeViewModel ->
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Home")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            if (drawerState.isClosed) drawerState.open() else drawerState
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Navigation Menu"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues.calculateTopPadding()),
            contentAlignment = Alignment.Center
        ) {
            // Display welcome message
            Text("Welcome to Home Screen (Whole Data Will be displayed inside the List...)")

            // Display hotel offers if available
            homeViewModel.hotelOffers?.let { offers ->
                // Use CustomItem composable to display individual hotel items
                offers.forEach { hotel ->
                    CustomItem(hotel = hotel.hotel)
                }
            }
        }
    }
}
