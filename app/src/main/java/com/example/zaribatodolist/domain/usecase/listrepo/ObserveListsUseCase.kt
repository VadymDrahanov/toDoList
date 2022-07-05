package com.example.zaribatodolist.domain.usecase.listrepo

import androidx.lifecycle.MutableLiveData
import com.example.zaribatodolist.data.model.ListModel
import com.example.zaribatodolist.domain.repository.ListRepository
import javax.inject.Inject

class ObserveListsUseCase @Inject constructor(private val repository: ListRepository) {
    var userLists: MutableLiveData<ArrayList<ListModel>> = repository.listsLiveData
}