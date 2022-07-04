package com.example.zaribatodolist.presentation.navigationMenu

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.zaribatodolist.data.model.TaskModel
import com.example.zaribatodolist.domain.usecase.taskrepo.ObservAndFilterTasksUseCase
import com.example.zaribatodolist.presentation.base.BaseViewModel
import com.example.zaribatodolist.presentation.base.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NavigationMenuViewModel @Inject constructor(
    private val tasksObserverUseCase: ObservAndFilterTasksUseCase
) :
    BaseViewModel<NavigationMenuUIState>() {

    val liveData: LiveData<ArrayList<TaskModel>> = tasksObserverUseCase.userTasks

    fun handleSearch(query: String?) {
        uistate.value = NavigationMenuUIState(true)
        tasksObserverUseCase.invoke(query)
    }

    fun searchClosed(){
        uistate.value = NavigationMenuUIState(false)
    }
}