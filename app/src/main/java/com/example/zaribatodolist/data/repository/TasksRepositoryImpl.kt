package com.example.zaribatodolist.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.zaribatodolist.data.model.SaveTaskParam
import com.example.zaribatodolist.data.model.TaskModel
import com.example.zaribatodolist.domain.repository.TaskRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FieldPath.documentId
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow


class TasksRepositoryImpl(
    private val fireStore: FirebaseFirestore
) : TaskRepository {
    private val TAG = "Query TAG"

    private var db = Firebase.firestore

    var userTasks: ArrayList<TaskModel> = ArrayList()
    override val tasksLiveData: MutableLiveData<ArrayList<TaskModel>> = MutableLiveData()

    override fun getTasks1(userId: String): Flow<MutableList<TaskModel>> = callbackFlow {
        var eventsCollection: Query? = null
        try {
            eventsCollection = fireStore.collection("tasks").whereEqualTo("user_id", userId)
        } catch (e: Throwable) {
            close(e)
        }
        val listener = eventsCollection?.addSnapshotListener { snapshot, _ ->
            if (snapshot == null) return@addSnapshotListener

            try {
                val list = mutableListOf<TaskModel>()

                snapshot.documents.forEach {
                    val model = it.toObject(TaskModel::class.java)
                    model?.uid = it.id

                    if (model != null) {
                        list.add(model)
                    }
                }

                trySend(list)
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }

        awaitClose {
            listener?.remove()
        }
    }

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
        tasksLiveData.value = userTasks
        //val temp = db.collection("tasks").get()
        lateinit var taskResult: Task<QuerySnapshot>

        taskResult = db.collection("users").whereEqualTo("uid", uid).get()
            .addOnCompleteListener { userQuery ->
                when {
                    userQuery.isSuccessful -> {

                        taskResult = userQuery

                        val listOfTaskKeys =
                            userQuery.result.documents[0].data?.get("tasks") as ArrayList<*>

                        if (listOfTaskKeys.isNotEmpty()) {
                            db.collection("tasks").whereIn(documentId(), listOfTaskKeys).get()
                                .addOnCompleteListener { taskQuery ->
                                    Log.i(TAG, "Query Call")

                                    when {
                                        taskQuery.isSuccessful -> {
                                            Log.i(TAG, "Query is Successful")

                                            taskResult = taskQuery
                                            for (i in 0..taskQuery.getResult().documents.size - 1) {

                                                val doc =
                                                    taskQuery.getResult().documents.get(i).data

                                                val model = TaskModel(
                                                    title = doc!!.get("title").toString(),
                                                    isCompleted = doc.get("completed") as Boolean,
                                                    user_id = doc.get("user_id").toString(),
                                                    note = doc.get("note").toString(),
                                                    uid = taskQuery.getResult().documents.get(i).id,
                                                    list_id = doc.get("list_id").toString()
                                                )
                                                userTasks.add(model)
                                                tasksLiveData.value = userTasks
                                            }
                                        }
                                        taskQuery.isCanceled -> {
                                            Log.i(TAG, "Query is canceled")
                                            taskResult = taskQuery
                                        }

                                    }
                                }
                        }
                    }
                    userQuery.isCanceled -> {
                        taskResult = userQuery
                    }
                }

            }
        return taskResult
//        var listOfTaskKeys = ArrayList<String>()

//        userRef.addOnCompleteListener {
//            listOfTaskKeys =
//                it.result.documents[0].data!!["tasks"] as ArrayList<String> /* = java.util.ArrayList<kotlin.String> */
//
//            if (listOfTaskKeys.isNotEmpty()) {
//                val res = db.collection("tasks").whereIn(documentId(), listOfTaskKeys).get()
//
//                userRef.addOnSuccessListener {
//                    Log.i(
//                        "Query status",
//                        "res2: " + listOfTaskKeys
//                    )
//                }
//
//                res.addOnCompleteListener {
//                    for (i in 0..it.getResult().documents.size - 1) {
//                        val doc = res.getResult().documents.get(i).data
//                        val model = TaskModel(
//                            title = doc!!.get("title").toString(),
//                            isCompleted = doc.get("completed") as Boolean,
//                            user_id = doc.get("user_id").toString(),
//                            note = doc.get("note").toString(),
//                            uid = res.getResult().documents.get(i).id,
//                            list_id = doc.get("list_id").toString()
//                        )
//                        userTasks.add(model)
//                    }
//                    Log.i(
//                        "----------------------------------------------------------------",
//                        "User tasks are changed, new size are: " + tasksLiveData.value?.size.toString()
//                    )
//                }
//            }
//
//            tasksLiveData.value = userTasks
//

//            )
//        }
//        return temp
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