package com.example.zaribatodolist.presentation.auth

import com.example.zaribatodolist.presentation.base.UIState

data class AuthUIState(
    val isLoading: Boolean = false,
    val isAthenticated: Boolean = false
) : UIState