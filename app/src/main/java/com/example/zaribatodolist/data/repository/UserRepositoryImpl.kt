package com.example.zaribatodolist.data.repository

import com.example.zaribatodolist.data.model.User
import com.example.zaribatodolist.domain.reposotory.UserRepository
import com.google.firebase.firestore.FirebaseFirestore

class UserRepositoryImpl : UserRepository {

    private val db : FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun getUser(id: String): User? {
        TODO("Not yet implemented")
    }

    override fun updateUser(user: User) {
        TODO("Not yet implemented")
    }

    override fun createUser(user: User) {
        db.collection("users")
    }

}