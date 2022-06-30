package com.example.zaribatodolist.domain.usecase

import com.example.zaribatodolist.data.model.TaskModel
import com.example.zaribatodolist.data.model.User
import com.example.zaribatodolist.domain.repository.TaskRepository
import com.example.zaribatodolist.domain.repository.UserRepository
import javax.inject.Inject

class GetTasksFromStorage @Inject constructor(private val tasksRepo: TaskRepository) {
//    fun invoke() : ArrayList<TaskModel> {
//        return tasksRepo.userTasks
//    }
}