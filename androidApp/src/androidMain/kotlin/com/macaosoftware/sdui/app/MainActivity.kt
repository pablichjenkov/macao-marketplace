package com.macaosoftware.sdui.app

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.macaosoftware.data.repository.Repository
import com.macaosoftware.plugin.MacaoApplicationState
import com.macaosoftware.utils.Constant.PUBLISHER_KEY
import com.macaosoftware.utils.usecases.CustomerState
import com.macaosoftware.utils.usecases.EphemeralState
import com.macaosoftware.utils.usecases.PaymentIntentState
import com.macaosoftware.viewmodel.MainViewModel
import com.stripe.android.PaymentConfiguration
import com.stripe.android.paymentsheet.PaymentSheet
import com.stripe.android.paymentsheet.PaymentSheetResult
import kotlinx.coroutines.Dispatchers

class MainActivity : ComponentActivity() {
    lateinit var paymentSheet: PaymentSheet
    var clientSecret: String? = null
    var customerId: String? = null
    var ephemeralKey: String? = null

    private val rootComponentProvider = AndroidRootComponentProvider(
        this@MainActivity,
    )
    val macaoApplicationState = MacaoApplicationState(
        Dispatchers.IO,
        rootComponentProvider
    )

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        paymentSheet = PaymentSheet(activity = this, callback = ::onPaymentSheetResult)
        PaymentConfiguration.init(applicationContext, PUBLISHER_KEY)
        setContent {
            val repository = remember { Repository() }
            val viewModel = remember { MainViewModel(repository) }
            GetCustomerId(viewModel)
            AndroidMacaoApplication(
                onBackPress = { finish() },
                macaoApplicationState = macaoApplicationState
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = "Example of Android Splash Screen",
                    )
                    Button(onClick = {
                        paymentFlow()
                    }) {
                        Text("Pay Now")
                    }
                }
            }
        }
    }

    @Composable
    private fun GetCustomerId(viewModel: MainViewModel) {
        var customerState by remember { mutableStateOf<CustomerState>(CustomerState.LOADING) }
        LaunchedEffect(Unit) {
            viewModel.getCustomer()
        }
        customerState = viewModel.customer.collectAsState().value
        when (customerState) {
            is CustomerState.LOADING -> {
                CircularProgressIndicator()
            }

            is CustomerState.SUCCESS -> {
                val response = (customerState as CustomerState.SUCCESS).customer
                customerId = response.id
                response.id?.let { GetEphemeral(it, viewModel) }
                Log.e("PaymentFlow", "$customerId and ${response.id}")
                Log.e("PaymentFlow", "$response")

            }

            is CustomerState.ERROR -> {
                val error = (customerState as CustomerState.ERROR).error
                SelectionContainer {
                    Text(text = error)
                }
            }
        }
    }

    @Composable
    private fun GetEphemeral(id: String, viewModel: MainViewModel) {
        var ephemeralState by remember { mutableStateOf<EphemeralState>(EphemeralState.LOADING) }
        LaunchedEffect(Unit) {
            viewModel.getEphemeral(id)
        }
        ephemeralState = viewModel.ephemeral.collectAsState().value
        when (ephemeralState) {
            is EphemeralState.LOADING -> {
                CircularProgressIndicator()
            }

            is EphemeralState.SUCCESS -> {
                val response = (ephemeralState as EphemeralState.SUCCESS).ephemeral
                ephemeralKey = response.id
                response.id?.let { GetPaymentIntent(id, it, viewModel) }
                Log.e("PaymentFlow", "$customerId and ephemeral ${response.id}")

            }

            is EphemeralState.ERROR -> {
                val error = (ephemeralState as EphemeralState.ERROR).error
                Text(text = error)
            }
        }
    }

    @Composable
    private fun GetPaymentIntent(id: String, ephemeralId: String, viewModel: MainViewModel) {
        var paymentIntentState by remember { mutableStateOf<PaymentIntentState>(PaymentIntentState.LOADING) }
        LaunchedEffect(Unit) {
            viewModel.getPaymentIntent(
                amount = 10000,
                currency = "usd",
                automatic_payment_methods = true,
                customer = id
            )
        }
        paymentIntentState = viewModel.paymentIntent.collectAsState().value
        when (paymentIntentState) {
            is PaymentIntentState.LOADING -> {
                CircularProgressIndicator()
            }

            is PaymentIntentState.SUCCESS -> {
                val response = (paymentIntentState as PaymentIntentState.SUCCESS).paymentIntents
                clientSecret = response.clientSecret
                Toast.makeText(this, "Proceed for Payment ${response.id}", Toast.LENGTH_LONG).show()
                Log.e("PaymentFlow", "$customerId and secret ${response.clientSecret}")
            }

            is PaymentIntentState.ERROR -> {
                val error = (paymentIntentState as PaymentIntentState.ERROR).error
                SelectionContainer {
                    Text(text = error)
                }
            }
        }
    }

    private fun paymentFlow() {
        if (customerId != null && ephemeralKey != null && clientSecret != null) {
            Log.d(
                "PaymentFlow",
                "Starting payment flow with customerId: $customerId, ephemeralKey: $ephemeralKey, clientSecret: $clientSecret"
            )
            paymentSheet.presentWithPaymentIntent(
                clientSecret!!,
                PaymentSheet.Configuration(
                    merchantDisplayName = "Macao Software",
                    customer = PaymentSheet.CustomerConfiguration(
                        id = customerId!!,
                        ephemeralKeySecret = ephemeralKey!!
                    )
                )
            )
        } else {
            Log.e("PaymentFlow", "$customerId, $ephemeralKey, or $clientSecret is null")
            Toast.makeText(this, "Payment Flow Error", Toast.LENGTH_LONG).show()
        }
    }


    fun onPaymentSheetResult(paymentSheetResult: PaymentSheetResult) {
        when (paymentSheetResult) {
            is PaymentSheetResult.Completed -> {
                Toast.makeText(this, "Payment Done", Toast.LENGTH_LONG).show()
            }

            is PaymentSheetResult.Canceled -> {
                Toast.makeText(this, "Payment Canceled", Toast.LENGTH_LONG).show()
            }

            is PaymentSheetResult.Failed -> {
                val errorMessage = paymentSheetResult.error.localizedMessage
                Toast.makeText(this, "Payment Failed: $errorMessage", Toast.LENGTH_LONG).show()
            }
        }
    }
}

fun customColors(): PaymentSheet.Colors {
    return PaymentSheet.Colors(
        primary = Color(0xFF00FF00), // Customize primary color
        surface = Color(0xFF0000FF), // Customize surface color
        componentBorder = Color(0xFFFF00FF), // Customize component border color
        component = Color(0xFF00FFFF), // Customize component color
        componentDivider = Color(0xFFFF00FF), // Customize component divider color
        subtitle = Color(0xFF0000FF), // Customize subtitle color
        placeholderText = Color(0xFFFFFF00), // Customize placeholder text color
        onComponent = Color(0xFF333333), // Customize on component color
        onSurface = Color(0xFFFF00FF), // Customize on surface color
        appBarIcon = Color(0xFFFF00FF), // Customize app bar icon color
        error = Color(0xFFFFFF00) // Customize error color
    )
}
fun customPrimaryButtonColors(): PaymentSheet.PrimaryButtonColors {
    return PaymentSheet.PrimaryButtonColors(
        background  = Color(0xFF00FF00), // Customize enabled state color
        onBackground = Color(0xFF808080), // Customize disabled state color
        border = Color(0xFF008000), // Customize pressed state color
    )
}


