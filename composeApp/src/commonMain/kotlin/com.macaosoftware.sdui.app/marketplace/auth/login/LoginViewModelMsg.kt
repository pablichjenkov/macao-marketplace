package com.macaosoftware.sdui.app.marketplace.auth.login

sealed class LoginViewModelMsg {
    object OnLoginWithEmailLinkClick : LoginViewModelMsg()
    object OnCreateAccountClick : LoginViewModelMsg()
    object OnForgotCredentialsClick : LoginViewModelMsg()
    class OnSuccess() : LoginViewModelMsg()
    class OnError() : LoginViewModelMsg()
}