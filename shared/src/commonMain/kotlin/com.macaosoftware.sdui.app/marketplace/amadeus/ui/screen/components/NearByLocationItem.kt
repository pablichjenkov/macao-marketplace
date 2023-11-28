package com.macaosoftware.sdui.app.marketplace.amadeus.ui.screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import com.macaosoftware.sdui.app.marketplace.amadeus.detail.HotelDetailsScreen
import com.macaosoftware.sdui.app.marketplace.amadeus.detail.SeeScreen
import com.macaosoftware.sdui.app.marketplace.amadeus.util.Util.IMAGE
import com.pablichj.incubator.amadeus.endpoint.hotels.model.HotelListing
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Heart
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun NearByLocationList(
    hotelListings: List<HotelListing>,
) {
    val navigator = LocalNavigator.current
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(start = 12.dp, end = 12.dp, top = 25.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Nearby your location",
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight(700),
                color = Color(0xFF101010),
            )
        )

        Text(
            text = "See all",
            style = TextStyle(
                fontSize = 14.sp,
                lineHeight = 21.sp,
                fontWeight = FontWeight(500),
                color = Color(0xFF4C4DDC),
            ),
            modifier = Modifier .hoverable(interactionSource = MutableInteractionSource(),enabled = true)
                .pointerHoverIcon(icon = PointerIcon.Hand).clickable {
                navigator!!.push(SeeScreen(hotelListings))
            }
        )
    }
    LazyRow(
        state = rememberLazyListState(),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.Top
    ) {
        items(hotelListings) { hotelDetails ->
            NearByLocationItem(hotelDetails)
        }
    }
}

@Composable
fun NearByLocationItem(
    hotelListing: HotelListing,
) {
    var isLiked by remember { mutableStateOf(true) }
    val navigator = LocalNavigator.current

    Card(
        modifier = Modifier
            .width(250.dp)  // Adjust the width as needed
            .height(200.dp) // Adjust the height as needed
            .clickable { navigator!!.push(HotelDetailsScreen(hotelListing, IMAGE)) },
        shape = RoundedCornerShape(8.dp), // Adjust the corner radius as needed
        colors = CardDefaults
            .cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
        elevation = CardDefaults.cardElevation(2.dp), // Adjust the elevation as needed
        border = null
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            // Image with heart icon at the top end
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp) // Adjust the height as needed
                    .clickable { navigator!!.push(HotelDetailsScreen(hotelListing, IMAGE)) }
                    .hoverable(interactionSource = MutableInteractionSource(), enabled = true)
                    .pointerHoverIcon(icon = PointerIcon.Hand)
            ) {
                KamelImage(
                    resource = asyncPainterResource(data = IMAGE),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop
                )

                Box(
                    modifier = Modifier
                        .size(30.dp) // Adjust the size as needed
                        .align(Alignment.TopEnd)
                        .padding(4.dp) // Adjust the padding as needed
                        .clickable {
                            isLiked = !isLiked
                        }
                        .background(
                            color = Color(0xFFFFFFFF),
                            shape = CircleShape
                        )
                        .padding(2.dp) // Adjust the padding as needed
                ) {
                    Icon(
                        imageVector = if (isLiked) FontAwesomeIcons.Solid.Heart else Icons.Default.FavoriteBorder,
                        contentDescription = null,
                        tint = if (isLiked) Color.Red else Color.Black
                    )
                }
            }

            // Hotel Details
            Column(
                modifier = Modifier
                    .padding(4.dp) // Adjust the padding as needed
                    .fillMaxWidth()
            ) {
                // Hotel Title
                Text(
                    text = hotelListing.name.toString(),
                    style = TextStyle(
                        fontSize = 12.sp, // Adjust the font size as needed
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF101010),
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(2.dp)) // Adjust the spacing as needed

                // Location
                Text(
                    text = hotelListing.address.toString(),
                    style = TextStyle(
                        fontSize = 10.sp, // Adjust the font size as needed
                        color = Color(0xFF878787),
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(2.dp)) // Adjust the spacing as needed

                // Rating
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(imageVector = Icons.Filled.Star, contentDescription = null)
                    Spacer(modifier = Modifier.width(1.dp)) // Adjust the spacing as needed
                    Text(
                        text = "5.0",
                        style = TextStyle(
                            fontSize = 8.sp, // Adjust the font size as needed
                            color = Color(0xFF101010),
                        )
                    )
                }

                Spacer(modifier = Modifier.height(2.dp)) // Adjust the spacing as needed

                // Rate per night
                Text(
                    text = "$200,7 /night",
                    style = TextStyle(
                        fontSize = 10.sp, // Adjust the font size as needed
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF4C4DDC),
                    )
                )
            }
        }
    }
}

