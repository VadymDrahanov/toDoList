package com.example.zaribatodolist.presentation.toDoList

import com.example.zaribatodolist.data.model.TaskModel
import com.example.zaribatodolist.presentation.base.UIState

data class ToDoListUIState (
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val taskList: ArrayList<TaskModel>? = ArrayList()
): UIState