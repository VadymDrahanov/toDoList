package com.example.zaribatodolist.presentation.auth

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.zaribatodolist.data.model.User
import com.example.zaribatodolist.domain.usecase.LoginWithGoogleUseCase
import com.example.zaribatodolist.domain.usecase.GetGoogleSignInClientUseCase
import com.example.zaribatodolist.domain.usecase.GetUserInfoUseCase
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
    private val saveNewUserUseCase: SaveNewUserUseCase,
    private val getUserInfoUseCase: GetUserInfoUseCase
) :
    BaseViewModel<AuthUIState>() {

    fun getGoogleSignIn(): GoogleSignInClient {

        return getGoogleSignInClient.invoke()
    }

    fun firebaseAuthWithGoogle(idToken: String) {
        uistate.value = AuthUIState(true, false)

        viewModelScope.launch {
            try {
                loginWithGoogleUseCase.invoke(idToken)?.addOnCompleteListener {
                    when {
                        it.isSuccessful -> {
                            if (it.result.additionalUserInfo!!.isNewUser) {
                                it.result.user!!.let {
                                    saveNewUser(it)
                                }
                            } else {
                                viewModelScope.launch {
                                    getUserInfoUseCase(it.getResult().user!!.uid)!!.addOnCompleteListener { getUser ->
                                        when {
                                            getUser.isSuccessful -> {
                                                uistate.value = AuthUIState(false, true)
                                            }
                                            getUser.isCanceled -> {
                                                uistate.value = AuthUIState(false, false)
                                                Log.i("Error", "Something went wrong")
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        it.isCanceled -> {
                            uistate.value = AuthUIState(false, false)
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
                        tasks = ArrayList(),
                        name = firebaseUser.displayName,
                        newUser = false,
                        photoUrl = firebaseUser.photoUrl
                    )
                )?.addOnCompleteListener { task ->
                    when {
                        task.isSuccessful ->{
                            viewModelScope.launch {
                                getUserInfoUseCase.invoke(firebaseUser.uid)
                            }
                            uistate.value = AuthUIState(false, true)
                        }
                        task.isCanceled ->{
                            uistate.value = AuthUIState(false, false)
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

    private fun handleError() {

    }
}
