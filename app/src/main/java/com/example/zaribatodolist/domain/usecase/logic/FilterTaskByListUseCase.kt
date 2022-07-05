package com.example.zaribatodolist.domain.usecase.logic

import com.example.zaribatodolist.data.model.TaskModel
import java.util.ArrayList

class FilterTaskByListUseCase {
    fun invoke(currentList: String, tasks: ArrayList<TaskModel>): ArrayList<TaskModel> {
        if(currentList.equals("main"))
            return tasks

        val res = tasks.filter {
            it.list_id == currentList
        } as ArrayList<TaskModel> /* = java.util.ArrayList<com.example.zaribatodolist.data.model.TaskModel> */

        return res
    }
}