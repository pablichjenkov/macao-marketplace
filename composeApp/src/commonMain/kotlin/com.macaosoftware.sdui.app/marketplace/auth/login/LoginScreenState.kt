package com.macaosoftware.sdui.app.marketplace.auth.login

import com.macaosoftware.sdui.app.marketplace.auth.signup.model.SignupForm

sealed class LoginScreenState {
    data class EnteringData(
        val signupForm: SignupForm
    ) : LoginScreenState()

    data object OngoingRequest : LoginScreenState()

    data class PresentResult(
        val success: Boolean
    ) : LoginScreenState()
}