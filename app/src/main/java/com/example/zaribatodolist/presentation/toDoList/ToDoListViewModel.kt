package com.example.zaribatodolist.presentation.toDoList

import androidx.lifecycle.LiveData

import com.example.zaribatodolist.data.model.TaskModel
import com.example.zaribatodolist.domain.usecase.listrepo.ObserveCurrentListUseCase
import com.example.zaribatodolist.domain.usecase.logic.FilterTaskByListUseCase
import com.example.zaribatodolist.domain.usecase.logic.FilterTasksUseCase
import com.example.zaribatodolist.domain.usecase.taskrepo.AddNewTaskUseCase
import com.example.zaribatodolist.domain.usecase.taskrepo.ObservTasksUseCase
import com.example.zaribatodolist.domain.usecase.taskrepo.UpdateTaskCompletionUseCase
import com.example.zaribatodolist.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ToDoListViewModel @Inject constructor(
    private val addNewTaskUseCase: AddNewTaskUseCase,
    private val updateTaskCompletionUseCase: UpdateTaskCompletionUseCase,
    private val tasksObserverUseCase: ObservTasksUseCase,
    private val observeCurrentListUseCase: ObserveCurrentListUseCase,
    private val filterTasksUseCase: FilterTasksUseCase,
    private val filterTaskByListUseCase: FilterTaskByListUseCase
) :
    BaseViewModel<ToDoListUIState>() {

    val tasksLiveData: LiveData<ArrayList<TaskModel>> = tasksObserverUseCase.userTasksData
    val currentList: LiveData<String> = observeCurrentListUseCase.currentLists

    fun handleCheckBoxClick(id: String) {
        updateTaskCompletionUseCase.invoke(id)
    }

//    fun handleDataChange() {
//        uistate.value =
//            ToDoListUIState(taskList = tasksLiveData.value?.let { filterTasksUseCase.invoke(false, it) })
//    }

    fun handleListChange() {
        //handleListChange()
        uistate.value =
            ToDoListUIState(taskList = tasksLiveData.value?.let {
                currentList.value?.let { it1 ->
                    filterTaskByListUseCase.invoke(
                        it1, it, false)
                }
            })
    }
}