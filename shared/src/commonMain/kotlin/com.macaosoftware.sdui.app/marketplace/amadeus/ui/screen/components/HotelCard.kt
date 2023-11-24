package com.macaosoftware.sdui.app.marketplace.amadeus.ui.screen.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.sharp.Build
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
<<<<<<< HEAD
import com.macaosoftware.sdui.app.marketplace.amadeus.data.model.citycode.CityCodeHotel
import com.macaosoftware.sdui.app.marketplace.amadeus.data.model.citycode.Data
import org.jetbrains.skia.FontFeature
=======
import com.pablichj.incubator.amadeus.endpoint.hotels.model.HotelListing
>>>>>>> origin

@Composable
fun HotelList(hotelListings: List<HotelListing>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(hotelListings) { hotelDetails ->
            HotelCard(hotelDetails)
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
<<<<<<< HEAD
fun HotelCard(hotelDetails: Data) {
=======
fun HotelCard(hotelListing: HotelListing) {
>>>>>>> origin
    var isVisible by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                isVisible = !isVisible
            }
            .background(MaterialTheme.colorScheme.background),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        AnimatedVisibility(visible = isVisible) {
            SimpleSnackBar(
                icon = Icons.Default.Info,
                description = "SnackBar",
                modifier = Modifier,
                isVisible = true,
                iconTint = MaterialTheme.colorScheme.primary,
                iconBackgroundColor = MaterialTheme.colorScheme.surface,
                backgroundColor = MaterialTheme.colorScheme.surface,
                content = {
                    Text(
<<<<<<< HEAD
                        text = hotelDetails.name.toString(),
=======
                        text = hotelListing.name.toString(),
>>>>>>> origin
                    )
                }
            )
        }
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            hotelListing.name?.let {
                Text(
                    text = it,
                    style = typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            Text(
                text = "Chain Code: ${hotelListing.chainCode}",
                style = typography.bodyMedium,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            Text(
                text = "Hotel ID: ${hotelListing.hotelId}",
                style = typography.bodyMedium,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            Text(
                text = "IATA Code: ${hotelListing.iataCode}",
                style = typography.bodyMedium,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            Text(
                text = "Dupe ID: ${hotelListing.dupeId}",
                style = typography.bodyMedium,
                modifier = Modifier.padding(bottom = 4.dp)
            )

            Text(
                text = "Country Code: ${hotelListing.address?.countryCode}",
                style = typography.bodyMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            PriceField(
                price = "20.99",
                modifier = Modifier.padding(bottom = 8.dp),
                currency = "$"
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Book Now Button
                SimpleOutlineButton(
                    title = "Book Now",
                    onClick = {},
                    modifier = Modifier.weight(1f)
                        .padding(end = 4.dp),
                    icon = Icons.Default.Send,
                    tintColor = MaterialTheme.colorScheme.primary,
                    enabled = true,
                )

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
