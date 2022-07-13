package com.example.zaribatodolist.presentation.navigationMenu

import com.example.zaribatodolist.data.model.TaskModel
import com.example.zaribatodolist.presentation.base.UIState

data class NavigationMenuUIState(
    val isSearching: Boolean = false
) : UIState

data class NavigationMenuItems(
    val taskList: List<TaskModel> = emptyList()
) : UIState