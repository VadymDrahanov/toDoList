package com.example.zaribatodolist.domain.usecase.tasks

import com.example.zaribatodolist.data.model.TaskModel
import com.example.zaribatodolist.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddTaskUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {
    fun invoke(task: TaskModel) {
        taskRepository.addTask(task)
    }
}