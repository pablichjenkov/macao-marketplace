package com.macaosoftware.sdui.app.marketplace.amadeus.ui.screen.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun SimpleButton(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    imageVector: ImageVector? = null,
    enabled: Boolean = true,
    textPadding: PaddingValues = PaddingValues(16.dp),
    shape: Shape = RoundedCornerShape(8.dp),
    containerColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary,
    iconTint: Color = Color.White,
    iconDescription: String? = null
) {
    val buttonColor by animateColorAsState(
        if (enabled) containerColor else MaterialTheme.colorScheme.surface
    )

    Surface(
        modifier = modifier.height(56.dp),
        onClick = onClick,
        shape = shape,
        color = buttonColor,
        enabled = enabled,
        contentColor = contentColor,
    ) {
        Row(
            Modifier.defaultMinSize(
                minWidth = ButtonDefaults.MinWidth,
                minHeight = ButtonDefaults.MinHeight
            ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            imageVector?.let {icon ->
                Icon(
                    modifier = Modifier.size(24.dp).padding(end = 8.dp),
                    imageVector = icon,
                    contentDescription = iconDescription,
                    tint = iconTint
                )
            }
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge.copy(color = contentColor),
                modifier = Modifier.padding(textPadding)
            )

        }
    }
}