package com.example.zaribatodolist.presentation.navigationMenu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.zaribatodolist.data.model.ListModel
import com.example.zaribatodolist.data.model.SaveParamList
import com.example.zaribatodolist.data.model.TaskModel
import com.example.zaribatodolist.domain.usecase.listrepo.CreateNewListUseCase
import com.example.zaribatodolist.domain.usecase.listrepo.ObserveListsUseCase
import com.example.zaribatodolist.domain.usecase.listrepo.SetCurrentListUseCase
import com.example.zaribatodolist.domain.usecase.logic.FilterTaskByListUseCase
import com.example.zaribatodolist.domain.usecase.logic.SearchTasksUseCase
import com.example.zaribatodolist.domain.usecase.taskrepo.ObservAndFilterTasksUseCase
import com.example.zaribatodolist.domain.usecase.tasks.GetTasksObservableUseCase
import com.example.zaribatodolist.domain.usecase.tasks.GetTasksParams
import com.example.zaribatodolist.domain.usecase.tasks.TaskCompletionState
import com.example.zaribatodolist.presentation.base.BaseViewModel
import com.example.zaribatodolist.presentation.completedList.CompletedUIState
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NavigationMenuViewModel @Inject constructor(
    private val tasksObserverUseCase: ObservAndFilterTasksUseCase,
    private val createNewListUseCase: CreateNewListUseCase,
    private val listsObserveListsUseCase: ObserveListsUseCase,
    private val setCurrentListUseCase: SetCurrentListUseCase,
    private val getTasksObservableUseCase: GetTasksObservableUseCase,
    private val filterTaskByListUseCase: FilterTaskByListUseCase,
    private val searchTasksUseCase: SearchTasksUseCase
) :
    BaseViewModel<NavigationMenuUIState>() {

    val tasksLiveData: LiveData<ArrayList<TaskModel>> = tasksObserverUseCase.userTasks
    val listsLiveData: LiveData<ArrayList<ListModel>> = listsObserveListsUseCase.userLists

    private val mutData = MutableLiveData<List<TaskModel>>()
    val liveData = mutData

    fun bindObservable(keyChar: String) = viewModelScope.launch {
        getTasksObservableUseCase.invoke(
            GetTasksParams(
                userId = Firebase.auth.currentUser?.uid ?: "",
                completionState = TaskCompletionState.NOT_COMPLETED,
                userEmail = Firebase.auth.currentUser?.email?: ""
            )
        ).collect {
            if (it.isNotEmpty()) {
                mutData.value = searchTasksUseCase.invoke(it, keyChar)
            } else {
                mutData.value = searchTasksUseCase.invoke(it, keyChar)
            }
        }
    }

    fun handleSearch(query: String?) {
        uiState.value = NavigationMenuUIState(true)
        if(query != null)
            bindObservable(query)
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