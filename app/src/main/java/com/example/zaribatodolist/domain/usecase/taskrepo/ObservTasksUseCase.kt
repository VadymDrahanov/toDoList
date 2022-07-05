package com.example.zaribatodolist.domain.usecase.taskrepo

import androidx.lifecycle.MutableLiveData
import com.example.zaribatodolist.data.model.TaskModel
import com.example.zaribatodolist.domain.repository.TaskRepository
import javax.inject.Inject

class ObservTasksUseCase @Inject constructor(private val repo: TaskRepository) {
    var userTasksData: MutableLiveData<ArrayList<TaskModel>> = repo.tasksLiveData

    suspend fun invoke() : List<TaskModel>? {
        //repo.getTasks("").getResult().
        return repo.tasksLiveData.value?.filter {
            it.isCompleted == false
        }
    }
}