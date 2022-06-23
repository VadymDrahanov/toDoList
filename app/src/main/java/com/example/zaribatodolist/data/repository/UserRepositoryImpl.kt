package com.example.zaribatodolist.data.repository

import android.util.Log
import com.example.zaribatodolist.data.model.User
import com.example.zaribatodolist.domain.repository.UserRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UserRepositoryImpl : UserRepository {

    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    override suspend fun getUser(id: String): Task<DocumentSnapshot>? {
        val doc = Firebase.firestore.collection("users").document(id)
        return try{
            doc.get()
        }catch (e : Throwable){
            Log.e("ERROR_SERVICE", e.toString())
            null
        }
    }

    override fun updateUser(user: User) {

    }


    override suspend fun createUser(user: User): Task<Void>? {
        return try{
            user.uid?.let { Firebase.firestore.collection("users").document(it).set(user) }
        }catch (e : Throwable){
            Log.e("ERROR_SERVICE", e.toString())
            null
        }
    }

}