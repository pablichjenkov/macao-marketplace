package com.macaosoftware.sdui.app.marketplace.amadeus.ui.screen.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun SimpleOutlineButton(
    title: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    icon: ImageVector? = null,
    tintColor: Color = Color.White,
    textStyle: TextStyle = MaterialTheme.typography.titleLarge,
    textPadding: PaddingValues = PaddingValues(16.dp),
    shape: Shape = RoundedCornerShape(24.dp),
    contentColor: Color = MaterialTheme.colorScheme.primary,
    border: BorderStroke = BorderStroke(1.dp, color = MaterialTheme.colorScheme.primary),
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Center
) {
    val buttonBorderColor by animateColorAsState(
        if (enabled) MaterialTheme.colorScheme.primary
        else MaterialTheme.colorScheme.surface
    )

    val buttonContentColor by animateColorAsState(
        if (enabled) contentColor
        else MaterialTheme.colorScheme.surface
    )

    Surface(
        modifier = modifier.height(56.dp),
        onClick = onClick,
        shape = shape,
        enabled = enabled,
        color = Color.Transparent,
        contentColor = buttonContentColor,
        border = BorderStroke(border.width, buttonBorderColor)
    ) {
        Row(
            Modifier
                .defaultMinSize(
                    minWidth = ButtonDefaults.MinWidth,
                    minHeight = ButtonDefaults.MinHeight
                ),
            horizontalArrangement = horizontalArrangement,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            icon?.let { icons ->
                Icon(
                    imageVector = icons,
                    contentDescription = null,
                    tint = tintColor
                )
            }
            Text(
                text = title,
                style = textStyle,
                color = buttonContentColor,
                modifier = Modifier.padding(textPadding)
            )
        }
    }
}