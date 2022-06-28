package com.example.zaribatodolist.presentation.toDoList

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.zaribatodolist.domain.usecase.AddNewTaskUseCase
import com.example.zaribatodolist.domain.usecase.GetUserTasksUseCase
import com.example.zaribatodolist.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class ToDoListViewModel @Inject constructor(
    private val addNewTaskUseCase: AddNewTaskUseCase,
    private val getUserTasksUseCase: GetUserTasksUseCase
) :
    BaseViewModel<ToDoListUIState>() {

    fun getUserTasks(uid: String) {
        viewModelScope.launch {
            getUserTasksUseCase.invoke(uid).addOnCompleteListener{
                it.getResult().documents.size
                Log.i("Errooooooooooooooooooooooooooooooooooor", it.getResult().documents.size.toString())

            }
        }
    }

    fun handleAddNewTask(taskTitle: String, uid: String) {
//        addNewTaskUseCase.invoke(taskTitle, uid)
    }
}