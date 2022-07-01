package com.example.zaribatodolist.domain.usecase.taskrepo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.zaribatodolist.data.model.TaskModel
import com.example.zaribatodolist.domain.repository.TaskRepository
import javax.inject.Inject

class GetTasksFromStorage @Inject constructor(private val tasksRepo: TaskRepository) {
    var userTasks: MutableLiveData<ArrayList<TaskModel>> = tasksRepo.tasksLiveData

    fun invoke(id: String): TaskModel? {

        for (task in userTasks.value!!) {
            if (task.uid == id) {
                return task
            }
        }
        return null

    }
}