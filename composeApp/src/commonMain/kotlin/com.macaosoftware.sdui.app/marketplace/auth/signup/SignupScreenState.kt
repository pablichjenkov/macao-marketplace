package com.macaosoftware.sdui.app.marketplace.auth.signup

import com.macaosoftware.sdui.app.marketplace.auth.signup.model.SignupForm

sealed class SignupScreenState {
    data class EnteringData(
        val signupForm: SignupForm
    ) : SignupScreenState()

    data object OngoingRequest : SignupScreenState()

    data class PresentResult(
        val success: Boolean
    ) : SignupScreenState()
}