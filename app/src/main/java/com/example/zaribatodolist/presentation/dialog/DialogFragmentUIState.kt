package com.example.zaribatodolist.presentation.dialog

import com.example.zaribatodolist.presentation.base.UIState

data class DialogFragmentUIState(
    val isNewListDialog: Boolean = false,
    val isRemoveTaskDialog: Boolean = false
) : UIState