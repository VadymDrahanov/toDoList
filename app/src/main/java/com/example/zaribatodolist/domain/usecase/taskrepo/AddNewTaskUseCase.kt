package com.example.zaribatodolist.domain.usecase.taskrepo

import com.example.zaribatodolist.data.model.SaveTaskParam
import com.example.zaribatodolist.domain.repository.TaskRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class AddNewTaskUseCase @Inject constructor(private val taskRepository: TaskRepository){
    suspend operator fun invoke(task: SaveTaskParam) : Task<DocumentReference> {
        val res =taskRepository.addTask(task)
        return res
    }

}