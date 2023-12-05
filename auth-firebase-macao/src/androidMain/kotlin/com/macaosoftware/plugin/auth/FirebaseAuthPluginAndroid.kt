package com.macaosoftware.plugin.auth

import android.util.Log
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.macaosoftware.plugin.AuthPlugin
import com.macaosoftware.plugin.LoginRequest
import com.macaosoftware.plugin.MacaoUser
import com.macaosoftware.plugin.SignupError
import com.macaosoftware.plugin.SignupRequest
import com.macaosoftware.plugin.elseIfNull
import com.macaosoftware.plugin.ifNotNull
import com.macaosoftware.plugin.util.MacaoResult
import java.util.concurrent.Executors

class FirebaseAuthPluginAndroid : AuthPlugin {

    private val TAG = "FirebaseAuthPlugin"
    private val executorService = Executors.newFixedThreadPool(2)

    override fun initialize() {
    }

    override fun signup(signupRequest: SignupRequest) {

        Firebase.auth.createUserWithEmailAndPassword(signupRequest.email, signupRequest.password)
            .addOnCompleteListener(executorService) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "createUserWithEmail:success")
                    Firebase.auth.currentUser.ifNotNull {
                        signupRequest.onResult(
                            MacaoResult.Success(it.toMacaoUser())
                        )
                    }.elseIfNull {
                        Log.d(TAG, "createUserWithEmail:success but unexpected use null")
                        signupRequest.onResult(
                            MacaoResult.Error(
                                SignupError()
                            )
                        )
                    }
                } else {
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    signupRequest.onResult(
                        MacaoResult.Error(
                            SignupError()
                        )
                    )
                }
            }
    }

    override fun login(loginRequest: LoginRequest) {

        Firebase.auth.signInWithEmailAndPassword(loginRequest.email, loginRequest.password)
            .addOnCompleteListener(executorService) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    Firebase.auth.currentUser.ifNotNull {
                        loginRequest.onResult(
                            MacaoResult.Success(it.toMacaoUser())
                        )
                    }.elseIfNull {
                        Log.d(TAG, "createUserWithEmail:success but unexpected use null")
                        loginRequest.onResult(
                            MacaoResult.Error(
                                SignupError()
                            )
                        )
                    }
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    loginRequest.onResult(
                        MacaoResult.Error(
                            SignupError()
                        )
                    )
                }
            }
    }

    private fun FirebaseUser.toMacaoUser(): MacaoUser {
        return MacaoUser(this.email ?: "no_email")
    }
}
