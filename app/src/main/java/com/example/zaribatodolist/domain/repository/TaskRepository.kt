package com.example.zaribatodolist.domain.repository

import com.example.zaribatodolist.data.model.TaskModel
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.QuerySnapshot

interface TaskRepository {
    var userTasks: ArrayList<TaskModel>
    suspend fun addTask(task: TaskModel) : Task<DocumentReference>
    fun removeTask()
    fun getAllTasks(): ArrayList<TaskModel>
    fun updateTask()
    suspend fun getTasks(uid: String) : Task<QuerySnapshot>
}