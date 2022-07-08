package com.example.zaribatodolist.presentation.completedList

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.zaribatodolist.data.model.TaskModel
import com.example.zaribatodolist.domain.usecase.listrepo.ObserveCurrentListUseCase
import com.example.zaribatodolist.domain.usecase.logic.FilterTaskByListUseCase
import com.example.zaribatodolist.domain.usecase.taskrepo.ObservTasksUseCase
import com.example.zaribatodolist.domain.usecase.tasks.GetTasksObservableUseCase
import com.example.zaribatodolist.presentation.base.BaseViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompletedListViewModel @Inject constructor(
    tasksObserverUseCase: ObservTasksUseCase,
    listObserveUseCase: ObserveCurrentListUseCase,
    private val getTasksObservableUseCase: GetTasksObservableUseCase,
    private val filterTaskByListUseCase: FilterTaskByListUseCase
) : BaseViewModel<CompletedUIState>() {

    val tasksLiveData: LiveData<ArrayList<TaskModel>> = tasksObserverUseCase.userTasksData
    val listLiveData: LiveData<String> = listObserveUseCase.currentLists

    fun bindObservable() = viewModelScope.launch {
        getTasksObservableUseCase.invoke(Firebase.auth.currentUser?.uid ?: "")
            .collect {
                if (it.isNotEmpty()) {
                    val newState = CompletedUIState(
                        isLoading = false,
                        isError = false,
                        taskList = it
                    )
                    uiState.value = newState
                } else {
                    val newState = CompletedUIState(
                        isLoading = false,
                        isError = false,
                        taskList = it
                    )
                    uiState.value = newState
                }
            }
    }

//    fun handleDataChanged() {
//        uiState.value =
//            CompletedUIState(taskList = tasksLiveData.value?.let {
//                listLiveData.value?.let { it1 ->
//                    filterTaskByListUseCase.invoke(
//                        it1, it, true
//                    )
//                }
//            })
//    }

    var listOfTasks: ArrayList<TaskModel> = ArrayList()
    fun handleOnLongClick(id: TaskModel) {
        for (task in listOfTasks) {
            if (id.uid.equals(task.uid)) {
                listOfTasks.remove(task)
            } else {
                listOfTasks.add(task)
            }
        }

    }
}