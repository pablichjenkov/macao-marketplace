package com.macaosoftware.sdui.app.marketplace.amadeus.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.macaosoftware.component.core.BackPressHandler
import com.macaosoftware.component.viewmodel.StateComponent
import com.macaosoftware.sdui.app.marketplace.amadeus.ui.screen.components.InfoCard
import com.macaosoftware.sdui.app.marketplace.amadeus.ui.screen.components.PriceField
import com.pablichj.incubator.amadeus.endpoint.hotels.model.HotelListing
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.AddressCard
import compose.icons.fontawesomeicons.solid.Coffee
import compose.icons.fontawesomeicons.solid.SearchLocation
import compose.icons.fontawesomeicons.solid.Star
import compose.icons.fontawesomeicons.solid.Wifi
import io.kamel.core.Resource
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

val HotelDetailsView: @Composable StateComponent<HotelDetailsViewModel>.(
    modifier: Modifier,
    viewModel: HotelDetailsViewModel
) -> Unit = { modifier: Modifier, viewModel: HotelDetailsViewModel ->

    BackPressHandler()
    HotelDetailsScreen(
        viewModel.hotelListing,
        viewModel.imageUrl
    ) {
        viewModel.onBackClicked()
    }
}

@Composable
private fun HotelDetailsScreen(
    hotelListing: HotelListing,
    imageUrl: String,
    onBackPressed: () -> Unit
) {

    val image: Resource<Painter> = asyncPainterResource(data = imageUrl)
    Column(
        modifier = Modifier.fillMaxSize()//Modifier.windowInsetsPadding(WindowInsets.safeDrawing)
            .verticalScroll(state = rememberScrollState())
    ) {

        // Row containing both the image and the details
        Row(modifier = Modifier.fillMaxSize()) {

            // Left side with image
            Box(modifier = Modifier.weight(0.45f)) {
                // Image
                KamelImage(
                    resource = image,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // Back Icon
                IconButton(onClick = onBackPressed) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "ArrowBack",
                        tint = Color.White
                    )
                }
            }

            // Divider between left and right sides
            Divider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(2.dp), // Adjust the width of the divider as needed
                thickness = 2.dp,
                color = Color.Blue
            )

            // Right side with details
            Box(modifier = Modifier.weight(0.55f)) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                        .padding(12.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start
                ) {
                    // Data.name text
                    Text(
                        text = hotelListing.name.toString(),
                        style = TextStyle(
                            fontSize = 22.sp,
                            lineHeight = 28.sp, // Increased line height for better readability
                            fontWeight = FontWeight(700),
                        ),
                        maxLines = 1,
                        modifier = Modifier.padding(16.dp)
                    )

                    // Display address with icon
                    hotelListing.address?.let { address ->
                        Row(
                            modifier = Modifier.padding(4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(FontAwesomeIcons.Solid.AddressCard, contentDescription = null, modifier = Modifier.size(20.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = address.toString(), style = MaterialTheme.typography.bodyLarge)
                        }
                    }

                    // Display other details with icons as needed
                    hotelListing.geoCode?.let { geoCode ->
                        Row(
                            modifier = Modifier.padding(4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(FontAwesomeIcons.Solid.SearchLocation, contentDescription = null, modifier = Modifier.size(20.dp))
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Latitude: ${geoCode.latitude}, Longitude: ${geoCode.longitude}",
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        // Wifi
                        InfoCard(icon = FontAwesomeIcons.Solid.Wifi, label = "Free Wifi")

                        // Breakfast
                        InfoCard(icon = FontAwesomeIcons.Solid.Coffee, label = "Free Breakfast")

                        // Rating
                        InfoCard(icon = FontAwesomeIcons.Solid.Star, label = "5.0", tint = Color(0XFFFFD33C))
                    }

                    PriceField(
                        price = "20.99 / Night", // Added "/ Night" to the price
                        modifier = Modifier.padding(bottom = 8.dp),
                        currency = "$"
                    )

                    Text(
                        text = "Description",
                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
                    )

                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Aston Hotel, Alice Springs NT 0870, Australia is a modern hotel. elegant 5 star hotel overlooking the sea. perfect for a romantic, charming Read More. . .",
                        style = MaterialTheme.typography.bodyLarge
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "Preview",
                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
                    )
                    Spacer(modifier = Modifier.height(6.dp))

                    Row(modifier = Modifier.fillMaxWidth()) {
                        repeat(4) {
                            KamelImage(
                                resource = image,
                                contentDescription = null,
                                modifier = Modifier
                                    .width(98.dp)
                                    .height(82.dp)
                                    .clip(shape = RoundedCornerShape(16.dp)),
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                        }
                    }

                    TextButton(
                        onClick = {},
                        modifier = Modifier.fillMaxWidth(),
                        enabled = true,
                        shape = MaterialTheme.shapes.medium,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0XFF4C4DDC),
                            contentColor = Color.White,
                        ),
                    ) {
                        Text(text = "Book Now")
                    }
                    // Add other details as needed
                }
            }
        }
    }
}
