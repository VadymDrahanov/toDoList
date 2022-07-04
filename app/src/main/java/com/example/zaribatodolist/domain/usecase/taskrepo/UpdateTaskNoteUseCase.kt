package com.example.zaribatodolist.domain.usecase.taskrepo

import com.example.zaribatodolist.domain.repository.TaskRepository
import javax.inject.Inject

class UpdateTaskNoteUseCase @Inject constructor(private val repo: TaskRepository) {
    fun invoke(id: String, newNote: String){
        repo.updateTaskNote(id, newNote)
    }
}