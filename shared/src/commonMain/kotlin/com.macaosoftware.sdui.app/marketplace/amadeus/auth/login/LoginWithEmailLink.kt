package com.macaosoftware.sdui.app.marketplace.amadeus.auth.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.macaosoftware.sdui.app.marketplace.amadeus.auth.AuthViewModel
import kotlinx.coroutines.launch

class LoginWithEmailLinkScreen(
    private val authViewModel: AuthViewModel
) : Screen {
    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    override fun Content() {
        var email by remember { mutableStateOf("") }
        var link by remember { mutableStateOf("") }
        var isError by remember { mutableStateOf(false) }
        var isEmailEntered by remember { mutableStateOf(false) }
        val navigator = LocalNavigator.current
        val keyboardController = LocalSoftwareKeyboardController.current
        val coroutineScope = rememberCoroutineScope()


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Email TextField
            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                    isError = false
                },
                label = { Text("Email") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
                isError = isError,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth(),
            )

            // Link TextField (visible only if email is entered correctly)
            AnimatedVisibility(visible = isEmailEntered) {
                OutlinedTextField(
                    value = link,
                    onValueChange = {
                        link = it
                        isError = false
                    },
                    label = { Text("Link") },
                    isError = isError,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                )
            }

            // Login Button
            Button(
                onClick = {
                    if (!isEmailEntered) {
                        // Check if the email is entered correctly
                        if (isValidEmail(email)) {
                            isEmailEntered = true
                            coroutineScope.launch {
                                authViewModel.sendEmailLink(email)
                            }
                        } else {
                            isError = true
                        }
                    } else {
                        // Check if both email and link are entered correctly
                        if (isValidCredentials(email, link)) {
                            coroutineScope.launch {
                                authViewModel.loginWithEmailAndLink(email, link)
                            }
                        } else {
                            isError = true
                        }
                    }
                },
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                Text("Login with Email Link")
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        // Add your email validation logic here
        return email.isNotEmpty() // Placeholder logic
    }

    private fun isValidCredentials(email: String, link: String): Boolean {
        // Add your validation logic here
        return email.isNotEmpty() && link.isNotEmpty()
    }
}

