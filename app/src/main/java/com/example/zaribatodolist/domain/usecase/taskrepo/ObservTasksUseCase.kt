package com.example.zaribatodolist.domain.usecase.taskrepo

import androidx.lifecycle.MutableLiveData
import com.example.zaribatodolist.data.model.TaskModel
import com.example.zaribatodolist.domain.repository.TaskRepository
import javax.inject.Inject

class ObservTasksUseCase @Inject constructor(private val repo: TaskRepository) {
    var userTasks: MutableLiveData<ArrayList<TaskModel>> = repo.tasksLiveData

}