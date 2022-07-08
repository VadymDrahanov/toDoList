package com.example.zaribatodolist.presentation.addTask

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.zaribatodolist.data.model.SaveTaskParam
import com.example.zaribatodolist.domain.usecase.listrepo.GetCurrentListUseCase
import com.example.zaribatodolist.domain.usecase.taskrepo.AddNewTaskUseCase
import com.example.zaribatodolist.domain.usecase.userrepo.AddNewTaskToUserUseCase
import com.example.zaribatodolist.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class AddNewTaskViewModel @Inject constructor(
    private val addNewTaskUseCase: AddNewTaskUseCase,
    private val getCurrentListUseCase: GetCurrentListUseCase,
    private val addNewTaskUseCaseToUserDocument: AddNewTaskToUserUseCase
) :
    BaseViewModel<AddNewTaskUIState>() {

    fun handleAddNewTask(taskTitle: String, uid: String) {
        viewModelScope.launch {
            val list_id = getCurrentListUseCase.invoke()
            addNewTaskUseCase.invoke(
                SaveTaskParam(
                    title = taskTitle,
                    isCompleted = false,
                    user_id = uid,
                    list_id = list_id
                )
            ).addOnCompleteListener {
                uiState.value = AddNewTaskUIState(true)
                Log.i("Success", "Task was written")
                uiState.value = AddNewTaskUIState(false)
                when {
                    it.isSuccessful -> {
                        addNewTaskUseCaseToUserDocument.invoke(it.getResult().id)
                        uiState.value = AddNewTaskUIState(true)
                        Log.i("Success", "Task was written")

                        uiState.value = AddNewTaskUIState(false)
                    }
                    it.isCanceled -> {
                        Log.i("Error", "Something went wrong")
                    }
                }
            }
        }
    }
}