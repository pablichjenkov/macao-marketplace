package com.macaosoftware.sdui.app.marketplace.amadeus.auth

sealed class AuthState {
    object Loading : AuthState()
    data class Success(val message: String) : AuthState()
    data class Error(val errorMessage: String) : AuthState()
}