package com.macaosoftware.sdui.app.marketplace.amadeus.ui.screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun IconWithBorder(
    icon: ImageVector
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
                imageVector = icon,
                contentDescription = null
            )
        }
    }

}