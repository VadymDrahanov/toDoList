package com.example.zaribatodolist.presentation.addTask

import com.example.zaribatodolist.data.model.TaskModel
import com.example.zaribatodolist.domain.usecase.listrepo.GetCurrentListUseCase
import com.example.zaribatodolist.domain.usecase.taskrepo.AddNewTaskUseCase
import com.example.zaribatodolist.domain.usecase.tasks.AddTaskUseCase
import com.example.zaribatodolist.domain.usecase.userrepo.AddNewTaskToUserUseCase
import com.example.zaribatodolist.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel

class AddNewTaskViewModel @Inject constructor(
    private val addNewTaskUseCase: AddNewTaskUseCase,
    private val getCurrentListUseCase: GetCurrentListUseCase,
    private val addNewTaskUseCaseToUserDocument: AddNewTaskToUserUseCase,
    private val addTaskUseCase: AddTaskUseCase
) :
    BaseViewModel<AddNewTaskUIState>() {

    fun handleAddNewTask(taskTitle: String, uid: String) {
        addTaskUseCase.invoke(TaskModel(title = taskTitle, false, uid, getRandomString(20), "", getCurrentListUseCase.invoke()))

//        viewModelScope.launch {
//            val list_id = getCurrentListUseCase.invoke()
//            addNewTaskUseCase.invoke(
//                SaveTaskParam(
//                    title = taskTitle,
//                    isCompleted = false,
//                    user_id = uid,
//                    list_id = list_id
//                )
//            ).addOnCompleteListener {
//                uiState.value = AddNewTaskUIState(true)
//                Log.i("Success", "Task was written")
//                uiState.value = AddNewTaskUIState(false)
//                when {
//                    it.isSuccessful -> {
//                        addNewTaskUseCaseToUserDocument.invoke(it.getResult().id)
//                        uiState.value = AddNewTaskUIState(true)
//                        Log.i("Success", "Task was written")
//
//                        uiState.value = AddNewTaskUIState(false)
//                    }
//                    it.isCanceled -> {
//                        Log.i("Error", "Something went wrong")
//                    }
//                }
//            }
//        }
    }

    fun getRandomString(length: Int): String {
        val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }

}