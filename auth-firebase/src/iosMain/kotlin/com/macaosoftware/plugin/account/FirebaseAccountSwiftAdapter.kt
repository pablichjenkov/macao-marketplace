package com.macaosoftware.plugin.account

@ObjCName(name = "MacaoFirebaseAccountSwiftAdapter", exact = true)
interface FirebaseAccountSwiftAdapter {

    fun createUserWithEmailAndPassword(
        email: String,
        password: String,
        onResult: (MacaoUser) -> Unit,
        onError: (String) -> Unit
    )

    fun signInWithEmailAndPassword(
        email: String,
        password: String,
        onResult: (MacaoUser) -> Unit,
        onError: (String) -> Unit
    )

    fun signInWithEmailLink(
        email: String,
        magicLink: String,
        onResult: (MacaoUser) -> Unit,
        onError: (String) -> Unit
    )
}
