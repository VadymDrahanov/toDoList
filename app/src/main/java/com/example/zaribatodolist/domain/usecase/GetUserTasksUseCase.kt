package com.example.zaribatodolist.domain.usecase

import android.util.Log
import com.example.zaribatodolist.data.model.TaskModel
import com.example.zaribatodolist.domain.repository.TaskRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.*
import javax.inject.Inject

class GetUserTasksUseCase @Inject constructor(private val taskRepository: TaskRepository) {
    suspend fun invoke(uid: String): Task<QuerySnapshot> {
        return taskRepository.getTasks(uid)
    }
}