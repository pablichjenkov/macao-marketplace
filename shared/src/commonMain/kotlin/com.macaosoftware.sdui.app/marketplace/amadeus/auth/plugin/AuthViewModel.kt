package com.macaosoftware.sdui.app.marketplace.amadeus.auth.plugin

//import dev.gitlive.firebase.auth.FirebaseUser
import dev.gitlive.firebase.auth.FirebaseUser
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(private val authRepository: AuthRepository): ViewModel() {

    private val _user = MutableStateFlow<FirebaseUser?>(null)
    val user: StateFlow<FirebaseUser?> = _user

    fun signUp(email: String, password: String) {
        GlobalScope.launch {
            val user = authRepository.signUpWithEmailAndPassword(email, password)
            _user.value = user

        }
    }

    fun login(email: String, password: String) {
        GlobalScope.launch {
            val user = authRepository.loginWithEmailAndPassword(email, password)
            _user.value = user
        }
    }
}