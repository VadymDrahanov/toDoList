package com.example.zaribatodolist.domain.usecase

import com.example.zaribatodolist.data.model.User
import com.example.zaribatodolist.domain.repository.UserRepository
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class SaveNewUserUseCase @Inject constructor(private val userRepository: UserRepository) {
    fun execute(firebaseUser: FirebaseUser,  listener: () -> Unit) {
        userRepository.createUser(User(firebaseUser.uid, firebaseUser.email, ArrayList(), firebaseUser.displayName)) {
            listener.invoke()
        }
    }
}