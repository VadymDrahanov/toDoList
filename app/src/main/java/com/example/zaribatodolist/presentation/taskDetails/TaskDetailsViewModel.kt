package com.example.zaribatodolist.presentation.taskDetails

import androidx.lifecycle.MutableLiveData
import com.example.zaribatodolist.data.model.TaskModel
import com.example.zaribatodolist.domain.usecase.taskrepo.GetTasksFromStorage
import com.example.zaribatodolist.presentation.base.BaseViewModel

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TaskDetailsViewModel @Inject constructor(private val getTasksFromStorage: GetTasksFromStorage) :
    BaseViewModel<TaskDetailsUIState>() {

    val tasksLiveData: MutableLiveData<TaskModel> = MutableLiveData()

    fun getTask(id: String){
        val res = getTasksFromStorage.invoke(id)
        if(res != null){
            tasksLiveData.value = res
        }
    }
}