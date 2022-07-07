package com.example.zaribatodolist.data.repository

import android.content.ContentValues.TAG
import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.zaribatodolist.data.model.User
import com.example.zaribatodolist.domain.repository.UserRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UserRepositoryImpl() : UserRepository {

    private var currentUser: User? = null
    override val userLiveData: MutableLiveData<User> = MutableLiveData()

    override suspend fun sendGetUserRequest(id: String): Task<DocumentSnapshot>? {
        val doc = Firebase.firestore.collection("users").document(id)
        doc.get().addOnSuccessListener { documentSnapshot ->
            val user = User(
                uid = documentSnapshot.get("uid").toString(),
                name = documentSnapshot.get("name").toString(),
                tasks = documentSnapshot?.get("tasks") as ArrayList<String> /* = java.util.ArrayList<kotlin.String> */,
                email = documentSnapshot.get("email").toString(),
                photoUrl = Uri.parse((documentSnapshot.get("photoUrl").toString()))
            )

            currentUser = user
            userLiveData.value = currentUser
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

    override fun addNewTask(taskID: String) {
        val tasks = userLiveData.value?.tasks
        Log.i("tag", "in use case")
        tasks?.add(taskID)
        userLiveData.value?.uid?.let {
            Firebase.firestore.collection("users").document(it).update("tasks", tasks)
        }

    }


    override fun shareTask(gmail: String, listOfTasks: ArrayList<String>) {
        Firebase.firestore.collection("users").whereEqualTo("email", gmail)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    for (i in 0..listOfTasks.size - 1) {
                        addNewTask(listOfTasks.get(i))
                    }
                    Log.d(TAG, "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }

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