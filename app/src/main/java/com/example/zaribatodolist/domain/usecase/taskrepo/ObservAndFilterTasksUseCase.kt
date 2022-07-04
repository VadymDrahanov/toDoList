package com.example.zaribatodolist.domain.usecase.taskrepo

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.zaribatodolist.data.model.TaskModel
import com.example.zaribatodolist.domain.repository.TaskRepository
import javax.inject.Inject

class ObservAndFilterTasksUseCase @Inject constructor(private val repo: TaskRepository)  {
    var userTasks: MutableLiveData<ArrayList<TaskModel>> = MutableLiveData()

    var userTasksData: MutableLiveData<ArrayList<TaskModel>> = repo.tasksLiveData

    fun invoke(query: String?){
        if (query.isNullOrBlank() || query.equals("") || query.equals(" ")){
            userTasks.value = ArrayList<TaskModel>()
            return
        }

        userTasks.value = userTasksData.value!!.filter {
            it.title.contains(query) == true
        } as ArrayList
    }
}
