package com.example.zaribatodolist.presentation.completedList

import androidx.lifecycle.LiveData
import com.example.zaribatodolist.data.model.TaskModel
import com.example.zaribatodolist.domain.usecase.listrepo.ObserveCurrentListUseCase
import com.example.zaribatodolist.domain.usecase.listrepo.ObserveListsUseCase
import com.example.zaribatodolist.domain.usecase.taskrepo.ObservTasksUseCase
import com.example.zaribatodolist.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CompletedListViewModel @Inject constructor(
    tasksObserverUseCase: ObservTasksUseCase,
    listObserveUseCase: ObserveCurrentListUseCase
) :
    BaseViewModel<CompletedUIState>() {
    val tasksLiveData: LiveData<ArrayList<TaskModel>> = tasksObserverUseCase.userTasksData
    val listLiveData: LiveData<String> = listObserveUseCase.currentLists

    fun handleDataChanged() {

    }
}