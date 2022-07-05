package com.example.zaribatodolist.domain.usecase.logic

import com.example.zaribatodolist.data.model.TaskModel

class FilterTasksUseCase {
    fun invoke(completion: Boolean, list: ArrayList<TaskModel>): ArrayList<TaskModel>{
        val res = list.filter {
            it.isCompleted == completion
        } as ArrayList<TaskModel> /* = java.util.ArrayList<com.example.zaribatodolist.data.model.TaskModel> */

        return res
    }
}