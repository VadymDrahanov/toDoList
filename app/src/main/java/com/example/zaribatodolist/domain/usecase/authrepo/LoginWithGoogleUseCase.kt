package com.example.zaribatodolist.domain.usecase.authrepo

import com.example.zaribatodolist.domain.repository.AuthRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import javax.inject.Inject

class LoginWithGoogleUseCase @Inject constructor(private val repository: AuthRepository) {

    @Throws(Exception::class)
    suspend operator fun invoke(accessToken: String?) : Task<AuthResult>? {
        if(accessToken == null){
            throw Exception()
        }

        return repository.loginWithGoogle(accessToken = accessToken)
    }
}