package com.example.zaribatodolist.presentation.completedList

import androidx.lifecycle.LiveData
import com.example.zaribatodolist.data.model.TaskModel
import com.example.zaribatodolist.domain.usecase.listrepo.ObserveCurrentListUseCase
import com.example.zaribatodolist.domain.usecase.listrepo.ObserveListsUseCase
import com.example.zaribatodolist.domain.usecase.logic.FilterTaskByListUseCase
import com.example.zaribatodolist.domain.usecase.taskrepo.ObservTasksUseCase
import com.example.zaribatodolist.presentation.base.BaseViewModel
import com.example.zaribatodolist.presentation.toDoList.ToDoListUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CompletedListViewModel @Inject constructor(
    tasksObserverUseCase: ObservTasksUseCase,
    listObserveUseCase: ObserveCurrentListUseCase,
    private val filterTaskByListUseCase: FilterTaskByListUseCase
) :
    BaseViewModel<CompletedUIState>() {
    val tasksLiveData: LiveData<ArrayList<TaskModel>> = tasksObserverUseCase.userTasksData
    val listLiveData: LiveData<String> = listObserveUseCase.currentLists

    fun handleDataChanged() {
        uistate.value =
            CompletedUIState(taskList = tasksLiveData.value?.let {
                listLiveData.value?.let { it1 ->
                    filterTaskByListUseCase.invoke(
                        it1, it, true)
                }
            })
    }
}