package com.example.zaribatodolist.presentation.auth

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.zaribatodolist.data.model.User
import com.example.zaribatodolist.domain.usecase.authrepo.GetGoogleSignInClientUseCase
import com.example.zaribatodolist.domain.usecase.authrepo.LoginWithGoogleUseCase
import com.example.zaribatodolist.domain.usecase.listrepo.GetListsUseCase
import com.example.zaribatodolist.domain.usecase.taskrepo.GetUserTasksUseCase
import com.example.zaribatodolist.domain.usecase.userrepo.GetUserInfoUseCase
import com.example.zaribatodolist.domain.usecase.userrepo.SaveNewUserUseCase
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
    private val getUserTasksUseCase: GetUserTasksUseCase,
    private val getListsUseCase: GetListsUseCase,
    private val saveNewUserUseCase: SaveNewUserUseCase,
    private val getUserInfoUseCase: GetUserInfoUseCase,
) :
    BaseViewModel<AuthUIState>() {

    fun getGoogleSignIn(): GoogleSignInClient {
        return getGoogleSignInClient.invoke()
    }

    fun firebaseAuthWithGoogle(idToken: String) {
        uiState.value = AuthUIState(true, false)

        viewModelScope.launch {
            try {
                loginWithGoogleUseCase.invoke(idToken)?.addOnCompleteListener {
                    when {
                        it.isSuccessful -> {
                            getUserInfo(it.getResult().user!!.uid)
                            if (it.result.additionalUserInfo!!.isNewUser) {
                                it.result.user!!.let {
                                    saveNewUser(it)
                                }
                            } else {
                                getUserInfo(it.getResult().user!!.uid)
                            }
                        }
                        it.isCanceled -> {
                            uiState.value = AuthUIState(false, false)
                            Log.i("Error", "Something went wrong")
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
                        name = firebaseUser.displayName,
                        photoUrl = firebaseUser.photoUrl
                    )
                )?.addOnCompleteListener { task ->
                    when {
                        task.isSuccessful -> {
                            viewModelScope.launch {
                                getUserInfoUseCase.invoke(firebaseUser.uid)
                            }
                            uiState.value = AuthUIState(false, true)
                        }
                        task.isCanceled -> {
                            uiState.value = AuthUIState(false, false)
                            Log.i("Error", "Something went wrong")
                        }
                    }
                }
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
                handleError()
            }
        }
    }

    fun getUserInfo(uid: String) {
        uiState.value = AuthUIState(true, false)

        viewModelScope.launch {
            getUserTasksUseCase.invoke(uid).addOnCompleteListener {
                when {
                    it.isSuccessful -> {
                        viewModelScope.launch {

                            getListsUseCase.invoke(uid).addOnCompleteListener { list_query ->
                                when {
                                    list_query.isSuccessful -> {
                                        uiState.value = AuthUIState(false, true)
                                    }
                                    list_query.isCanceled -> {
                                        uiState.value = AuthUIState(false, false)
                                        Log.i("Error", "Something went wrong")
                                    }
                                }
                            }
                        }
                    }
                    it.isCanceled -> {
                        uiState.value = AuthUIState(false, false)
                        Log.i("Error", "Something went wrong")
                    }
                }
            }
            getUserInfoUseCase(uid)!!.addOnCompleteListener { getUser ->
                when {
                    getUser.isSuccessful -> {
                        Log.i("Error", "All Okey")
                    }
                    getUser.isCanceled -> {
                        uiState.value = AuthUIState(false, false)
                        Log.i("Error", "Something went wrong")
                    }
                }
            }
        }
    }

    private fun handleError() {

    }
}
