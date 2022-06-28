package com.example.zaribatodolist.presentation.addTask

import com.example.zaribatodolist.presentation.base.UIState

data class AddNewTaskUIState(
    val isCompleted: Boolean = false
) : UIState