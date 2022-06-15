package com.example.zaribatodolist.presentation.auth

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.zaribatodolist.data.repository.AuthRepository
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseUser

class AuthViewModel(
    private val repository: AuthRepository) : ViewModel() {
    val userLiveData = MutableLiveData<FirebaseUser>()

    fun getFirebaseUser() {
        userLiveData.value = repository.getFirebaseUser()
    }

    fun firebaseAuthWithGoogle(idToken: String) {
        repository.firebaseAuthWithGoogle(idToken)
    }

    fun getGoogleSignInGoogle(): GoogleSignInClient {
        return repository.googleSignInClient
    }

}