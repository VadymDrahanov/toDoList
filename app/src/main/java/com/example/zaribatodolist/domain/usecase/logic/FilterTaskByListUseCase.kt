package com.example.zaribatodolist.domain.usecase.logic

import com.example.zaribatodolist.data.model.TaskModel
import kotlin.collections.ArrayList

class FilterTaskByListUseCase {
    fun invoke(
        currentList: String,
        tasks: ArrayList<TaskModel>,
        completion: Boolean
    ): ArrayList<TaskModel> {

        var res = tasks.filter {
            it.isCompleted == completion
        } as ArrayList<TaskModel> /* = java.util.ArrayList<com.example.zaribatodolist.data.model.TaskModel> */

        if (currentList.equals("main"))
            return res

        res = res.filter {
            it.list_id == currentList
        } as ArrayList

        return res
    }
}