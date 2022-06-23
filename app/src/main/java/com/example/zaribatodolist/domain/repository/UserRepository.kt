package com.example.zaribatodolist.domain.repository

import com.example.zaribatodolist.data.model.User
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot

interface UserRepository {
    suspend fun getUser(id: String): Task<DocumentSnapshot>?
    fun updateUser(user: User)
    suspend fun createUser(user: User): Task<Void>?
}