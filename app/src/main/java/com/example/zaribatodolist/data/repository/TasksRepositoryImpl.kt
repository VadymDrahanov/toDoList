package com.example.zaribatodolist.data.repository

import android.content.ContentValues.TAG
import android.util.Log
import com.example.zaribatodolist.data.model.Task
import com.example.zaribatodolist.domain.repository.TaskRepository
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class TasksRepositoryImpl : TaskRepository {

    private var db = Firebase.firestore

    override fun addTask(task: Task, userUid: String): Boolean {
        db.collection("tasks").add(task).addOnSuccessListener { documentReference ->
            Log.w(TAG,"DocumentSnapshot added with ID:${documentReference.id}")
        }
        return true
    }

    override fun removeTask() {
        TODO("Not yet implemented")
    }

    override fun getAllTasks(): ArrayList<Task> {
        var result : ArrayList<Task> = emptyList<Task>() as ArrayList<Task>
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
                        val c: Task? = d.toObject(Task::class.java)

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
}