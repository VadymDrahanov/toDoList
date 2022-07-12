package com.example.zaribatodolist.domain.usecase.userrepo

import com.example.zaribatodolist.domain.repository.UserRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
import javax.inject.Inject

class ShareTasksUseCase @Inject constructor(private val userRepository: UserRepository) {
    suspend fun invoke(userGmail: String, listOfTasks: ArrayList<String>) : Task<QuerySnapshot> {
        return userRepository.shareNewTask(userGmail, listOfTasks)
    }
}