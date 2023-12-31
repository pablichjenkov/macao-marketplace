package com.macaosoftware.sdui.app.marketplace.amadeus.auth.forget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.macaosoftware.sdui.app.marketplace.amadeus.auth.AuthViewModel
import kotlinx.coroutines.launch

class ForgetScreen(
    private val authViewModel: AuthViewModel? = null
) : Screen {
    @Composable
    override fun Content() {
        val coroutineScope = rememberCoroutineScope()
        val density = LocalDensity.current.density
        val uriHandler = LocalUriHandler.current
        val keyboardController = LocalSoftwareKeyboardController.current

        var email by remember { mutableStateOf("") }
        var isError by remember { mutableStateOf(false) }
        var isSuccess by remember { mutableStateOf(false) }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Email TextField
                OutlinedTextField(
                    value = email,
                    onValueChange = {
                        email = it
                        isError = false
                    },
                    label = { Text("Email") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        imeAction = ImeAction.Done
                    ),
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.MailOutline, contentDescription = null)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    isError = isError
                )

                // Error Text
                if (isError) {
                    Text(
                        text = "Invalid Email",
                        color = Color.Red,
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                    )
                }

                // Success Text
                if (isSuccess) {
                    Text(
                        text ="Password reset instructions sent to your email",
                        color = Color.Green,
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                    )
                }

                // Reset Button
                Button(
                    onClick = {
                        if (isValidEmail(email)) {
                            coroutineScope.launch {
                                authViewModel?.resetPassword(email)
                                isSuccess = true
                                keyboardController?.hide()
                            }
                        } else {
                            isError = true
                        }
                    },
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    Text("Reset Password")
                }
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        val emailRegex = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}\$")
        return email.trim().matches(emailRegex)
    }
}
