package com.example.zaribatodolist.presentation.toDoList

import androidx.lifecycle.LiveData

import com.example.zaribatodolist.data.model.TaskModel
import com.example.zaribatodolist.domain.usecase.taskrepo.AddNewTaskUseCase
import com.example.zaribatodolist.domain.usecase.taskrepo.ObservTasksUseCase
import com.example.zaribatodolist.domain.usecase.taskrepo.UpdateTaskCompletionUseCase
import com.example.zaribatodolist.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ToDoListViewModel @Inject constructor(
    private val addNewTaskUseCase: AddNewTaskUseCase,
    private val updateTaskCompletionUseCase: UpdateTaskCompletionUseCase,
    tasksObserverUseCase: ObservTasksUseCase
) :
    BaseViewModel<ToDoListUIState>() {

    val liveData: LiveData<ArrayList<TaskModel>> = tasksObserverUseCase.userTasks

    fun handleCheckBoxClick(id: String){
        updateTaskCompletionUseCase.invoke(id)
    }
}