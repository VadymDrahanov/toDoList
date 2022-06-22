package com.example.zaribatodolist.domain.repository

import com.example.zaribatodolist.data.model.Task

interface TaskRepository {
    fun addTask(task: Task, userUid: String) : Boolean
    fun removeTask()
    fun getAllTasks(): ArrayList<Task>
    fun updateTask()
}