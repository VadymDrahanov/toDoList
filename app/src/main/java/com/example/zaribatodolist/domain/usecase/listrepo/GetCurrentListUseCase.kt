package com.example.zaribatodolist.domain.usecase.listrepo

import com.example.zaribatodolist.domain.repository.ListRepository
import javax.inject.Inject

class GetCurrentListUseCase @Inject constructor(private val repository: ListRepository) {
    fun invoke() : String{
        return repository.currentList.value!!
    }
}