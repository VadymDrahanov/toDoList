package com.example.zaribatodolist.domain.usecase.tasks

import com.example.zaribatodolist.data.model.TaskModel
import com.example.zaribatodolist.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTasksObservableUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {
    fun invoke(userId: String): Flow<List<TaskModel>> = taskRepository.getTasks1(userId)
}