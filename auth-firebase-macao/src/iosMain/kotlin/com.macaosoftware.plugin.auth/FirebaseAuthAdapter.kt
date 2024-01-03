package com.macaosoftware.plugin.auth

interface FirebaseAuthAdapter {

    fun createUser(
        email: String,
        password: String,
        onResult: (MacaoUser) -> Unit
    )

    fun signIn(
        email: String,
        password: String,
        onResult: (Boolean) -> Unit
    )
}