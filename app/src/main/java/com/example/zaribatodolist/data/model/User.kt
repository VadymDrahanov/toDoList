package com.example.zaribatodolist.data.model

data class User(
    val uid: String?,
    val email: String?,
    val tasks: ArrayList<Task>?,
    val name: String?
)
