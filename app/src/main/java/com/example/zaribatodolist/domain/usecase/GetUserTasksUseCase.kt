package com.example.zaribatodolist.domain.usecase

import com.example.zaribatodolist.domain.repository.TaskRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
import javax.inject.Inject

class GetUserTasksUseCase @Inject constructor(private val taskRepository: TaskRepository) {
    suspend fun invoke(uid: String) : Task<QuerySnapshot> {
        return taskRepository.getTasks(uid)
    }
}