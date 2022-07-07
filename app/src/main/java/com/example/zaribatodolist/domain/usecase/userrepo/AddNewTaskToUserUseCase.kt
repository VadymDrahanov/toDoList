package com.example.zaribatodolist.domain.usecase.userrepo

import com.example.zaribatodolist.domain.repository.UserRepository
import javax.inject.Inject

class AddNewTaskToUserUseCase @Inject constructor(private val userRepository: UserRepository) {
    fun invoke(id: String){
        userRepository.addNewTask(id)
    }
}