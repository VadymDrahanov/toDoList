package com.example.zaribatodolist.presentation.completedList

import com.example.zaribatodolist.data.model.TaskModel
import com.example.zaribatodolist.presentation.base.UIState

data class CompletedUIState (
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val taskList: List<TaskModel> = emptyList()
) : UIState