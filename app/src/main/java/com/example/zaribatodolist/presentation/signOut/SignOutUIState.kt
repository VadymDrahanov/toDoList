package com.example.zaribatodolist.presentation.signOut

import com.example.zaribatodolist.presentation.base.UIState

data class SignOutUIState (
    val isLoading: Boolean = false,
    val isSignOuted: Boolean = false

): UIState