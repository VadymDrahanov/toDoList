package com.example.zaribatodolist.data.repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.zaribatodolist.R

import com.example.zaribatodolist.domain.repository.AuthRepository
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AuthRepositoryImpl(private val context: Context) : AuthRepository {

    private var auth: FirebaseAuth = Firebase.auth

    private val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(context.getString(R.string.default_web_client_id))
        .requestEmail()
        .build()




    override suspend fun loginWithGoogle(accessToken: String): Task<AuthResult>? = try {
        auth.signInWithCredential(
            GoogleAuthProvider.getCredential(
                accessToken, null
            )
        )
    } catch (e: Throwable) {
        Log.e("ERROR_SERVICE", e.toString())
        null
    }


    override fun getGoogleSignInClient() = GoogleSignIn.getClient(context, gso)

    override fun signOut() {
        Firebase.auth.signOut()
    }

    override fun getSignInStatus(): Boolean {
        if(auth.currentUser != null){
            return true
        }else return false
    }

}