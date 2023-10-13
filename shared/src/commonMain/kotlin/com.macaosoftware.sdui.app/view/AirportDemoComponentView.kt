package com.macaosoftware.sdui.app.view

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
import com.macaosoftware.sdui.app.viewmodel.AirportDemoViewModel

val AirportDemoComponentView: @Composable StateComponent<AirportDemoViewModel>.(
    modifier: Modifier,
    viewModel: AirportDemoViewModel
) -> Unit = { modifier: Modifier,
              viewModel: AirportDemoViewModel ->

    BackPressHandler()
    AirportDemoView(modifier, viewModel)
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AirportDemoView(
    modifier: Modifier,
    airportDemoViewModel: AirportDemoViewModel
) {
    Column(
        modifier.fillMaxSize()
    ) {
        Spacer(Modifier.fillMaxWidth().height(24.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Welcome to Amadeus Flight Booking API",
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
        FlowRow(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {
                airportDemoViewModel.getAccessToken()
            }) {
                Text("Get Access Token")
            }
            Button(onClick = {
                airportDemoViewModel.searchAirportByKeyword()
            }) {
                Text("Search Airport")
            }
            Button(onClick = {
                airportDemoViewModel.searchFlightOffersGet()
            }) {
                Text("Search Flight Offers")
            }
            Button(onClick = {
                airportDemoViewModel.confirmFlightOffersGet()
            }) {
                Text("Confirm Flight Offers")
            }
            /*Button(
                onClick = {
                    getFlightDestinations()
                }
            ) {
                Text("Get Flight Destinations")
            }*/
            Button(onClick = {
                airportDemoViewModel.console.value = ""
            }) {
                Text("Clear")
            }
        }
        Text(
            modifier = Modifier.fillMaxSize().padding(8.dp)
                .verticalScroll(rememberScrollState()).background(Color.White),
            text = airportDemoViewModel.console.value
        )
    }
}