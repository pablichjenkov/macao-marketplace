package com.macaosoftware.sdui.app.marketplace.amadeus.ui.screen.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun SimpleSnackBar(
    icon: ImageVector?,
    description: String,
    modifier: Modifier = Modifier,
    isVisible: Boolean = true,
    iconTint: Color = MaterialTheme.colorScheme.primary,
    iconBackgroundColor: Color = MaterialTheme.colorScheme.primaryContainer,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    content: @Composable () -> Unit
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = slideInVertically { it },
        exit = slideOutVertically { it },
        modifier = Modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 4.dp, vertical = 4.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(color = backgroundColor)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            icon?.let { icons ->
                Icon(
                    modifier = Modifier.background(
                        color = iconBackgroundColor,
                        shape = RoundedCornerShape(12.dp)
                    ).padding(8.dp),
                    imageVector = icons,
                    contentDescription = description,
                    tint = iconTint
                )
            }
            content()
        }
    }
}