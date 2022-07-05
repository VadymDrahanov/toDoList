package com.example.zaribatodolist.domain.usecase.listrepo

import com.example.zaribatodolist.domain.repository.ListRepository
import javax.inject.Inject

class SetCurrentListUseCase @Inject constructor(private val repository: ListRepository) {
    fun invoke(id: String){
        repository.currentList.value = id
    }
}