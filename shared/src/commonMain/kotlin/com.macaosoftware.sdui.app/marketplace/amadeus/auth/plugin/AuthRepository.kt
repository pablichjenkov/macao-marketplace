package com.macaosoftware.sdui.app.marketplace.amadeus.auth.plugin

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.auth.FirebaseUser
import dev.gitlive.firebase.auth.auth

class AuthRepository {

    private val auth: FirebaseAuth = Firebase.auth

    suspend fun signUpWithEmailAndPassword(email: String, password: String): FirebaseUser? {
        return try {
            val result = auth.createUserWithEmailAndPassword(email, password)
            result.user
        } catch (e: Exception) {
            null
        }
    }

    suspend fun loginWithEmailAndPassword(email: String, password: String): FirebaseUser? {
        return try {
            val result = auth.signInWithEmailAndPassword(email, password)
            result.user
        } catch (e: Exception) {
            // Handle login failure, e.g., display error message
            null
        }
    }


}