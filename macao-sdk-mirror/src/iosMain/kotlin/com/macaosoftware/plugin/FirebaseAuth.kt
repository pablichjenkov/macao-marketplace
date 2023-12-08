package com.macaosoftware.plugin

interface FirebaseAuth {

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