package com.macaosoftware.sdui.app.marketplace.amadeus.schedule

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import com.macaosoftware.sdui.app.marketplace.amadeus.data.model.citycode.Address
import com.macaosoftware.sdui.app.marketplace.amadeus.data.model.citycode.Data
import com.macaosoftware.sdui.app.marketplace.amadeus.data.model.citycode.GeoCode
import com.macaosoftware.sdui.app.marketplace.amadeus.ui.screen.components.CustomBottomSheet
import com.macaosoftware.sdui.app.marketplace.amadeus.util.Util
import io.kamel.core.Resource
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

@Composable
fun MySchedules() {
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(start = 12.dp, end = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "My Schedule",
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
    val data1 = Data(
        address = Address("123 Main St"),
        chainCode = "ABC123",
        dupeId = 1,
        geoCode = GeoCode(12.34, 56.78),
        hotelId = "H123",
        iataCode = "IATA123",
        lastUpdate = "2023-01-01",
        name = "Hotel 1"
    )

    val data2 = Data(
        address = Address("456 Oak St"),
        chainCode = "DEF456",
        dupeId = 2,
        geoCode = GeoCode(23.45, 67.89),
        hotelId = "H456",
        iataCode = "IATA456",
        lastUpdate = "2023-02-01",
        name = "Hotel 2"
    )

    // Creating a list of Data instances
    val dataList = listOf(data1, data2)

    // Printing the list
    dataList.forEach { data ->
        println(data)
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 12.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(dataList) { hotelDetails ->
            repeat(10) {
                MyScheduleItem(hotelDetails)
                Spacer(modifier = Modifier.height(6.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyScheduleItem(data: Data) {
    //val navigator = LocalNavigator.current
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    val shape = ShapeDefaults.Medium
    var visibility by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .shadow(
                elevation = 6.dp,
                spotColor = Color(0x0A121212),
                ambientColor = Color(0x0A121212)
            )
            .width(357.dp)
            .height(108.dp)
            .clickable {
                visibility = !visibility
                // navigator?.push(DetailScreen(data, Util.IMAGE))
            }
            .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 12.dp))
            .padding(
                end = 12.dp,
            )
    ) {
        // Image
        val image: Resource<Painter> = asyncPainterResource(data = Util.IMAGE)
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
                    text = data.name.toString(),
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
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.LocationOn,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = Color(0xFF4C4DDC),
                )

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
            }


            // Rating
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.DateRange,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = Color(0xFF4C4DDC),
                )

                Text(
                    text = "19 October 2022",
                    style = TextStyle(
                        fontSize = 12.sp,
                        lineHeight = 18.sp,
                        fontWeight = FontWeight(500),
                        color = Color(0xFF878787),
                    )
                )
            }

            if (visibility) {
                CustomBottomSheet(
                    onRequestDismiss = { visibility = false },
                    sheetState = sheetState,
                    scope = scope,
                    modifier = Modifier.fillMaxWidth(),
                    containerColor = Color.White,
                    contentColor = Color.LightGray
                )
            }
        }
    }
}