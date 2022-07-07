package com.example.zaribatodolist.domain.usecase.logic

import javax.inject.Inject

class IsValidShareDataUseCase @Inject constructor() {
    fun invoke(gmail: String, listOfTasks: ArrayList<String>) : Boolean{
        if(gmail.isBlank() || gmail.isEmpty()){
            return false
        }
        if(listOfTasks.isEmpty()){
            return false
        }

        return true
    }
}