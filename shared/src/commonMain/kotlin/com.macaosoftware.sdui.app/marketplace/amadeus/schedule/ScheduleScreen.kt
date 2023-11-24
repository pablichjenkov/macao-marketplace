package com.macaosoftware.sdui.app.marketplace.amadeus.schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen

class ScheduleScreen() : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        Column(modifier = Modifier.fillMaxSize()) {//windowInsetsPadding(WindowInsets.safeDrawing)


            // Row containing both the image and the details
            Row(modifier = Modifier.fillMaxSize()) {

                // Left side with image
                Box(modifier = Modifier.weight(0.45f)) {

                    Column(modifier = Modifier.fillMaxWidth()) {
                        //Custom Top App Bar
                        Row(
                            modifier = Modifier.fillMaxWidth()
                                .padding(start = 4.dp, end = 4.dp, top = 2.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Box(
                                modifier = Modifier
                                    .border(
                                        width = 1.dp,
                                        color = Color(0xFFEDEDED),
                                        shape = RoundedCornerShape(size = 8.dp)
                                    )
                                    .width(40.dp)
                                    .height(40.dp)
                                    .background(
                                        color = Color(0xFFFFFFFF),
                                        shape = RoundedCornerShape(size = 8.dp)
                                    )
                                    .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 8.dp),
                                contentAlignment = Alignment.TopStart,
                            ) {
                                IconButton(
                                    onClick = {},
                                    enabled = true,
                                    modifier = Modifier.clip(
                                        shape = RoundedCornerShape(6.dp)
                                    ),
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.KeyboardArrowLeft,
                                        contentDescription = null
                                    )
                                }
                            }

                            Text(
                                text = "Schedule",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    lineHeight = 24.sp,
                                    fontWeight = FontWeight(700),
                                    color = Color(0xFF101010),
                                )
                            )

                            //Setting
                            Box(
                                modifier = Modifier
                                    .border(
                                        width = 1.dp,
                                        color = Color(0xFFEDEDED),
                                        shape = RoundedCornerShape(size = 8.dp)
                                    )
                                    .width(40.dp)
                                    .height(40.dp)
                                    .background(
                                        color = Color(0xFFFFFFFF),
                                        shape = RoundedCornerShape(size = 8.dp)
                                    )
                                    .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 8.dp),
                                contentAlignment = Alignment.TopStart,
                            ) {
                                IconButton(
                                    onClick = {},
                                    enabled = true,
                                    modifier = Modifier.clip(
                                        shape = RoundedCornerShape(6.dp)
                                    ),
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Settings,
                                        contentDescription = null
                                    )
                                }
                            }
                        }

                        Column(
                            modifier = Modifier.fillMaxWidth().padding(8.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            //Date Content
                            val dateState = rememberDateRangePickerState()
                            DateRangePicker(
                                state = dateState
                            )
                        }
                    }
                }

                // Divider between left and right sides
                Divider(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(2.dp), // Adjust the width of the divider as needed
                    thickness = 2.dp,
                    color = Color.LightGray
                )

                // Right side with details
                Box(modifier = Modifier.weight(0.55f)) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.Start
                    ) {
                        MySchedules()
                    }
                }
            }

        }

    }
}