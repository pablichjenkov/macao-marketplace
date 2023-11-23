package com.macaosoftware.sdui.app.marketplace.amadeus.ui.screen.offers

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalViewConfiguration
import cafe.adriel.voyager.core.screen.Screen
import com.macaosoftware.component.core.collectAsStateWithLifecycle
import com.macaosoftware.sdui.app.marketplace.amadeus.data.repository.Repository
import com.macaosoftware.sdui.app.marketplace.amadeus.domain.usecases.HotelState
import com.macaosoftware.sdui.app.marketplace.amadeus.ui.screen.components.HotelsList
import com.macaosoftware.sdui.app.marketplace.amadeus.ui.viewmodel.MainViewModel

class HotelOffers() : Screen {
    @Composable
    override fun Content() {
        val repository by remember { mutableStateOf(Repository()) }
        val viewModel by remember { mutableStateOf(MainViewModel(repository)) }
        var hotelState by remember { mutableStateOf<HotelOffersState>(HotelOffersState.Loading) }

        LaunchedEffect(Unit) {
            viewModel.getHotels()
        }
        hotelState = viewModel.hotels.collectAsState().value

        Column(modifier = Modifier.windowInsetsPadding(WindowInsets.safeDrawing)) {

            when (hotelState) {
                is HotelOffersState.Loading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }

                is HotelOffersState.Success -> {
                    val response = (hotelState as HotelOffersState.Success).hotels
                    HotelsList(response)
                }

                is HotelOffersState.Error -> {
                    val error = (hotelState as HotelOffersState.Error).error
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = error)
                    }
                }
            }

        }
    }
}