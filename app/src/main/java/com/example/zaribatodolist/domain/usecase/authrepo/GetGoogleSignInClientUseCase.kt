package com.example.zaribatodolist.domain.usecase.authrepo

import com.example.zaribatodolist.domain.repository.AuthRepository
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import javax.inject.Inject

class GetGoogleSignInClientUseCase  @Inject constructor(private val repository: AuthRepository){

    @Throws(Exception::class)
    operator fun invoke() : GoogleSignInClient {
        return repository.getGoogleSignInClient()
    }
}