package com.example.zaribatodolist.presentation.mainTaskList

import androidx.lifecycle.viewModelScope
import com.example.zaribatodolist.data.model.TaskModel
import com.example.zaribatodolist.domain.usecase.logic.IsValidShareDataUseCase
import com.example.zaribatodolist.domain.usecase.taskrepo.RemoveTasksUseCase
import com.example.zaribatodolist.domain.usecase.userrepo.ShareTasksUseCase
import com.example.zaribatodolist.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainTasksViewModel @Inject constructor(
    private val removeTasksUseCase: RemoveTasksUseCase,
    private val shareTasksUseCase: ShareTasksUseCase,
    private val isValidDataForShare: IsValidShareDataUseCase
) : BaseViewModel<MainTaskViewUIState>() {

    fun removeTasks(list: ArrayList<TaskModel>) {
        removeTasksUseCase.invoke(list)
    }

    fun handleOnShareButtonClick(gmail: String, listOfTasks: ArrayList<String>) {
        val isValid =isValidDataForShare.invoke(gmail.trim(), listOfTasks)
        if(!isValid){
            uistate.value = MainTaskViewUIState(shareWentWrong = true)
            uistate.value = MainTaskViewUIState(shareWentWrong = false)
            return
        }
        viewModelScope.launch {

            uistate.value = MainTaskViewUIState(isProcess = true)
            shareTasksUseCase.invoke(gmail, listOfTasks).addOnCompleteListener {
                uistate.value = MainTaskViewUIState(isProcess = false)
                when {
                    it.isSuccessful -> {
                        if(it.getResult().isEmpty){
                            uistate.value = MainTaskViewUIState(shareWentWrong = true)
                            uistate.value = MainTaskViewUIState(shareWentWrong = false)
                        }else{
                            uistate.value = MainTaskViewUIState(shareSuccess = true)
                            uistate.value = MainTaskViewUIState(shareSuccess = false)
                        }

                    }
                    it.isCanceled -> {
                        uistate.value = MainTaskViewUIState(shareWentWrong = true)
                        uistate.value = MainTaskViewUIState(shareWentWrong = false)
                    }
                }
            }
        }
    }
}