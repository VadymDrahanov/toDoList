package com.example.zaribatodolist.presentation.navigationMenu

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.zaribatodolist.data.model.ListModel
import com.example.zaribatodolist.data.model.SaveParamList
import com.example.zaribatodolist.data.model.TaskModel
import com.example.zaribatodolist.domain.usecase.listrepo.CreateNewListUseCase
import com.example.zaribatodolist.domain.usecase.listrepo.ObserveListsUseCase
import com.example.zaribatodolist.domain.usecase.listrepo.SetCurrentListUseCase
import com.example.zaribatodolist.domain.usecase.taskrepo.ObservAndFilterTasksUseCase
import com.example.zaribatodolist.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NavigationMenuViewModel @Inject constructor(
    private val tasksObserverUseCase: ObservAndFilterTasksUseCase,
    private val createNewListUseCase: CreateNewListUseCase,
    private val listsObserveListsUseCase: ObserveListsUseCase,
    private val setCurrentListUseCase: SetCurrentListUseCase
) :
    BaseViewModel<NavigationMenuUIState>() {

    val tasksLiveData: LiveData<ArrayList<TaskModel>> = tasksObserverUseCase.userTasks
    val listsLiveData: LiveData<ArrayList<ListModel>> = listsObserveListsUseCase.userLists

    fun handleSearch(query: String?) {
        uiState.value = NavigationMenuUIState(true)
        tasksObserverUseCase.invoke(query)
    }

    fun searchClosed() {
        uiState.value = NavigationMenuUIState(false)
    }

    fun handleAddNewList(listTitle: String, user_id: String) {
        viewModelScope.launch {
            createNewListUseCase.invoke(SaveParamList(title = listTitle, user_id = user_id))
        }
    }

    fun handleListItemClick(list_id: String) {
        setCurrentListUseCase.invoke(list_id)
    }

}