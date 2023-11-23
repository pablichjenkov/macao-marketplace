package com.macaosoftware.sdui.app.marketplace.amadeus.ui.screen.travel

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.macaosoftware.sdui.app.marketplace.amadeus.data.model.travel.Travel
import com.macaosoftware.sdui.app.marketplace.amadeus.data.repository.Repository
import com.macaosoftware.sdui.app.marketplace.amadeus.ui.screen.components.TravelRecommendations
import com.macaosoftware.sdui.app.marketplace.amadeus.ui.viewmodel.MainViewModel

class TravelScreen() : Screen {
    @Composable
    override fun Content() {

        val repository = remember { Repository() }
        val viewModel = remember { MainViewModel(repository) }

        var travelState by remember { mutableStateOf<TravelState>(TravelState.Loading) }
        var data by remember { mutableStateOf<Travel?>(null) }

        LaunchedEffect(Unit) {
            viewModel.getTravel()
        }
        travelState = viewModel.travel.collectAsState().value

        Column(modifier = Modifier.windowInsetsPadding(WindowInsets.safeDrawing)) {

            //Soon Will work on this....
            //Text("TravelScreen Recommendations")

            when (travelState) {
                is TravelState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is TravelState.Success -> {
                    val response = (travelState as TravelState.Success).travel
                    data = response
                    TravelRecommendations(data!!)
                }

                is TravelState.Error -> {
                    val error = (travelState as TravelState.Error).error
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
}