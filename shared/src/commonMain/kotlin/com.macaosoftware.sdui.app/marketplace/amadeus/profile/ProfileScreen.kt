package com.macaosoftware.sdui.app.marketplace.amadeus.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Facebook
import androidx.compose.material.icons.filled.Gite
import androidx.compose.material.icons.filled.InstallMobile
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.WifiTetheringErrorRounded
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.macaosoftware.plugin.User
import com.macaosoftware.plugin.UserData
import com.macaosoftware.plugin.util.MacaoResult
import com.macaosoftware.sdui.app.marketplace.amadeus.auth.AuthViewModel
import com.macaosoftware.sdui.app.marketplace.amadeus.auth.login.LoginScreen
import com.macaosoftware.sdui.app.marketplace.amadeus.ui.screen.components.SocialLink
import com.macaosoftware.sdui.app.marketplace.amadeus.util.Util.PROFILE
import io.kamel.core.Resource
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ProfileScreen(
    private val authViewModel: AuthViewModel? = null
) : Screen {
    @Composable
    override fun Content() {
        var usersData by remember { mutableStateOf<UserData?>(null) }
        val coroutineScope = rememberCoroutineScope()
        val currentUser =
            User("email@email.com", "123", "username", "305-213-2345")//firebaseUser.currentUser
        val navigator = LocalNavigator.current
        val uriHandler = LocalUriHandler.current
        var editProfile by remember { mutableStateOf(false) }
        var displayName by remember { mutableStateOf("") }
        var country by remember { mutableStateOf("") }
        var phone by remember { mutableStateOf("") }
        var photoUrl by remember { mutableStateOf("") }
        var facebookUrl by remember { mutableStateOf("") }
        var linkedinUrl by remember { mutableStateOf("") }
        var githubUrl by remember { mutableStateOf("") }
        var loading by remember { mutableStateOf(true) }

        LaunchedEffect(true) {
            val userDataResult = authViewModel!!.plugin.fetchUserData()

            when (userDataResult) {
                is MacaoResult.Success -> {
                    usersData = userDataResult.value
                    println("User Data: $usersData")
                }

                is MacaoResult.Error -> {
                    val error = userDataResult.error
                    // Handle error
                    println("Error: ${error}")
                }
            }
        }


        Column(modifier = Modifier.fillMaxSize()) { // Modifier.windowInsetsPadding(WindowInsets.safeDrawing)

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
                                    onClick = {
                                        navigator?.popAll()
                                    },
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
                                    onClick = {
                                        // navigator?.push(SettingsComponentView())
                                    },
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

                            //Edit Profile
                            OutlinedButton(
                                onClick = {
                                    editProfile = !editProfile
                                },
                                modifier = Modifier.fillMaxWidth(0.75f).padding(top = 20.dp),
                                enabled = true,
                                shape = ButtonDefaults.outlinedShape,
                                elevation = ButtonDefaults.buttonElevation(3.dp),
                                colors = ButtonDefaults.outlinedButtonColors(
                                    containerColor = Color.White,
                                    contentColor = MaterialTheme.colorScheme.primary
                                ),
                                border = BorderStroke(
                                    2.dp,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            ) {
                                Text(text = "Edit Profile")
                            }


                            //Logout
                            OutlinedButton(
                                onClick = {
                                    coroutineScope.launch {
                                       val result = authViewModel!!.plugin.logoutUser()
                                        when (result) {
                                            is MacaoResult.Success -> {
                                                navigator?.push(LoginScreen(authViewModel))
                                                println("User Data: $usersData")
                                            }

                                            is MacaoResult.Error -> {

                                                // Handle error
                                                println("Error: ")
                                            }
                                        }
                                    }
                                },
                                modifier = Modifier.fillMaxWidth(0.75f).padding(top = 20.dp),
                                enabled = true,
                                shape = ButtonDefaults.outlinedShape,
                                elevation = ButtonDefaults.buttonElevation(3.dp),
                                colors = ButtonDefaults.outlinedButtonColors(
                                    containerColor = Color.White,
                                    contentColor = MaterialTheme.colorScheme.primary
                                ),
                                border = BorderStroke(
                                    2.dp,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            ) {
                                Text(text = "Logout")
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
                            text = "${usersData?.displayName}",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Software Developer ${usersData?.uid.toString()}",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Gray
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Add more items as needed
                        Text(
                            text = "Location: ${usersData?.country.toString()}",
                            style = MaterialTheme.typography.bodyLarge,

                            )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Email: ${usersData?.email.toString()}",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.clickable {
                                uriHandler.openUri("${usersData?.email}")
                            }
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Phone: ${usersData?.phoneNo}",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.clickable {
                                uriHandler.openUri(usersData?.phoneNo.toString())
                            }
                        )

                        // Social links
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Start,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            SocialLink(
                                icon = Icons.Default.Facebook,
                                link = "${usersData?.facebookLink}"
                            )
                            SocialLink(icon = Icons.Default.Gite, link = "${usersData?.github}")
                            SocialLink(
                                icon = Icons.Default.InstallMobile,
                                link = "${usersData?.linkedIn}"
                            )
                        }

                    }
                    // Edit Profile AlertDialog
                    if (editProfile) {
                        AlertDialog(
                            onDismissRequest = { editProfile = false },
                            title = { Text("Edit Profile") },
                            text = {
                                Column {
                                    OutlinedTextField(
                                        value = displayName,
                                        onValueChange = { displayName = it },
                                        label = { Text("Username") }
                                    )
                                    OutlinedTextField(
                                        value = country,
                                        onValueChange = { country = it },
                                        label = { Text("Country") }
                                    )
                                    OutlinedTextField(
                                        value = phone,
                                        onValueChange = { phone = it },
                                        label = { Text("Phone") }
                                    )
                                    OutlinedTextField(
                                        value = photoUrl,
                                        onValueChange = { photoUrl = it },
                                        label = { Text("PhotoUrl") }
                                    )
                                    OutlinedTextField(
                                        value = facebookUrl,
                                        onValueChange = { facebookUrl = it },
                                        label = { Text("Facebook") }
                                    )
                                    OutlinedTextField(
                                        value = linkedinUrl,
                                        onValueChange = { linkedinUrl = it },
                                        label = { Text("LinedIn") }
                                    )
                                    OutlinedTextField(
                                        value = githubUrl,
                                        onValueChange = { githubUrl = it },
                                        label = { Text("Github") }
                                    )
                                }
                            },
                            confirmButton = {
                                Button(
                                    onClick = {
                                        // Save changes
                                        coroutineScope.launch {
                                            authViewModel!!.plugin.updateFullProfile(
                                                displayName = displayName,
                                                phoneNo = phone,
                                                country = country,
                                                photoUrl = photoUrl,
                                                facebookLink = facebookUrl,
                                                linkedIn = linkedinUrl,
                                                github = githubUrl
                                            )
                                        }

                                        editProfile = false

                                    },
                                    content = { Text("Save") },
                                    colors = ButtonDefaults.buttonColors(
                                        contentColor = MaterialTheme.colorScheme.primary,
                                        containerColor = MaterialTheme.colorScheme.surface
                                    )
                                )
                            },
                            dismissButton = {
                                Button(
                                    onClick = { editProfile = false },
                                    content = { Text("Cancel") },
                                    colors = ButtonDefaults.buttonColors(
                                        contentColor = MaterialTheme.colorScheme.error,
                                        containerColor = MaterialTheme.colorScheme.surface
                                    )
                                )
                            }
                        )
                    }
                }
            }

        }
    }
}