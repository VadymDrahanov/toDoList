package com.example.zaribatodolist.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.zaribatodolist.data.model.SaveTaskParam
import com.example.zaribatodolist.data.model.TaskModel
import com.google.android.gms.tasks.Task

import com.example.zaribatodolist.domain.repository.TaskRepository
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FieldPath.documentId
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class TasksRepositoryImpl : TaskRepository {
    private var db = Firebase.firestore

    var userTasks: ArrayList<TaskModel> = ArrayList()
    override val tasksLiveData: MutableLiveData<ArrayList<TaskModel>> = MutableLiveData()

    override suspend fun addTask(task: SaveTaskParam): Task<DocumentReference> {
        return db.collection("tasks").add(task).addOnCompleteListener {
            when {
                it.isSuccessful -> {
                    userTasks.add(
                        TaskModel(
                            title = task.title,
                            uid = it.getResult().id,
                            isCompleted = false,
                            user_id = task.user_id,
                            list_id = task.list_id
                        )
                    )
                    tasksLiveData.value = userTasks
                }
            }
        }

    }


    override fun updateTaskNote(id: String, newNote: String) {
        Log.i("----------------------------------------------------------------", id)

        val ref = db.collection("tasks").document(id)
        var tempToAdd: TaskModel? = null
        var tempToRemove: TaskModel? = null

        ref.update("note", newNote)
            .addOnSuccessListener {
                for (task in userTasks) {
                    if (task.uid == id) {
                        tempToRemove = task
                        tempToAdd = task.copy(note = newNote)
                    }
                }

                if (tempToAdd != null) {
                    userTasks.add(tempToAdd!!)
                }

                if (tempToRemove != null) {
                    userTasks.remove(tempToRemove!!)
                }

                tasksLiveData.value = userTasks
            }
    }


    override fun updateTaskCompletion(id: String) {

        val ref = db.collection("tasks").document(id)
        var tempToAdd: TaskModel? = null
        var tempToRemove: TaskModel? = null

        ref.update("completed", true)
            .addOnSuccessListener {
                for (task in userTasks) {
                    if (task.uid == id) {
                        tempToRemove = task
                        tempToAdd = task.copy(isCompleted = true)
                    }
                }

                if (tempToAdd != null)
                    userTasks.add(tempToAdd!!)

                if (tempToRemove != null)
                    userTasks.remove(tempToRemove!!)

                tasksLiveData.value = userTasks
            }
    }

    override suspend fun getTasks(uid: String): Task<QuerySnapshot> {

        userTasks.clear()
        tasksLiveData.value?.clear()
        val temp = db.collection("tasks").get()
        Log.i("----------------------------------------------------------------", "Query Called")
        val userRef = db.collection("users").whereEqualTo("uid", uid).get()

        var listOfTaskKeys = ArrayList<String>()

        userRef.addOnCompleteListener {
            listOfTaskKeys =
                it.result.documents[0].data!!["tasks"] as ArrayList<String> /* = java.util.ArrayList<kotlin.String> */

            if (listOfTaskKeys.isNotEmpty()) {
                val res = db.collection("tasks").whereIn(documentId(), listOfTaskKeys).get()

                userRef.addOnSuccessListener {
                    Log.i(
                        "----------------------------------------------------------------",
                        "res2: " + listOfTaskKeys
                    )
                }

                res.addOnCompleteListener {
                    for (i in 0..it.getResult().documents.size - 1) {
                        val doc = res.getResult().documents.get(i).data
                        val model = TaskModel(
                            title = doc!!.get("title").toString(),
                            isCompleted = doc.get("completed") as Boolean,
                            user_id = doc.get("user_id").toString(),
                            note = doc.get("note").toString(),
                            uid = res.getResult().documents.get(i).id,
                            list_id = doc.get("list_id").toString()
                        )
                        userTasks.add(model)
                    }
                    Log.i(
                        "----------------------------------------------------------------",
                        "User tasks are changed, new size are: " + tasksLiveData.value?.size.toString()
                    )
                }
            }

            tasksLiveData.value = userTasks

            Log.i(
                "----------------------------------------------------------------",
                "Query Finished"
            )
        }
        return temp
    }

    override fun getTasksFromStorage(): ArrayList<TaskModel> {
        return userTasks
    }

    override fun removeTasks(tasks: ArrayList<TaskModel>) {
        val docRef = db.collection("tasks")

        for (task in tasks) {
            docRef.document(task.uid).delete().addOnCompleteListener {
                when {
                    it.isSuccessful -> {
                        userTasks.remove(task)
                        Log.i(
                            "----------------------------------------------------------------",
                            "Here"
                        )
                        tasksLiveData.value = userTasks
                    }
                }
            }
        }
    }
}