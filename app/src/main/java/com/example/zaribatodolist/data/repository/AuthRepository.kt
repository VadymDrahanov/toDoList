package com.example.zaribatodolist.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.zaribatodolist.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase as Firebase

class AuthRepository(private val context: Context) {

    private var auth: FirebaseAuth = Firebase.auth
    private var user : FirebaseUser? = auth.currentUser

    private val _currentUser = MutableLiveData<FirebaseUser>()

    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(context.getString(R.string.default_web_client_id))
        .requestEmail()
        .build()

    val googleSignInClient = GoogleSignIn.getClient(context, gso)

    fun firebaseAuthWithGoogle(idToken: String) : FirebaseUser? {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                 user = auth.currentUser

            }
        }
        return user
    }

    fun getFirebaseUser() : FirebaseUser? {
        return auth.currentUser
    }
}