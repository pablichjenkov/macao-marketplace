package com.macaosoftware.viewmodel

import com.macaosoftware.data.repository.Repository
import com.macaosoftware.utils.usecases.CustomerState
import com.macaosoftware.utils.usecases.EphemeralState
import com.macaosoftware.utils.usecases.PaymentIntentState
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {

    //Customer
    private val _customer = MutableStateFlow<CustomerState>(CustomerState.LOADING)
    val customer: StateFlow<CustomerState> = _customer.asStateFlow()

    //Ephemeral
    private val _ephemeral = MutableStateFlow<EphemeralState>(EphemeralState.LOADING)
    val ephemeral: StateFlow<EphemeralState> = _ephemeral.asStateFlow()

    //Ephemeral
    private val _paymentIntent = MutableStateFlow<PaymentIntentState>(PaymentIntentState.LOADING)
    val paymentIntent: StateFlow<PaymentIntentState> = _paymentIntent.asStateFlow()

    fun getCustomer() {
        viewModelScope.launch {
            _customer.value = CustomerState.LOADING
            try {
                val response = repository.getCustomers()
                _customer.value = CustomerState.SUCCESS(response)
            } catch (e: Exception) {
                val error = e.message.toString()
                _customer.value = CustomerState.ERROR(error)
            }
        }
    }

    fun getEphemeral(id: String) {
        viewModelScope.launch {
            _ephemeral.value = EphemeralState.LOADING
            try {
                val response = repository.getEphemeralKey(id)
                _ephemeral.value = EphemeralState.SUCCESS(response)
            } catch (e: Exception) {
                val error = e.message.toString()
                _ephemeral.value = EphemeralState.ERROR(error)
            }
        }
    }

    fun getPaymentIntent(customer: String, amount: Int, currency: String, automatic_payment_methods: Boolean) {
        viewModelScope.launch {
            _paymentIntent.value = PaymentIntentState.LOADING
            try {
                val response =
                    repository.getPaymentIntents(customer, amount, currency, automatic_payment_methods)
                _paymentIntent.value = PaymentIntentState.SUCCESS(response)
            } catch (e: Exception) {
                val error = e.message.toString()
                _paymentIntent.value = PaymentIntentState.ERROR(error)
            }
        }
    }
}