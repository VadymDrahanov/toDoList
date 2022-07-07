package com.example.zaribatodolist.domain.repository

import androidx.lifecycle.MutableLiveData
import com.example.zaribatodolist.data.model.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot

interface UserRepository {
    suspend fun sendGetUserRequest(id: String): Task<DocumentSnapshot>?
    fun updateUser(user: User)
    suspend fun createUser(user: User): Task<Void>?
    val userLiveData: MutableLiveData<User>
    fun addNewTask(taskID: String)
    suspend fun shareTask(gmail: String, listOfTasks: ArrayList<String>) : Task<QuerySnapshot>
}