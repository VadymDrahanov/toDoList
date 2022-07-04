package com.example.zaribatodolist.presentation.navigationMenu

import com.example.zaribatodolist.presentation.base.UIState

data class NavigationMenuUIState(
    val isSearching: Boolean = false
) : UIState
