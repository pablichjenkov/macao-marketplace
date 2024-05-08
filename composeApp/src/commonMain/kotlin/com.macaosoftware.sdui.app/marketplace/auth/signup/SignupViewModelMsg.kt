package com.macaosoftware.sdui.app.marketplace.auth.signup

sealed class SignupViewModelMsg {
    object OnGoBack : SignupViewModelMsg()
    class OnSuccess() : SignupViewModelMsg()
}
