package com.example.zaribatodolist.domain.repository

import androidx.lifecycle.MutableLiveData
import com.example.zaribatodolist.data.model.SaveTaskParam
import com.example.zaribatodolist.data.model.TaskModel
import com.example.zaribatodolist.domain.usecase.tasks.GetTasksParams
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.flow.Flow

interface TaskRepository {

    val tasksLiveData: MutableLiveData<ArrayList<TaskModel>>

    fun getTasks1(params: GetTasksParams): Flow<MutableList<TaskModel>>
    fun addTask(task: TaskModel)

    suspend fun addTask(task: SaveTaskParam): Task<DocumentReference>
    suspend fun getTasks(uid: String): Task<QuerySnapshot>
    fun getTasksFromStorage(): ArrayList<TaskModel>

    fun removeTasks(tasks: ArrayList<TaskModel>)
    fun updateTaskCompletion(id: String)
    fun updateTaskNote(id: String, newNote: String)
}