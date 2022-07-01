package com.example.zaribatodolist.data.model

data class SaveTaskParam(
    val title: String,
    val isCompleted: Boolean,
    val user_id: String,
    val note: String = ""
)