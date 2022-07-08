package com.example.zaribatodolist.domain.usecase.tasks

import com.example.zaribatodolist.data.model.TaskModel
import com.example.zaribatodolist.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTasksObservableUseCase @Inject constructor(
    private val taskRepository: TaskRepository
) {
    fun invoke(param: GetTasksParams): Flow<List<TaskModel>> = taskRepository.getTasks1(param)
}

data class GetTasksParams(
    val userId: String,
    val completionState: TaskCompletionState
)

enum class TaskCompletionState {
    COMPLETED,
    NOT_COMPLETED,
    ALL
}