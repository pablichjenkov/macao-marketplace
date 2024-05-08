package com.macaosoftware.sdui.app.marketplace.auth.forget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import com.macaosoftware.component.core.BackPressHandler
import com.macaosoftware.component.viewmodel.StateComponent
import kotlinx.coroutines.launch

val ForgotCredentialsComponentView: @Composable StateComponent<ForgotCredentialsViewModel>.(
    modifier: Modifier,
    forgotCredentialsViewModel: ForgotCredentialsViewModel
) -> Unit = { modifier: Modifier, forgotCredentialsViewModel: ForgotCredentialsViewModel ->

    BackPressHandler()
    ForgotCredentialsScreen(forgotCredentialsViewModel)
}


@Composable
private fun ForgotCredentialsScreen(
    forgotCredentialsViewModel: ForgotCredentialsViewModel
) {

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
                    text = "Password reset instructions sent to your email",
                    color = Color.Green,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(),
                )
            }

            // Reset Button
            Button(
                onClick = {
                    if (forgotCredentialsViewModel.isValidEmail(email)) {
                        coroutineScope.launch {
                            forgotCredentialsViewModel?.resetPassword(email)
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
