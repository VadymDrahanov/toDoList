package com.example.zaribatodolist.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TaskModel(
    val title: String,
    val isCompleted: Boolean,
    val user_id: String,
    val uid: String,
    val note: String = "",
    val list_id: String,
    var isSelected: Boolean = false
) :
    Parcelable