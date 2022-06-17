package com.example.zaribatodolist.data.model

data class Task(val title: String, val isCompleted: Boolean) {
    constructor(
        uid: String?,
        title: String,
        isCompleted: Boolean
    ) : this(title, isCompleted)
}