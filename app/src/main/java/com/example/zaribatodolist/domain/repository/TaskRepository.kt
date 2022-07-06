package com.example.zaribatodolist.domain.repository

import androidx.lifecycle.MutableLiveData
import com.example.zaribatodolist.data.model.SaveTaskParam
import com.example.zaribatodolist.data.model.TaskModel
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.QuerySnapshot

interface TaskRepository {

    val tasksLiveData: MutableLiveData<ArrayList<TaskModel>>

    suspend fun addTask(task: SaveTaskParam): Task<DocumentReference>
    suspend fun getTasks(uid: String): Task<QuerySnapshot>
    fun getTasksFromStorage(): ArrayList<TaskModel>

    fun removeTasks(tasks: ArrayList<TaskModel>)
    fun updateTaskCompletion(id: String)
    fun updateTaskNote(id: String, newNote: String)
}