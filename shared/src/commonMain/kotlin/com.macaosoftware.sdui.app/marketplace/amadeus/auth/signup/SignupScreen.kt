package com.macaosoftware.sdui.app.marketplace.amadeus.auth.signup

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.macaosoftware.plugin.MacaoUser
import com.macaosoftware.plugin.SignupRequest
import com.macaosoftware.plugin.User
import com.macaosoftware.plugin.util.MacaoResult
import com.macaosoftware.sdui.app.marketplace.amadeus.auth.AuthViewModel
import com.macaosoftware.sdui.app.marketplace.amadeus.auth.login.LoginScreen
import com.macaosoftware.sdui.app.marketplace.amadeus.auth.login.LoginViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

class SignUpScreen(
    private val authViewModel: AuthViewModel,
) : Screen {
    @OptIn(ExperimentalResourceApi::class, ExperimentalComposeUiApi::class)
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
        val navigator  = LocalNavigator.current
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

                // Email TextField
                OutlinedTextField(
                    value = email,
                    onValueChange = {
                        email = it
                        isError = false
                    },
                    label = { Text("Email") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    isError = isError
                )

                // Phone TextField
                OutlinedTextField(
                    value = phone,
                    onValueChange = {
                        phone = it
                        isError = false
                    },
                    label = { Text("Phone No") },
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

                // Confirm Password TextField
                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = {
                        confirmPassword = it
                        isError = false
                    },
                    label = { Text("Confirm Password") },
                    visualTransformation = PasswordVisualTransformation(),
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
                        if (isValidInput(username, email, password, password, confirmPassword)) {
                            coroutineScope.launch {
                                loadingState = true
                                keyboardController?.hide()
                                try {
                                    val signupRequest = SignupRequest(
                                        email = email,
                                        password = password,
                                        username = username,
                                        phoneNo = phone,
                                        onResult = { result ->
                                            handleSignupResult(result)
                                        }
                                    )
                                    authViewModel.signup(signupRequest.email, signupRequest.password, signupRequest.username, signupRequest.phoneNo)
                                    val user = User(signupRequest.email, signupRequest.password,signupRequest.username,signupRequest.phoneNo)
                                    authViewModel.storeData(user)
                                    delay(2000)
                                    loadingState = false
                                    showMessage = true
                                    messageText = "Sign up successful!"
                                    delay(200)
                                    navigator?.push(LoginScreen(authViewModel))
                                    println("Signup Successful")
                                } catch (e: Exception) {
                                    loadingState = false
                                    showMessage = true
                                    messageText = "Sign up failed: ${e.message}"
                                    println("Signup Failed....}")
                                }
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
                            if (showMessage) messageText else "Sign Up", // Show message or "Sign Up" text
                            modifier = Modifier.weight(1f), // Center the text
                            textAlign = TextAlign.Center // Center the text horizontally
                        )
                        AnimatedVisibility(visible = loadingState) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(24.dp),
                                color = Color.White
                            )
                        }
                    }
                }



                Text(
                    text = "Already have an account? Login instead!",
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable {
                            navigator!!.push(
                                LoginScreen(authViewModel)
                            )
                        }
                )
            }
        }
    }

    private fun handleSignupResult(result: MacaoResult<MacaoUser>) {
        when (result) {
            is MacaoResult.Success -> {
                println("Sign up successful!")
            }

            is MacaoResult.Error -> {
                // Handle signup failure
                println("Sign up failed: ${result.error}")
            }
        }
    }

    private fun isValidInput(
        username: String,
        email: String,
        phone: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        val emailRegex = Regex("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")

        return username.isNotEmpty() &&
                emailRegex.matches(email) &&
                phone.isNotEmpty() &&
                password.length >= 8 && // Password should be 8 characters or above
                password == confirmPassword
    }


}
