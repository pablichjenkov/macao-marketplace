package com.macaosoftware.sdui.app.marketplace.amadeus.auth.signup

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.macaosoftware.plugin.SignupRequest
import com.macaosoftware.sdui.app.marketplace.amadeus.auth.AuthViewModel
import com.macaosoftware.sdui.app.marketplace.amadeus.auth.login.LoginScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SignUpScreen(
    private val authViewModel: AuthViewModel,
) : Screen {
    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    override fun Content() {
        var username by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var phone by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var confirmPassword by remember { mutableStateOf("") }
        var isError by remember { mutableStateOf(false) }
        var showMessage by remember { mutableStateOf(false) }
        var messageText by remember { mutableStateOf("") }
        var loadingState by remember { mutableStateOf(false) }
        var passwordVisibility by remember { mutableStateOf(false) }
        var confirmPasswordVisibility by remember { mutableStateOf(false) }
        val navigator = LocalNavigator.current
        val keyboardController = LocalSoftwareKeyboardController.current
        val coroutineScope = rememberCoroutineScope()
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                // Username TextField with Clear Text Button
                OutlinedTextField(
                    value = username,
                    onValueChange = {
                        username = it
                        isError = false
                    },
                    label = { Text("Username") },
                    trailingIcon = {
                        ClearTextButton(username) { username = "" }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    isError = isError
                )

                // Email TextField with Clear Text Button
                OutlinedTextField(
                    value = email,
                    onValueChange = {
                        email = it
                        isError = false
                    },
                    label = { Text("Email") },
                    trailingIcon = {
                        ClearTextButton(email) { email = "" }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    isError = isError
                )

                // Phone TextField with Clear Text Button
                OutlinedTextField(
                    value = phone,
                    onValueChange = {
                        phone = it
                        isError = false
                    },
                    label = { Text("Phone No") },
                    trailingIcon = {
                        ClearTextButton(phone) { phone = "" }
                    },
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
                    label = { Text("Confirm Password") },
                    trailingIcon = {
                        IconButton(
                            onClick = { passwordVisibility = !passwordVisibility },
                            content = {
                                Icon(
                                    imageVector = if (passwordVisibility) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                                    contentDescription = null
                                )
                            }
                        )
                    },
                    visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    isError = isError
                )

                // Confirm Password TextField
                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = {
                        confirmPassword = it
                        isError = false
                    },
                    label = { Text("Confirm Password") },
                    trailingIcon = {
                        IconButton(
                            onClick = { confirmPasswordVisibility = !confirmPasswordVisibility },
                            content = {
                                Icon(
                                    imageVector = if (confirmPasswordVisibility) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                                    contentDescription = null
                                )
                            }
                        )
                    },
                    visualTransformation = if (confirmPasswordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    isError = isError
                )

                // Error Text
                if (isError) {
                    Text(
                        text = "Invalid input. Please check your details.",
                        color = Color.Red,
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }

                // Sign Up Button
                Button(
                    onClick = {
                        if (isValidInput(username, email, password, confirmPassword)) {
                            coroutineScope.launch {
                                loadingState = true
                                keyboardController?.hide()

                                val signupRequest = SignupRequest(
                                    email = email,
                                    password = password,
                                    username = username,
                                    phoneNo = phone
                                )

                                // Use the AuthViewModel to perform signup
                                authViewModel.signup(
                                    signupRequest.email,
                                    signupRequest.password,
                                    signupRequest.username,
                                    signupRequest.phoneNo
                                )

                                showMessage = true
                                messageText = "Signup Successful"
                                delay(1000)

                                // Clear the fields after signup
                                username = ""
                                email = ""
                                phone = ""
                                password = ""
                                confirmPassword = ""

                                navigator?.push(LoginScreen(authViewModel))
                            }
                        } else {
                            isError = true
                        }
                    },
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = if (showMessage) messageText else "Sign Up",
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center
                        )
                        AnimatedVisibility(visible = loadingState) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(24.dp),
                                color = Color.White
                            )
                        }
                    }
                }

                // Text for already having an account
                Text(
                    text = "Already have an account? Login instead!",
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable {
                            navigator?.push(LoginScreen(authViewModel))
                        }
                )
            }
        }
    }

    private fun isValidInput(
        username: String,
        email: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        // Perform your sign-up validation logic here
        // For simplicity, just checking if the fields are not empty and passwords match
        return username.isNotEmpty() && email.isNotEmpty() &&
                password.isNotEmpty() && password == confirmPassword
    }
    @Composable
    private fun ClearTextButton(text: String, onClick: () -> Unit) {
        if (text.isNotEmpty()) {
            IconButton(
                onClick = onClick,
                content = {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = null
                    )
                }
            )
        }
    }

}
