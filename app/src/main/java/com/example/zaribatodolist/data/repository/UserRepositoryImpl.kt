package com.example.zaribatodolist.data.repository

import android.net.Uri
import android.util.Log
import com.example.zaribatodolist.data.model.User
import com.example.zaribatodolist.domain.repository.UserRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UserRepositoryImpl() : UserRepository {

    override var currentUser: User? = null

    override suspend fun sendGetUserRequest(id: String): Task<DocumentSnapshot>? {
        val doc = Firebase.firestore.collection("users").document(id)
        doc.get().addOnSuccessListener { documentSnapshot ->
            val user = User(
                uid = documentSnapshot.get("uid").toString(),
                name = documentSnapshot.get("name").toString(),
                //tasks = documentSnapshot.get("tasks") as ArrayList<TaskModel>,
                email = documentSnapshot.get("email").toString(),
//                newUser = documentSnapshot.get("newUser") as Boolean,
                photoUrl = Uri.parse((documentSnapshot.get("photoUrl").toString()))
            )

            currentUser = user
        }.addOnFailureListener {
            currentUser = null
        }

        return try {
            doc.get()
        } catch (e: Throwable) {
            Log.e("ERROR_rSERVICE", e.toString())
            null
        }
    }

    override fun updateUser(user: User) {
        //
    }

    override suspend fun createUser(user: User): Task<Void>? {
        return try {
            user.uid?.let { Firebase.firestore.collection("users").document(it).set(user) }
        } catch (e: Throwable) {
            Log.e("ERROR_SERVICE", e.toString())
            null
        }
    }

}