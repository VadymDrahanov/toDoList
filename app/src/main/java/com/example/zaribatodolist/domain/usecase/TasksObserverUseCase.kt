package com.example.zaribatodolist.domain.usecase

import androidx.lifecycle.MutableLiveData
import com.example.zaribatodolist.data.model.TaskModel
import com.example.zaribatodolist.domain.repository.TaskRepository
import javax.inject.Inject

class TasksObserverUseCase @Inject constructor(private val repo: TaskRepository) {
    var userTasks: MutableLiveData<ArrayList<TaskModel>> = repo.tasksLiveData
}