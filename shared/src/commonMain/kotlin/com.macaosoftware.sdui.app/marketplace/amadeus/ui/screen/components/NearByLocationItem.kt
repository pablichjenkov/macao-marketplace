package com.macaosoftware.sdui.app.marketplace.amadeus.ui.screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import com.macaosoftware.sdui.app.marketplace.amadeus.util.Util.IMAGE
import com.pablichj.incubator.amadeus.endpoint.hotels.model.HotelListing
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Heart
import io.kamel.core.Resource
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun NearByLocationList(
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
            )
        )
    }
    LazyRow(state = rememberLazyListState(),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.Top
    ) {
        items(hotelListings) { hotelDetails ->
            NearByLocationItem(hotelDetails, onItemClick)
        }
    }
}

@Composable
fun NearByLocationItem(
    hotelListing: HotelListing,
    onClick: (HotelListing) -> Unit
) {
    var isLiked by remember { mutableStateOf(true) }
    //val navigator = LocalNavigator.current

    Card(
        modifier = Modifier
            .width(367.dp)
            .height(313.dp)
            .clickable { onClick(hotelListing) },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults
            .cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
        elevation = CardDefaults.cardElevation(4.dp),
        border = null
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {

            /*Place Holder Image For Now
            * Will use the original Image With This
            * */
            val image: Resource<Painter> = asyncPainterResource(data = IMAGE)
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                KamelImage(
                    resource = image,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(209.dp),
                    contentScale = ContentScale.Crop
                )

                // Box with heart icon at the top end
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .width(52.dp)
                        .height(52.dp)
                        .padding(12.dp)
                        .clickable {
                            isLiked = !isLiked
                        }
                        .background(
                            color = Color(0xFFFFFFFF),
                            shape = RoundedCornerShape(size = 100.dp)
                        )
                        .padding(start = 6.dp, top = 6.dp, end = 6.dp, bottom = 6.dp)
                ) {
                    Icon(
                        // modifier = Modifier.align(alignment = Alignment.TopEnd),
                        imageVector = if (isLiked) FontAwesomeIcons.Solid.Heart else Icons.Default.FavoriteBorder,
                        contentDescription = null,
                        tint = if (isLiked) Color.Red else Color.Black
                    )
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {

                    //Hotel Title
                    Row(modifier = Modifier.fillMaxWidth()) {
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
                        )
                        Spacer(modifier = Modifier.width(50.dp))
                        Icon(imageVector = Icons.Filled.Star, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
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
                    Spacer(modifier = Modifier.width(8.dp))

                    //Location
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = hotelListing.address.toString(),
                            style = TextStyle(
                                fontSize = 12.sp,
                                lineHeight = 18.sp,
                                fontWeight = FontWeight(500),
                                color = Color(0xFF878787),
                            )
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    //Rate
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            text = "$200,7 /night",
                            style = TextStyle(
                                fontSize = 14.sp,
                                lineHeight = 21.sp,
                                fontWeight = FontWeight(700),
                                color = Color(0xFF4C4DDC),
                            )
                        )
                    }

                }
            }
        }
    }
}
