package com.example.zaribatodolist.domain.usecase.taskrepo

import com.example.zaribatodolist.data.model.TaskModel
import com.example.zaribatodolist.domain.repository.TaskRepository
import javax.inject.Inject

class RemoveTasksUseCase @Inject constructor(private val repository: TaskRepository) {
    fun invoke(arrayList: ArrayList<TaskModel>){
        repository.removeTasks(arrayList)
    }
}