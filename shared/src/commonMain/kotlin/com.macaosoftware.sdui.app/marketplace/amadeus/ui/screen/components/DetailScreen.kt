package com.macaosoftware.sdui.app.marketplace.amadeus.ui.screen.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.macaosoftware.component.core.Component
import com.macaosoftware.sdui.app.marketplace.amadeus.data.model.citycode.Data
import com.macaosoftware.sdui.app.marketplace.amadeus.ui.screen.home.HomeScreen
import io.kamel.core.Resource
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

class DetailScreen(
    private val data: Data,
    private val imageUrl: String
) : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        Column(modifier = Modifier.windowInsetsPadding(WindowInsets.safeDrawing)) {

            // Row containing both the image and the details
            Row(modifier = Modifier.fillMaxSize()) {

                // Left side with image
                Box(modifier = Modifier.weight(0.45f)) {
                    // Image
                    val image: Resource<Painter> = asyncPainterResource(data = imageUrl)
                    KamelImage(
                        resource = image,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )

                    // Back Icon
                    IconButton(onClick = {
                        navigator?.pop()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "ArrowBack",
                            tint = Color.White
                        )
                    }
                }

                // Divider between left and right sides
                Divider(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(2.dp), // Adjust the width of the divider as needed
                    thickness = 2.dp,
                    color = Color.Blue
                )

                // Right side with details
                Box(modifier = Modifier.weight(0.55f)) {

                    // Data.name text
                    Text(
                        text = data.name.toString(),
                        style = TextStyle(
                            fontSize = 22.sp,
                            lineHeight = 21.sp,
                            fontWeight = FontWeight(700),
                        ),
                        maxLines = 1,
                        modifier = Modifier.padding(16.dp)
                    )

                    // Add other details as needed
                }
            }
        }
    }
}
