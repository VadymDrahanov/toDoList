package com.example.zaribatodolist.domain.usecase.listrepo

import com.example.zaribatodolist.data.model.SaveParamList
import com.example.zaribatodolist.domain.repository.ListRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import javax.inject.Inject

class CreateNewListUseCase @Inject constructor(private val listRepository: ListRepository){
    suspend operator fun invoke(list: SaveParamList) : Task<DocumentReference> {
        return listRepository.addList(list)
    }
}