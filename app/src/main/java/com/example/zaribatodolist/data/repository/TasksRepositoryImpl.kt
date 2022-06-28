package com.example.zaribatodolist.data.repository

import android.util.Log
import com.example.zaribatodolist.data.model.TaskModel
import com.google.android.gms.tasks.Task

import com.example.zaribatodolist.domain.repository.TaskRepository
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase


class TasksRepositoryImpl : TaskRepository {
    private var db = Firebase.firestore
    override var userTasks: ArrayList<TaskModel>
        get() = TODO("Not yet implemented")
        set(value) {}



    override suspend fun addTask(task: TaskModel): Task<DocumentReference> {
        return db.collection("tasks").add(task)
    }

    override fun removeTask() {
        TODO("Not yet implemented")
    }

    override fun getAllTasks(): ArrayList<TaskModel> {
        var result : ArrayList<TaskModel> = emptyList<TaskModel>() as ArrayList<TaskModel>
        db.collection("tasks").get()
            .addOnSuccessListener { queryDocumentSnapshots ->
                // after getting the data we are calling on success method
                // and inside this method we are checking if the received
                // query snapshot is empty or not.
                if (!queryDocumentSnapshots.isEmpty) {
                    // if the snapshot is not empty we are
                    // hiding our progress bar and adding
                    // our data in a list.
                    val list = queryDocumentSnapshots.documents
                    for (d in list) {
                        // after getting this list we are passing
                        // that list to our object class.
                        val c: TaskModel? = d.toObject(TaskModel::class.java)

                        // and we will pass this object class
                        // inside our arraylist which we have
                        // created for recycler view.
                        result.add(c!!)
                    }
                    // after adding the data to recycler view.
                    // we are calling recycler view notifuDataSetChanged
                    // method to notify that data has been changed in recycler view.

                } else {
                    // if the snapshot is empty we are displaying a toast message.

                }
            }.addOnFailureListener { // if we do not get any data or any error we are displaying
                // a toast message that we do not get any data
            }
        return result
    }

    override fun updateTask() {
        TODO("Not yet implemented")
    }

    override suspend fun getTasks(uid: String) : Task<QuerySnapshot>{
        val res = db.collection("tasks")
            .whereEqualTo("user_id", uid).get()

        return res
    }
}