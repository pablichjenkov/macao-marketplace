package com.macaosoftware.sdui.app.marketplace.amadeus.auth.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.macaosoftware.sdui.app.marketplace.amadeus.auth.forget.ForgetScreen
import com.macaosoftware.sdui.app.marketplace.amadeus.auth.signup.SignUpScreen
import org.jetbrains.compose.resources.ExperimentalResourceApi

class LoginScreen : Screen {
    @OptIn(ExperimentalResourceApi::class)
    @Composable
    override fun Content() {
        var username by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var isError by remember { mutableStateOf(false) }
        val navigator = LocalNavigator.current
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                //Logo

//                Image(
//                    painter = painterResource("logo.png"),
//                    contentDescription = null,
//                    modifier = Modifier.size(250.dp)
//                )

                Text(
                    text = "Logo Template",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )

                // Username TextField
                OutlinedTextField(
                    value = username,
                    onValueChange = {
                        username = it
                        isError = false
                    },
                    label = { Text("Username") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    isError = isError
                )

                // Password TextField
                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        password = it
                        isError = false
                    },
                    label = { Text("Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    isError = isError
                )

                // Error Text
                if (isError) {
                    Text(
                        text = "Invalid username or password",
                        color = Color.Red,
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }

                // Login Button
                Button(
                    onClick = {
                        // Perform login logic, e.g., validate credentials
                        if (isValidCredentials(username, password)) {
                            // Navigate to the next screen or perform necessary actions
                            // For now, let's just print a success message
                            println("Login successful!")
                        } else {
                            // Set error flag to display error message
                            isError = true
                        }
                    },
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    Text("Login")
                }

                // Text for account not created
                Text(
                    text = "Don't have an account? Create one!",
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable {
                            navigator?.push(SignUpScreen())
                        }
                )

                // Text for forgot password
                Text(
                    text = "Forgot Password?",
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable {
                            navigator?.push(ForgetScreen())
                        }
                )
            }
        }
    }

    private fun isValidCredentials(username: String, password: String): Boolean {
        return username.isNotEmpty() && password.isNotEmpty()
    }
}
