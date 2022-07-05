package com.example.zaribatodolist.domain.usecase.listrepo

import androidx.lifecycle.MutableLiveData
import com.example.zaribatodolist.domain.repository.ListRepository
import javax.inject.Inject

class ObserveCurrentListUseCase @Inject constructor(private val repository: ListRepository) {
    var currentLists: MutableLiveData<String> = repository.currentList

}