package com.example.zaribatodolist.domain.usecase.userrepo

import com.example.zaribatodolist.data.model.User
import com.example.zaribatodolist.domain.repository.UserRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import javax.inject.Inject

class SaveNewUserUseCase @Inject constructor(private val userRepository: UserRepository) {

    suspend operator fun invoke(user: User) : Task<Void>? {

        return userRepository.createUser(user)
    }
}