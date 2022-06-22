package com.example.zaribatodolist.data.repository

import com.example.zaribatodolist.data.model.User
import com.example.zaribatodolist.domain.repository.UserRepository
import com.google.firebase.firestore.FirebaseFirestore

class UserRepositoryImpl : UserRepository {

    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun getUser(id: String): User? {
        TODO("Not yet implemented")
    }

    override fun updateUser(user: User) {
        TODO("Not yet implemented")
    }

    override fun createUser(user: User, listener: (Boolean) -> Unit) {
        db.collection("users").add(user).addOnCompleteListener {
            if (it.isSuccessful) {
                listener.invoke(true)
            }
        }
    }

}