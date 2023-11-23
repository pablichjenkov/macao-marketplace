package com.macaosoftware.sdui.app.marketplace.amadeus.ui.screen.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ConnectWithoutContact
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Facebook
import androidx.compose.material.icons.filled.InstallMobile
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.WifiTetheringErrorRounded
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import com.macaosoftware.sdui.app.marketplace.amadeus.ui.screen.components.SocialLink
import com.macaosoftware.sdui.app.marketplace.amadeus.util.Util.PROFILE
import io.kamel.core.Resource
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource

class ProfileScreen() : Screen {
    @Composable
    override fun Content() {
        Column(modifier = Modifier.windowInsetsPadding(WindowInsets.safeDrawing)) {


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
                                text = "My ProfileScreen",
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
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            //Add Content Here
                            val image: Resource<Painter> = asyncPainterResource(data = PROFILE)
                            KamelImage(
                                resource = image,
                                contentDescription = null,
                                modifier = Modifier.size(250.dp)
                                    .clip(shape = RoundedCornerShape(50.dp)),
                                onLoading = { progress ->
                                    CircularProgressIndicator(progress)
                                },
                                onFailure = {
                                    Icon(
                                        imageVector = Icons.Default.WifiTetheringErrorRounded,
                                        contentDescription = null
                                    )
                                },
                                contentAlignment = Alignment.Center,
                                contentScale = ContentScale.Crop
                            )

                            OutlinedButton(onClick = {},
                                modifier = Modifier.fillMaxWidth(0.75f).padding(top = 20.dp),
                                enabled = true,
                                shape = ButtonDefaults.outlinedShape,
                                elevation = ButtonDefaults.buttonElevation(3.dp),
                                colors = ButtonDefaults.outlinedButtonColors(
                                    containerColor = Color.White,
                                    contentColor = MaterialTheme.colorScheme.primary
                                ),
                                border = BorderStroke(2.dp, color = MaterialTheme.colorScheme.primary)
                                ){
                                Text(text = "Edit Profile")
                            }

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
                        modifier = Modifier.fillMaxWidth()
                            .padding(start = 4.dp, top = 4.dp),
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.Start
                    ) {
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "John Doe",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Software Developer",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Gray
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Add more items as needed
                        Text(
                            text = "Location: City, Country",
                            style = MaterialTheme.typography.bodyLarge
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Email: john.doe@example.com",
                            style = MaterialTheme.typography.bodyLarge
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Phone: +1 (555) 123-4567",
                            style = MaterialTheme.typography.bodyLarge
                        )

                        // Social links
                            Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            SocialLink(icon = Icons.Default.Facebook, link = "https://www.facebook.com/")
                            SocialLink(icon = Icons.Default.Email, link = "https://email.com/")
                            SocialLink(icon = Icons.Default.InstallMobile, link = "https://www.instagram.com/")
                        }

                    }
                }
            }

        }
    }
}