package com.macaosoftware.sdui.app.marketplace.auth.forget

sealed class ForgotCredentialsViewModelMsg {
    object OnGoBack : ForgotCredentialsViewModelMsg()
    object OnCreateAccountClick : ForgotCredentialsViewModelMsg()
    class OnSuccess() : ForgotCredentialsViewModelMsg()
}