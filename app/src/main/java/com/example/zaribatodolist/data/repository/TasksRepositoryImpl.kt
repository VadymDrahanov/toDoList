package com.example.zaribatodolist.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.zaribatodolist.data.model.TaskModel
import com.google.android.gms.tasks.Task

import com.example.zaribatodolist.domain.repository.TaskRepository
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class TasksRepositoryImpl : TaskRepository {
    private var db = Firebase.firestore

    var userTasks: ArrayList<TaskModel> = ArrayList()
    override val tasksLiveData: MutableLiveData<ArrayList<TaskModel>> = MutableLiveData()

    override suspend fun addTask(task: TaskModel): Task<DocumentReference> {
        return db.collection("tasks").add(task).addOnCompleteListener {
            when {
                it.isSuccessful -> {
                    userTasks.add(task)
                    tasksLiveData.value = userTasks
                }
            }
        }

    }

    override fun removeTask() {
        TODO("Not yet implemented")
    }

    override fun updateTask() {
        TODO("Not yet implemented")
    }

    override suspend fun getTasks(uid: String): Task<QuerySnapshot> {

        Log.i("----------------------------------------------------------------", "Query Called")
        val res = db.collection("tasks").whereEqualTo("user_id", uid).get()

        res.addOnCompleteListener {
            for (i in 0..it.getResult().documents.size - 1) {
                val doc = res.getResult().documents.get(i).data
                val model = TaskModel(
                    doc!!.get("title").toString(),
                    doc!!.get("completed") as Boolean,
                    doc!!.get("user_id").toString()
                )
                userTasks.add(model)
            }
            Log.i(
                "----------------------------------------------------------------",
                "User tasks are changed, new size are: " + tasksLiveData.value?.size.toString()
            )
        }

        tasksLiveData.value = userTasks

        Log.i("----------------------------------------------------------------", "Query Finished")

        return res
    }

    override fun getTasksFromStorage(): ArrayList<TaskModel> {
        return userTasks
    }

}