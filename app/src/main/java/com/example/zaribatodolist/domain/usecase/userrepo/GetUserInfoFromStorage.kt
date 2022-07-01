package com.example.zaribatodolist.domain.usecase.userrepo

import com.example.zaribatodolist.data.model.User
import com.example.zaribatodolist.domain.repository.UserRepository
import javax.inject.Inject

class GetUserInfoFromStorage @Inject constructor(private val userRepository: UserRepository) {
    fun invoke() : User? {
        return userRepository.currentUser
    }
}

