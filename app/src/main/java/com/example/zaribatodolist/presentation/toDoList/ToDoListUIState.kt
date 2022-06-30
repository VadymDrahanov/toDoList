package com.example.zaribatodolist.presentation.toDoList

import com.example.zaribatodolist.presentation.base.UIState

data class ToDoListUIState (
    val isLoading: Boolean = true,
    val isError: Boolean = false
): UIState