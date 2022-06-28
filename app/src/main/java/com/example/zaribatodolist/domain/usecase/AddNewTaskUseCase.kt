package com.example.zaribatodolist.domain.usecase

import com.example.zaribatodolist.data.model.TaskModel
import com.example.zaribatodolist.domain.repository.TaskRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import javax.inject.Inject

class AddNewTaskUseCase @Inject constructor(private val taskRepository: TaskRepository){
    suspend operator fun invoke(task: TaskModel) : Task<DocumentReference> {
        return taskRepository.addTask(task)
    }
}