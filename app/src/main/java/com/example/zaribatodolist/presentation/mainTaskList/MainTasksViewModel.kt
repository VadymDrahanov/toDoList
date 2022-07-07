package com.example.zaribatodolist.presentation.mainTaskList

import com.example.zaribatodolist.data.model.TaskModel
import com.example.zaribatodolist.domain.usecase.taskrepo.RemoveTasksUseCase
import com.example.zaribatodolist.domain.usecase.userrepo.ShareTasksUseCase
import com.example.zaribatodolist.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainTasksViewModel @Inject constructor(
    private val removeTasksUseCase: RemoveTasksUseCase,
    private val shareTasksUseCase: ShareTasksUseCase
) : BaseViewModel<MainTaskViewUIState>() {

    fun removeTasks(list: ArrayList<TaskModel>){
        removeTasksUseCase.invoke(list)
    }

    fun handleOnShareButtonClick(gmail: String, listOfTasks: ArrayList<String>){
        shareTasksUseCase.invoke(gmail, listOfTasks)
    }
}