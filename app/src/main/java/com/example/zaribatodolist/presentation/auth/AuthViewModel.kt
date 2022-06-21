package com.example.zaribatodolist.presentation.auth

import com.example.zaribatodolist.data.model.User
import com.example.zaribatodolist.domain.reposotory.AuthRepository
import com.example.zaribatodolist.domain.reposotory.UserRepository
import com.example.zaribatodolist.presentation.base.BaseViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepos: AuthRepository,
    private val userRepos: UserRepository
) : BaseViewModel() {


    fun getGoogleSignInGoogle(): GoogleSignInClient {
        return authRepos.getGoogleSignInClient()
    }

    fun firebaseAuthWithGoogle(idToken: String): Boolean {
        authRepos.firebaseAuthWithGoogle(idToken) { user, isNew ->
            if (user != null) {
                authState.value = "authenticated"
                if (isNew == true) {
                    userRepos.createUser(User(user.uid, user.email, null, user.displayName))
                }
            }
        }
        return true
    }

}
