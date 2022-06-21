package com.example.zaribatodolist.data.repository

import android.content.Context
import android.widget.Toast
import com.example.zaribatodolist.R
import com.example.zaribatodolist.data.model.User
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
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

    override fun firebaseAuthWithGoogle(
        idToken: String,
        listener: (FirebaseUser?, Boolean) -> Unit
    ) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)

        auth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                auth.currentUser?.let { user ->
                    val isNewUser = it.getResult().additionalUserInfo?.isNewUser ?: true
                    listener.invoke(user, isNewUser)
                }
            } else {
                auth.currentUser?.let { user ->
                    listener.invoke(null, false)
                }
            }
        }
    }

    override fun getGoogleSignInClient(): GoogleSignInClient {
        val googleSignInClient = GoogleSignIn.getClient(context, gso)
        return googleSignInClient
    }

}