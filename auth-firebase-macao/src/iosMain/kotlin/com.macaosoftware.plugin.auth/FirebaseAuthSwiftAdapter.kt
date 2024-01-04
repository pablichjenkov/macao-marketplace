package com.macaosoftware.plugin.auth

import com.macaosoftware.plugin.MacaoUser

interface FirebaseAuthSwiftAdapter {

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