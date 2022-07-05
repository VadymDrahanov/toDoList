package com.example.zaribatodolist.domain.repository

import androidx.lifecycle.MutableLiveData
import com.example.zaribatodolist.data.model.ListModel
import com.example.zaribatodolist.data.model.SaveParamList
import com.example.zaribatodolist.data.model.SaveTaskParam
import com.example.zaribatodolist.data.model.TaskModel
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.QuerySnapshot

interface ListRepository {
    val listsLiveData: MutableLiveData<ArrayList<ListModel>>
    val currentList: MutableLiveData<String>

    //var currentList: String
    suspend fun addList(list: SaveParamList): Task<DocumentReference>
    suspend fun getLists(uid: String): Task<QuerySnapshot>
}