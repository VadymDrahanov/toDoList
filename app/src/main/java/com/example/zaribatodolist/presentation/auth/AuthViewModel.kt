package com.example.zaribatodolist.presentation.auth

import com.example.zaribatodolist.domain.repository.AuthRepository
import com.example.zaribatodolist.domain.usecase.SaveNewUserUseCase
import com.example.zaribatodolist.presentation.base.BaseViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepos: AuthRepository,
    private val saveNewUserUseCase: SaveNewUserUseCase
) : BaseViewModel<AuthUIState>() {

    fun getGoogleSignInGoogle(): GoogleSignInClient {
        return authRepos.getGoogleSignInClient()
    }

    fun firebaseAuthWithGoogle(idToken: String): Boolean {
        uistate.value = AuthUIState(true, false)

        authRepos.firebaseAuthWithGoogle(idToken) { user, isNew ->
            if (user != null) {
                if (isNew == true) {
                    saveNewUserUseCase.execute(user)

                        uistate.value = AuthUIState(false, true)
                }
            }else{
                uistate.value = AuthUIState(false, true)
            }
        }
        return true
    }

}
