package com.example.zaribatodolist.data.repository

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.zaribatodolist.R
import com.example.zaribatodolist.data.model.User
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase as Firebase

class AuthRepository(private val context: Context) {

    private var auth: FirebaseAuth = Firebase.auth
    private var firebaseUser : FirebaseUser? = auth.currentUser
    private var db = Firebase.firestore


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
                firebaseUser = auth.currentUser
                val user = User(auth.currentUser?.uid, auth.currentUser?.email,null,  auth.currentUser?.displayName)
                db.collection("users").document(user.uid!!).set(user)
            }
        }
        return firebaseUser
    }

    fun getFirebaseUser() : FirebaseUser? {
        return auth.currentUser
    }


}