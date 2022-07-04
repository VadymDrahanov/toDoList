package com.example.zaribatodolist.presentation.addTask

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.zaribatodolist.data.model.SaveTaskParam
import com.example.zaribatodolist.domain.usecase.taskrepo.AddNewTaskUseCase
import com.example.zaribatodolist.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class AddNewTaskViewModel @Inject constructor(
    private val addNewTaskUseCase: AddNewTaskUseCase
) :
    BaseViewModel<AddNewTaskUIState>() {

    fun handleAddNewTask(taskTitle: String, uid: String) {
        viewModelScope.launch {
            addNewTaskUseCase.invoke(SaveTaskParam(taskTitle, false, uid)).addOnCompleteListener {
                uistate.value = AddNewTaskUIState(true)
                Log.i("Success", "Task was written")
                uistate.value = AddNewTaskUIState(false)
                when {
                    it.isSuccessful -> {
                        uistate.value = AddNewTaskUIState(true)
                        Log.i("Success", "Task was written")

                        uistate.value = AddNewTaskUIState(false)
                    }
                    it.isCanceled -> {
                        Log.i("Error", "Something went wrong")
                    }
                }
            }
        }
    }
}