package com.example.zaribatodolist.domain.reposotory

import androidx.lifecycle.MutableLiveData
import com.example.zaribatodolist.data.model.User
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.google.protobuf.Internal

interface AuthRepository {
    fun firebaseAuthWithGoogle(idToken: String, listener: (FirebaseUser?, Boolean) -> Unit)
    fun getGoogleSignInClient() : GoogleSignInClient
}