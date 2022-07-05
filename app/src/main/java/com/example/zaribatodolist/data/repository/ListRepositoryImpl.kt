package com.example.zaribatodolist.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.zaribatodolist.data.model.ListModel
import com.example.zaribatodolist.data.model.SaveParamList
import com.example.zaribatodolist.data.model.TaskModel
import com.example.zaribatodolist.domain.repository.ListRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ListRepositoryImpl : ListRepository{
    private val db = Firebase.firestore

    var userLists: ArrayList<ListModel> = ArrayList()

    override val listsLiveData: MutableLiveData<ArrayList<ListModel>> = MutableLiveData()
    override val currentList: MutableLiveData<String> = MutableLiveData()

//    override lateinit var currentList : String

    override suspend fun addList(list: SaveParamList): Task<DocumentReference> {
        return db.collection("lists").add(list).addOnCompleteListener {
            when {
                it.isSuccessful -> {
                    userLists.add(
                        ListModel(
                            title = list.title,
                            id = it.getResult().id,
                            user_id = list.user_id
                        )
                    )
                    listsLiveData.value = userLists
                }
            }
        }

    }



    override suspend fun getLists(uid: String): Task<QuerySnapshot> {

        userLists.clear()
        listsLiveData.value?.clear()

        Log.i("----------------------------------------------------------------", "Query Called")
        val res = db.collection("lists").whereEqualTo("user_id", uid).get()

        res.addOnCompleteListener {
            for (i in 0..it.getResult().documents.size - 1) {
                val doc = res.getResult().documents.get(i).data
                val model = ListModel(
                    title = doc!!.get("title").toString(),
                    user_id = doc.get("user_id").toString(),
                    id = res.getResult().documents.get(i).id
                )
                userLists.add(model)
            }
            Log.i(
                "----------------------------------------------------------------",
                "User lists are changed, new size are: " + listsLiveData.value?.size.toString()
            )
        }

        listsLiveData.value = userLists

        Log.i("----------------------------------------------------------------", "Query Finished")

        return res
    }
}