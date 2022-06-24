package com.example.zaribatodolist.domain.repository


import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseUser

import com.google.firebase.auth.AuthResult

interface AuthRepository {
    suspend fun loginWithGoogle(accessToken: String): Task<AuthResult>?
    fun getGoogleSignInClient() : GoogleSignInClient
    fun signOut()
    fun userSign_ins() : Boolean
}