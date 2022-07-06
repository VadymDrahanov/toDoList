package com.example.zaribatodolist.presentation.mainTaskList

import androidx.lifecycle.ViewModel
import com.example.zaribatodolist.data.model.TaskModel
import com.example.zaribatodolist.domain.repository.AuthRepository
import com.example.zaribatodolist.domain.repository.TaskRepository
import com.example.zaribatodolist.domain.usecase.taskrepo.RemoveTasksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainTasksViewModel @Inject constructor(
    private val removeTasksUseCase: RemoveTasksUseCase
) : ViewModel() {
    fun removeTasks(list: ArrayList<TaskModel>){
        removeTasksUseCase.invoke(list)
    }
}