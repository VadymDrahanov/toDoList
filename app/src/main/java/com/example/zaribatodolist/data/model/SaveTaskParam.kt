package com.example.zaribatodolist.data.model

data class SaveTaskParam(
    val title: String,
    val isCompleted: Boolean,
    val user_id: List<String>,
    val note: String = "",
    val list_id: String
)