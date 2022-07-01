package com.example.zaribatodolist.domain.usecase.taskrepo

import com.example.zaribatodolist.domain.repository.TaskRepository
import javax.inject.Inject

class UpdateTaskCompletionUseCase @Inject constructor(private val taskRepository: TaskRepository){
    fun invoke(id: String) {
        taskRepository.updateTaskCompletion(id)
    }
}