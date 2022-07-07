package com.example.zaribatodolist.presentation.mainTaskList

import com.example.zaribatodolist.presentation.base.UIState

data class MainTaskViewUIState(
    val shareSuccess: Boolean = false,
    val shareWentWrong: Boolean = false,
    val isProcess: Boolean = false
) : UIState