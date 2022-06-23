package com.example.zaribatodolist.presentation.auth

import androidx.lifecycle.viewModelScope
import com.example.zaribatodolist.data.model.Task
import com.example.zaribatodolist.data.model.User
import com.example.zaribatodolist.domain.usecase.LoginWithGoogleUseCase
import com.example.zaribatodolist.domain.usecase.GetGoogleSignInClientUseCase
import com.example.zaribatodolist.domain.usecase.SaveNewUserUseCase
import com.example.zaribatodolist.presentation.base.BaseViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginWithGoogleUseCase: LoginWithGoogleUseCase,
    private val getGoogleSignInClient: GetGoogleSignInClientUseCase,
    private val saveNewUserUseCase: SaveNewUserUseCase
) :
    BaseViewModel<AuthUIState>() {

    fun getGoogleSignIn(): GoogleSignInClient = getGoogleSignInClient.invoke()

    fun firebaseAuthWithGoogle(idToken: String) {
        uistate.value = AuthUIState(true, false)

        viewModelScope.launch {
            try {
                loginWithGoogleUseCase.invoke(idToken)?.addOnCompleteListener {
                    when {
                        it.isSuccessful -> {
                            if (it.result.additionalUserInfo!!.isNewUser) {
                                it.result.user?.let {
                                    saveNewUser(it)
                                } ?: {
                                    handleError()
                                }
                            } else {
                                uistate.value = AuthUIState(false, true)
                            }
                        }
                    }
                }
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
                handleError()
            }
        }
    }

    private fun saveNewUser(firebaseUser: FirebaseUser) {
        viewModelScope.launch {
            try {
                saveNewUserUseCase.invoke(
                    User(
                        uid = firebaseUser.uid,
                        email = firebaseUser.email,
                        tasks = ArrayList(),
                        name = firebaseUser.displayName,
                        isNewUser = false
                    )
                )?.addOnCompleteListener { task ->
                    when {
                        task.isSuccessful ->{
                            uistate.value = AuthUIState(false, true)
                        }
                    }
                }
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
                handleError()
            }
        }
    }

    private fun handleError() {

    }
}
