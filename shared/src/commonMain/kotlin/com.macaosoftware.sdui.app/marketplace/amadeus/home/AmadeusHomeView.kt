package com.macaosoftware.sdui.app.marketplace.amadeus.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.macaosoftware.component.core.BackPressHandler
import com.macaosoftware.component.core.collectAsStateWithLifecycle
import com.macaosoftware.component.viewmodel.StateComponent
import com.macaosoftware.sdui.app.marketplace.amadeus.ui.screen.components.NearByLocationList
import com.macaosoftware.sdui.app.marketplace.amadeus.ui.screen.components.PopularList
import com.pablichj.incubator.amadeus.endpoint.hotels.model.HotelListing
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.SlidersH
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

val AmadeusHomeView: @Composable StateComponent<AmadeusHomeViewModel>.(
    modifier: Modifier,
    viewModel: AmadeusHomeViewModel
) -> Unit = { modifier: Modifier, viewModel: AmadeusHomeViewModel ->

    val hotelState by viewModel
        .hotelByCityCode
        .collectAsStateWithLifecycle(this)

    BackPressHandler()
    AmadeusHomeScreen(hotelState) {
        viewModel.hotelCardClick(it)
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun AmadeusHomeScreen(
    hotelState: HotelState,
    onHotelClick: (HotelListing) -> Unit
) {

    var searchText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),//windowInsetsPadding(WindowInsets.safeDrawing).fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Logo
        Image(
            painter = painterResource("logo.png"),
            contentDescription = null,
            modifier = Modifier.size(150.dp)
        )
        TextField(
            value = searchText,
            onValueChange = { searchText = it },
            Modifier
                .padding(top = 20.dp)
                .border(
                    width = 1.dp,
                    color = Color(0xFFD6D6D6),
                    shape = RoundedCornerShape(size = 8.dp)
                )
                .width(427.dp)
                .wrapContentHeight()
                .align(alignment = Alignment.CenterHorizontally)
                .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 8.dp))
                .padding(start = 12.dp, top = 12.dp, end = 12.dp, bottom = 12.dp),
            enabled = true,
            leadingIcon = {
                IconButton(
                    onClick = {},
                    modifier = Modifier.pointerHoverIcon(icon = PointerIcon.Hand)
                ) {
                    Icon(
                        imageVector = Icons.Default.Search, contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                }
            },
            placeholder = {
                Text(
                    text = "Search Hotel",
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 21.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF878787),
                    )
                )
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                    },
                    modifier = Modifier
                        .width(28.dp)
                        .height(28.dp)
                        .pointerHoverIcon(icon = PointerIcon.Hand)
                        .background(
                            color = Color(0xFF4C4DDC),
                            shape = RoundedCornerShape(size = 8.dp)
                        )
                        .padding(start = 4.dp, top = 4.dp, end = 4.dp, bottom = 4.dp)
                ) {
                    Icon(
                        imageVector = FontAwesomeIcons.Solid.SlidersH,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                unfocusedTextColor = Color(0xFF878787),
                unfocusedLeadingIconColor = Color(0xFF878787),
                focusedContainerColor = Color.White,
                focusedTextColor = Color.DarkGray,
                focusedLeadingIconColor = Color.DarkGray,
                cursorColor = Color.Blue,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
            )
        )

        when (val hotelStateCopy = hotelState) {
            is HotelState.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is HotelState.Success -> {
                NearByLocationList(hotelStateCopy.hotelListings) {
                    onHotelClick(it)
                }

                // Spacer to add some space between the lists
                Spacer(modifier = Modifier.height(16.dp))

                // PopularList
                PopularList(hotelStateCopy.hotelListings) {
                    onHotelClick(it)
                }

                //HotelList(data!!)
                SideEffect {
                    println("Api Response: $hotelStateCopy")
                }
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
                        Icon(
                            imageVector = Icons.Default.Warning,
                            contentDescription = "warning"
                        )
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
