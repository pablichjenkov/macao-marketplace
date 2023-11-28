package com.macaosoftware.sdui.app.marketplace.amadeus.auth.forget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

class ForgetScreen() : Screen {
    @OptIn(ExperimentalResourceApi::class)
    @Composable
    override fun Content() {
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


                Image(
                    painter = painterResource("logo.png"),
                    contentDescription = null,
                    modifier = Modifier.size(250.dp)
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

                // Error Text
                if (isError) {
                    Text(
                        text = "Invalid email address",
                        color = Color.Red,
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }

                // Success Text
                if (isSuccess) {
                    Text(
                        text = "Password reset instructions sent to your email",
                        color = Color.Green,
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }

                // Reset Button
                Button(
                    onClick = {
                        // Perform password reset logic, e.g., send reset email
                        if (isValidEmail(email)) {
                            // For now, let's just print a success message
                            println("Password reset instructions sent to $email")
                            isSuccess = true
                        } else {
                            // Set error flag to display error message
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
        // Perform email validation logic here
        // For simplicity, just checking if the email is not empty
        return email.isNotEmpty()
    }
}
