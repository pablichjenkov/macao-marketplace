package com.macaosoftware.sdui.app

import com.macaosoftware.plugin.MacaoUser

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