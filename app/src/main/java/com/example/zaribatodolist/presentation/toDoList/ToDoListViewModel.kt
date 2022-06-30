package com.example.zaribatodolist.presentation.toDoList

import androidx.lifecycle.LiveData

import com.example.zaribatodolist.data.model.TaskModel
import com.example.zaribatodolist.domain.usecase.AddNewTaskUseCase
import com.example.zaribatodolist.domain.usecase.TasksObserverUseCase
import com.example.zaribatodolist.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ToDoListViewModel @Inject constructor(
    private val addNewTaskUseCase: AddNewTaskUseCase,
    tasksObserverUseCase: TasksObserverUseCase
) :
    BaseViewModel<ToDoListUIState>() {

    val liveData: LiveData<ArrayList<TaskModel>> = tasksObserverUseCase.userTasks
}