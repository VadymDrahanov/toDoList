package com.example.zaribatodolist.domain.usecase.logic

import com.example.zaribatodolist.data.model.TaskModel
import javax.inject.Inject

class SearchTasksUseCase @Inject constructor() {
    operator fun  invoke(list : List<TaskModel>, keyChar: String) : List<TaskModel> {
        val list = list.filter {
            it.title.contains(keyChar)
        }
        return list
    }
}