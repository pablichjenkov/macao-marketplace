package com.macaosoftware.sdui.app.marketplace.amadeus.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.macaosoftware.component.viewmodel.StateComponent
import com.macaosoftware.sdui.app.marketplace.amadeus.data.model.citycode.CityCodeHotel
import com.macaosoftware.sdui.app.marketplace.amadeus.repository.Repository
import com.macaosoftware.sdui.app.marketplace.amadeus.ui.screen.components.HotelList
import com.macaosoftware.sdui.app.marketplace.amadeus.util.states.HotelState
import com.macaosoftware.sdui.app.marketplace.amadeus.viewmodel.MainViewModel
import com.macaosoftware.sdui.app.viewmodel.amadeus.viewmodel.AHomeScreenViewModel

@Composable
fun HomeScreen() {
    val repository = Repository()
    val viewModel = MainViewModel(repository)

    var hotelState by remember { mutableStateOf<HotelState>(HotelState.Loading) }
    var data by remember { mutableStateOf<CityCodeHotel?>(null) }

    LaunchedEffect(Unit) {
        viewModel.getHotelByCityCode("Isb")
        viewModel.hotelByCityCode.collect() { state ->
            hotelState = state
        }
    }


    Column(modifier = Modifier.windowInsetsPadding(WindowInsets.safeDrawing)) {

        when (hotelState) {
            is HotelState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is HotelState.Success -> {
                val response = (hotelState as HotelState.Success).cityCodeHotel
                data = response
                HotelList(data!!)
                println("Api Response: $data")
            }

            is HotelState.Error -> {
                val error = (hotelState as HotelState.Error).error
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Icon(imageVector = Icons.Default.Warning, contentDescription = "warning")
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = error,
                            fontSize = MaterialTheme.typography.labelLarge.fontSize,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }

    }


}