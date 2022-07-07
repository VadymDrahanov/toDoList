package com.example.zaribatodolist.data.repository

import android.content.ContentValues.TAG
import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.zaribatodolist.data.model.User
import com.example.zaribatodolist.domain.repository.UserRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
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
        tasks?.add(taskID)
        userLiveData.value?.uid?.let {
            Firebase.firestore.collection("users").document(it).update("tasks", tasks)
        }
    }

    fun shareNewTask(taskID: String, email: String) {
        val tasks = userLiveData.value?.tasks
        tasks?.add(taskID)
        userLiveData.value?.uid?.let {
            Firebase.firestore.collection("users").document(it).update("tasks", tasks)
        }
    }

    override suspend fun shareTask(
        gmail: String,
        listOfTasks: ArrayList<String>
    ): Task<QuerySnapshot> {
        val res = Firebase.firestore.collection("users").whereEqualTo("email", gmail).get()
            .addOnCompleteListener {
                when {
                    it.isSuccessful -> {
                        if (!it.result.documents.isEmpty()) {
                            val recipientID = it.result.documents[0].id
                            val recipientRef =
                                Firebase.firestore.collection("users").document(recipientID)
                            Log.i("ERROR_SERVICE", it.result.documents[0].id)
                            for (i in 0 until listOfTasks.size) {
                                recipientRef.update(
                                    "tasks",
                                    FieldValue.arrayUnion(listOfTasks.get(i))
                                )
                            }
                        }
                    }
                }
            }
        return res
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