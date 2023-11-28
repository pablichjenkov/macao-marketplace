package com.macaosoftware.sdui.app.marketplace.amadeus.ui.screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
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
import io.kamel.core.Resource
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun PopularList(
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
            text = "Popular Destination",
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
    LazyRow(state = rememberLazyListState(),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(hotelListings) { hotelDetails ->
            PopularDestinationItem(hotelDetails)
        }
    }
}

@Composable
fun PopularDestinationItem(
    hotelListing: HotelListing,
) {
    val navigator = LocalNavigator.current
    Row(
        modifier = Modifier
            .shadow(
                elevation = 8.dp,
                spotColor = Color(0x0A121212),
                ambientColor = Color(0x0A121212)
            )
            .hoverable(interactionSource = MutableInteractionSource(),enabled = true)
            .pointerHoverIcon(icon = PointerIcon.Hand)
            .width(250.dp)  // Adjust the width as needed
            .height(80.dp)  // Adjust the height as needed
            .clickable {
                navigator!!.push(HotelDetailsScreen(hotelListing, IMAGE))
            }
            .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 8.dp))
            .padding(end = 8.dp) // Adjust the end padding as needed
    ) {
        // Image
        val image: Resource<Painter> = asyncPainterResource(data = IMAGE)
        KamelImage(
            resource = image,
            contentDescription = null,
            modifier = Modifier
                .width(80.dp)   // Adjust the width as needed
                .height(80.dp)  // Adjust the height as needed
                .clip(
                    shape = RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp)
                ),
            contentScale = ContentScale.FillBounds,
        )

        // Spacer
        Spacer(modifier = Modifier.width(8.dp))  // Adjust the width as needed

        // Content Column
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(
                    start = 8.dp,   // Adjust the start padding as needed
                    top = 8.dp,     // Adjust the top padding as needed
                    end = 8.dp,     // Adjust the end padding as needed
                    bottom = 8.dp    // Adjust the bottom padding as needed
                )
        ) {
            // Title & Price
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 2.dp) // Adjust end padding as needed
            ) {
                Text(
                    modifier = Modifier.weight(0.65f),
                    text = hotelListing.name.toString(),
                    style = TextStyle(
                        fontSize = 12.sp, // Adjust the font size as needed
                        lineHeight = 18.sp,
                        fontWeight = FontWeight(700),
                        color = Color(0xFF101010),
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )

                Text(
                    text = "$165.3 /night",
                    style = TextStyle(
                        fontSize = 12.sp, // Adjust the font size as needed
                        lineHeight = 18.sp,
                        fontWeight = FontWeight(700),
                        color = Color(0xFF4C4DDC),
                    )
                )
            }

            // Address
            Text(
                text = "Wilora NT 0872, Australia",
                style = TextStyle(
                    fontSize = 10.sp, // Adjust the font size as needed
                    lineHeight = 15.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF878787),
                ),
                modifier = Modifier.padding(top = 2.dp) // Adjust top padding as needed
            )

            // Rating
            Row(
                horizontalArrangement = Arrangement.spacedBy(2.dp), // Adjust the spacing as needed
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp) // Adjust top padding as needed
            ) {
                repeat(5) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = Color(0XFFFFD33C)
                    )
                }
                Text(
                    text = "5.0",
                    style = TextStyle(
                        fontSize = 8.sp, // Adjust the font size as needed
                        lineHeight = 12.sp,
                        fontWeight = FontWeight(700),
                        color = Color(0xFF101010),
                    )
                )
            }
        }
    }
}
