package com.macaosoftware.sdui.app.marketplace.amadeus.ui.screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.macaosoftware.sdui.app.marketplace.amadeus.data.model.hotels.Hotels

@Composable
fun HotelsList(hotels: Hotels) {
    LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Adaptive(240.dp)) {
        items(hotels.hotelsData) { hotels ->
            HotelsItems(hotels)
        }
    }

}

@Composable
fun HotelsItems(hotelsData: com.macaosoftware.sdui.app.marketplace.amadeus.data.model.hotels.Data) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { /* Handle click on item if needed */ },
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Location Information
            Text(text = hotelsData.name, style = MaterialTheme.typography.headlineSmall)
            Text(
                text = "City: ${hotelsData.address.cityName}, State: ${hotelsData.address.stateCode}",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "Country: ${hotelsData.address.countryCode}",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "IATA Code: ${hotelsData.iataCode}",
                style = MaterialTheme.typography.bodyLarge
            )


            // GeoCode Information
            Text(
                text = "Latitude: ${hotelsData.geoCode.latitude}",
                style = MaterialTheme.typography.bodyLarge
            )

            Text(
                text = "Longitude: ${hotelsData.geoCode.longitude}",
                style = MaterialTheme.typography.bodyLarge
            )

        }
    }
}