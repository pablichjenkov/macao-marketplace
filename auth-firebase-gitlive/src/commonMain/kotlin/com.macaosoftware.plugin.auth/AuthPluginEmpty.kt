package com.macaosoftware.plugin.auth

import com.macaosoftware.plugin.AuthPlugin
import com.macaosoftware.plugin.LoginRequest
import com.macaosoftware.plugin.SignupRequest

/**
 * An empty implementation for those platforms that don't have Firebase.
 * */
class AuthPluginGitLive : AuthPlugin {

    override fun initialize() {
        println(" AuthPluginGitLive::initialize() has been called")
    }

    override fun signup(signupRequest: SignupRequest) {
        println(" AuthPluginGitLive::signup() has been called")
    }

    override fun login(loginRequest: LoginRequest) {
        println(" AuthPluginGitLive::login() has been called")
    }

}