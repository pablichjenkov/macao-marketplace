package com.macaosoftware.sdui.app.marketplace.amadeus.ui.screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.macaosoftware.sdui.app.marketplace.amadeus.data.model.citycode.CityCodeHotel
import com.macaosoftware.sdui.app.marketplace.amadeus.data.model.citycode.Data

@Composable
fun HotelList(apiResponse: CityCodeHotel) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(apiResponse.dataList) { hotelDetails ->
            HotelCard(hotelDetails)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun HotelCard(hotelDetails: Data) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            hotelDetails.name?.let {
                Text(
                    text = it,
                    style = typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            Text(
                text = "Chain Code: ${hotelDetails.chainCode}",
                style = typography.bodyMedium,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            Text(
                text = "Hotel ID: ${hotelDetails.hotelId}",
                style = typography.bodyMedium,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            Text(
                text = "IATA Code: ${hotelDetails.iataCode}",
                style = typography.bodyMedium,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            Text(
                text = "Dupe ID: ${hotelDetails.dupeId}",
                style = typography.bodyMedium,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            Text(
                text = "Country Code: ${hotelDetails.address?.countryCode}",
                style = typography.bodyMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Book Now Button
                OutlinedButton(
                    onClick = { /* Handle book now click */ },
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 4.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(imageVector = Icons.Default.Send, contentDescription = null)
                        Text("Book Now")
                    }
                }

                // Distance and Warning Icon
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(imageVector = Icons.Default.Place, contentDescription = null)
                    Icon(imageVector = Icons.Default.Warning, contentDescription = null)
                }
            }
        }
    }
}
