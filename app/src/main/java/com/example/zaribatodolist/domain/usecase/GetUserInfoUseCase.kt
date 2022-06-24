package com.example.zaribatodolist.domain.usecase

import com.example.zaribatodolist.domain.repository.UserRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import javax.inject.Inject

class GetUserInfoUseCase  @Inject constructor(private val userRepository: UserRepository){
    suspend operator fun invoke(uid: String) : Task<DocumentSnapshot>?{
        return userRepository.sendGetUserRequest(uid)
    }
}