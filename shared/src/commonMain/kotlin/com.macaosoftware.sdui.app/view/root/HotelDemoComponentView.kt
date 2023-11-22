package com.macaosoftware.sdui.app.view.root

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.macaosoftware.component.core.BackPressHandler
import com.macaosoftware.component.viewmodel.StateComponent
import com.macaosoftware.sdui.app.viewmodel.HotelDemoViewModel

val HotelDemoComponentView: @Composable StateComponent<HotelDemoViewModel>.(
    modifier: Modifier,
    viewModel: HotelDemoViewModel
) -> Unit = { modifier: Modifier,
              viewModel: HotelDemoViewModel ->

    BackPressHandler()
    HotelDemoView(modifier, viewModel)
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HotelDemoView(
    modifier: Modifier,
    hotelDemoViewModel: HotelDemoViewModel
) {
    Column(
        modifier.fillMaxSize()
    ) {
        Spacer(Modifier.fillMaxWidth().height(24.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Welcome to Amadeus Hotel Booking API",
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
        FlowRow(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {
                hotelDemoViewModel.getAccessToken()
            }) {
                Text("Get Access Token")
            }
            Button(onClick = {
                hotelDemoViewModel.getCitiesByKeyword()
            }) {
                Text("City Search")
            }
            Button(onClick = {
                hotelDemoViewModel.getHotelsByCity()
            }) {
                Text("Get Hotels By City")
            }
            Button(onClick = {
                hotelDemoViewModel.getMultiHotelsOffers()
            }) {
                Text("Get Multi Hotel Offers")
            }
            Button(onClick = {
                if (hotelDemoViewModel.hotelOffers?.size == 0) {
                    hotelDemoViewModel.output("hotelOffers.size == 0. Do a successful hotel offer search before calling this function.")
                    return@Button
                }
                val offers = hotelDemoViewModel.hotelOffers?.get(0)?.offers
                if (offers.isNullOrEmpty()) {
                    hotelDemoViewModel.output("offers.size == 0. This Hotel Offer has zero offers")
                    return@Button
                }
                val offerId = offers[0].id
                hotelDemoViewModel.getFullOfferDetails(offerId)
            }) {
                Text("Get Offer")
            }
            Button(onClick = {
                hotelDemoViewModel.hotelBook()
            }) {
                Text("Book a Hotel")
            }
            Button(onClick = {
                hotelDemoViewModel.console.value = ""
            }) {
                Text("Clear")
            }
        }
        Text(
            modifier = Modifier.fillMaxSize().padding(8.dp)
                .verticalScroll(rememberScrollState()).background(Color.White),
            text = hotelDemoViewModel.console.value
        )
    }
}