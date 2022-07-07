package com.example.zaribatodolist.domain.usecase.userrepo

import com.example.zaribatodolist.domain.repository.UserRepository
import javax.inject.Inject

class ShareTasksUseCase @Inject constructor(private val userRepository: UserRepository) {
    fun invoke(userGmail: String, listOfTasks: ArrayList<String>){
        userRepository.shareTask(userGmail, listOfTasks)
    }
}