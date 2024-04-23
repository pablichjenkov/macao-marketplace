package com.macaosoftware.sdui.app.marketplace.amadeus.ui.screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import macao_sdui_app.composeapp.generated.resources.Res
import macao_sdui_app.composeapp.generated.resources.logo1
import org.jetbrains.compose.resources.painterResource

@Composable
fun CustomLogo(res: String, modifier: Modifier, size: Dp) {
    Image(
        painter = painterResource(Res.drawable.logo1),
        contentDescription = null,
        modifier = modifier.size(size)
    )
}
