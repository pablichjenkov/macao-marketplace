package com.macaosoftware.sdui.app.marketplace.amadeus.ui.screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import com.macaosoftware.sdui.app.marketplace.amadeus.detail.HotelDetailsScreenWithVoyager
import com.macaosoftware.sdui.app.marketplace.amadeus.util.Util.IMAGE
import com.pablichj.incubator.amadeus.endpoint.hotels.model.HotelListing
import io.kamel.core.Resource
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun SeeAllList(
    hotelListing: List<HotelListing>,
) {

    val rememberedHotelListing by rememberUpdatedState(hotelListing)

    LazyColumn(
        state = rememberLazyListState(),
        contentPadding = PaddingValues(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 4.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(rememberedHotelListing) { hotelDetails ->
            SeeAllItems(hotelDetails)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeeAllItems(
    hotelListing: HotelListing,
) {
    val navigator = LocalNavigator.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        onClick = {
            navigator!!.push(HotelDetailsScreenWithVoyager(hotelListing, IMAGE))
        }
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Image
            val image: Resource<Painter> = asyncPainterResource(data = IMAGE)
            KamelImage(
                resource = image,
                contentDescription = null,
                modifier = Modifier
                    .width(80.dp)
                    .height(120.dp)
                    .clip(
                        shape = RoundedCornerShape(topStart = 8.dp, bottomStart = 8.dp)
                    ),
                contentScale = ContentScale.Crop
            )

            // Spacer
            Spacer(modifier = Modifier.width(8.dp))

            // Content Column
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                // Title & Price
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 2.dp)
                ) {
                    Text(
                        modifier = Modifier.weight(0.65f),
                        text = hotelListing.name.toString(),
                        style = TextStyle(
                            fontSize = 12.sp,
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
                            fontSize = 12.sp,
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
                        fontSize = 10.sp,
                        lineHeight = 15.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF878787),
                    ),
                    modifier = Modifier.padding(top = 2.dp)
                )

                // Rating
                Row(
                    horizontalArrangement = Arrangement.spacedBy(2.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp)
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
                            fontSize = 8.sp,
                            lineHeight = 12.sp,
                            fontWeight = FontWeight(700),
                            color = Color(0xFF101010),
                        )
                    )
                }
            }
        }
    }
}
