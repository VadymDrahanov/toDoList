package com.example.zaribatodolist.domain.usecase.listrepo

import android.util.Log
import com.example.zaribatodolist.domain.repository.ListRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
import javax.inject.Inject

class GetListsUseCase @Inject constructor(private val listRepository: ListRepository) {
    suspend fun invoke(uid: String): Task<QuerySnapshot> {
        return listRepository.getLists(uid)
    }
}