package com.macaosoftware.sdui.app.marketplace.amadeus.ui.screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.macaosoftware.sdui.app.marketplace.amadeus.data.model.travel.Travel

@Composable
fun TravelRecommendations(travel: Travel) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(240.dp)
    ) {
        items(travel.travelData){travelData ->
            TravelRecItems(travelData)
        }
    }

}

@Composable
fun TravelRecItems(travelData: com.macaosoftware.sdui.app.marketplace.amadeus.data.model.travel.Data) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
            .clickable { /* Handle click */ }
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = travelData.name,
                style = MaterialTheme.typography.labelLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "IATA Code: ${travelData.iataCode}",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = Color(0xFFF5A623) // Yellow color for the star
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = travelData.relevance.toString(),
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color(0xFFF5A623)
                )
            }
        }
    }

}