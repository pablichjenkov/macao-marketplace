package com.macaosoftware.sdui.app.marketplace.amadeus.schedule

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen

class ScheduleScreen() : Screen {

    @Composable
    override fun Content() {
        Column(modifier = Modifier.fillMaxSize()) {
            // Custom Top App Bar
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(start = 4.dp, end = 4.dp, top = 2.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
//                CustomLogo(
//                    res = "logo.png",
//                    modifier = Modifier,
//                    size = 140.dp
//                )

                Text(
                    text = "Logo Template",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )

                // Navigation and Settings Icons
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    IconButton(
                        onClick = {},
                        enabled = true,
                        modifier = Modifier.clip(shape = RoundedCornerShape(6.dp)),
                    ) {
                        Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = null)
                    }

                    IconButton(
                        onClick = {},
                        enabled = true,
                        modifier = Modifier.clip(shape = RoundedCornerShape(6.dp)),
                    ) {
                        Icon(imageVector = Icons.Default.Settings, contentDescription = null)
                    }
                }
            }

            // Date Content
            val dateState = rememberDateRangePickerState()
            DateRangePicker(
                state = dateState,
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            )

            // Divider between left and right sides
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp), // Adjust the height of the divider as needed
                thickness = 2.dp,
                color = Color.LightGray
            )

            // Right side with details
            Box(modifier = Modifier.weight(0.55f)) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.Start
                ) {
                    MySchedules()
                }
            }
        }
    }
}
