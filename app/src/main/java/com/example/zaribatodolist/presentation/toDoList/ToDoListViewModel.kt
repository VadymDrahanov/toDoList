package com.example.zaribatodolist.presentation.toDoList

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.zaribatodolist.data.model.TaskModel
import com.example.zaribatodolist.domain.usecase.listrepo.ObserveCurrentListUseCase
import com.example.zaribatodolist.domain.usecase.logic.FilterTaskByListUseCase
import com.example.zaribatodolist.domain.usecase.logic.FilterTasksUseCase
import com.example.zaribatodolist.domain.usecase.taskrepo.AddNewTaskUseCase
import com.example.zaribatodolist.domain.usecase.taskrepo.ObservTasksUseCase
import com.example.zaribatodolist.domain.usecase.taskrepo.UpdateTaskCompletionUseCase
import com.example.zaribatodolist.domain.usecase.tasks.AddTaskUseCase
import com.example.zaribatodolist.domain.usecase.tasks.GetTasksObservableUseCase
import com.example.zaribatodolist.domain.usecase.tasks.GetTasksParams
import com.example.zaribatodolist.domain.usecase.tasks.TaskCompletionState
import com.example.zaribatodolist.presentation.base.BaseViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToDoListViewModel @Inject constructor(
    private val addNewTaskUseCase: AddNewTaskUseCase,
    private val updateTaskCompletionUseCase: UpdateTaskCompletionUseCase,
    private val tasksObserverUseCase: ObservTasksUseCase,
    private val observeCurrentListUseCase: ObserveCurrentListUseCase,
    private val getTasksObservableUseCase: GetTasksObservableUseCase,
    private val addTaskUseCase: AddTaskUseCase,
    private val filterTasksUseCase: FilterTasksUseCase,
    private val filterTaskByListUseCase: FilterTaskByListUseCase
) :
    BaseViewModel<ToDoListUIState>() {

    val tasksLiveData: LiveData<ArrayList<TaskModel>> = tasksObserverUseCase.userTasksData
    val currentList: LiveData<String> = observeCurrentListUseCase.currentLists

    fun bindObservable() = viewModelScope.launch {
        getTasksObservableUseCase.invoke(
            GetTasksParams(
                userId = Firebase.auth.currentUser?.uid ?: "",
                completionState = TaskCompletionState.NOT_COMPLETED,
                userEmail = Firebase.auth.currentUser?.email?: ""

            )
        ).collect {
            if (it.isNotEmpty()) {
                val newState = ToDoListUIState(
                    isLoading = false,
                    isError = false,
                    taskList = it
                )
                uiState.value = newState
            } else {
                val newState = ToDoListUIState(
                    isLoading = false,
                    isError = false,
                    taskList = it
                )
                uiState.value = newState
            }
        }
    }

    fun addTask(task: TaskModel) {
        addTaskUseCase.invoke(task)
    }

    fun handleCheckBoxClick(id: String) {
        updateTaskCompletionUseCase.invoke(id)
    }

//    fun handleDataChange() {
//        uistate.value =
//            ToDoListUIState(taskList = tasksLiveData.value?.let { filterTasksUseCase.invoke(false, it) })
//    }

//    fun handleListChange() {
//        //handleListChange()
//        uiState.value =
//            ToDoListUIState(taskList = tasksLiveData.value?.let {
//                currentList.value?.let { it1 ->
//                    filterTaskByListUseCase.invoke(
//                        it1, it, false)
//                }
//            })
//    }
}