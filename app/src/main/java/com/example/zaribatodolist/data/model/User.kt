package com.example.zaribatodolist.data.model

import android.net.Uri

data class User(
    val uid: String?,
    val email: String?,
    val tasks: ArrayList<TaskModel>?,
    val name: String?,
    val newUser: Boolean?,
    val photoUrl: Uri?
)
