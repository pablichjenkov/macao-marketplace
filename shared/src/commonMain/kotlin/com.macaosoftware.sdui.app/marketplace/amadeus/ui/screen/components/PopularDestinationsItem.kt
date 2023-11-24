package com.macaosoftware.sdui.app.marketplace.amadeus.ui.screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.macaosoftware.sdui.app.marketplace.amadeus.util.Util.IMAGE
import com.pablichj.incubator.amadeus.endpoint.hotels.model.HotelListing
import io.kamel.core.Resource
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun PopularList(
    hotelListings: List<HotelListing>,
    onItemClick: (HotelListing) -> Unit
) {
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
            )
        )
    }
    LazyRow(state = rememberLazyListState(),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(hotelListings) { hotelDetails ->
            PopularDestinationItem(hotelDetails, onItemClick)
        }
    }
}

@Composable
fun PopularDestinationItem(
    hotelListing: HotelListing,
    onClick: (HotelListing) -> Unit
) {
    Row(
        modifier = Modifier
            .shadow(
                elevation = 16.dp,
                spotColor = Color(0x0A121212),
                ambientColor = Color(0x0A121212)
            )
            .width(357.dp)
            .height(108.dp)
            .clickable {
                onClick(hotelListing)
            }
            .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 12.dp))
            .padding(end = 12.dp)
    ) {
        // Image
        val image: Resource<Painter> = asyncPainterResource(data = IMAGE)
        KamelImage(
            resource = image,
            contentDescription = null,
            modifier = Modifier
                .width(119.dp)
                .height(108.dp)
                .clip(
                    shape = RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp)
                ),
            contentScale = ContentScale.FillBounds,
        )

        // Spacer
        Spacer(modifier = Modifier.width(10.dp))

        // Content Column
        Column(
            modifier = Modifier.fillMaxWidth()
                .fillMaxHeight()
                .padding(
                    start = 12.dp,
                    top = 12.dp,
                    end = 12.dp,
                    bottom = 12.dp
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
                        fontSize = 14.sp,
                        lineHeight = 21.sp,
                        fontWeight = FontWeight(700),
                        color = Color(0xFF101010),
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )

                Text(
                    text = "$165.3 /night",
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 21.sp,
                        fontWeight = FontWeight(700),
                        color = Color(0xFF4C4DDC),
                    )
                )
            }

            // Address
            Text(
                text = "Wilora NT 0872, Australia",
                style = TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 18.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFF878787),
                ),
                modifier = Modifier.padding(top = 4.dp) // Adjust top padding as needed
            )

            // Rating
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
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
                        fontSize = 12.sp,
                        lineHeight = 18.sp,
                        fontWeight = FontWeight(700),
                        color = Color(0xFF101010),
                    )
                )
            }
        }
    }
}
