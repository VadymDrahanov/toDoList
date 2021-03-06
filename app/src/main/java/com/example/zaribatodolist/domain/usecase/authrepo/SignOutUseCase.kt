package com.example.zaribatodolist.domain.usecase.authrepo

import com.example.zaribatodolist.domain.repository.AuthRepository
import javax.inject.Inject

class SignOutUseCase @Inject constructor(private val authRepository: AuthRepository) {
    fun invoke(){
        authRepository.signOut()
    }
}