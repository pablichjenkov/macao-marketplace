package com.macaosoftware.plugin.account

/**
 * The real implementation of the interface MacaoFirebaseAuthKmpWrapper can be found in
 * the Swift Package hosted at:
 * https://github.com/pablichjenkov/firebase-kmp/blob/main/FirebaseAuthKmp/Sources/FirebaseAuthKmpWrapperImpl.swift
 * */
@ObjCName(name = "MacaoFirebaseAuthKmpWrapper", exact = true)
interface FirebaseAuthKmpWrapper {

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
