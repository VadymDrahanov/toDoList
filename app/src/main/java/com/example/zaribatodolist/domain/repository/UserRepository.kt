package com.example.zaribatodolist.domain.repository

import com.example.zaribatodolist.data.model.User

interface UserRepository {
    fun getUser(id: String): User?
    fun updateUser(user: User)
    fun createUser(user: User, listener: (Boolean) -> Unit)
}