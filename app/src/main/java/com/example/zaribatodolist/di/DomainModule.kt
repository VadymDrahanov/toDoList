package com.example.zaribatodolist.di

import com.example.zaribatodolist.domain.repository.AuthRepository
import com.example.zaribatodolist.domain.repository.UserRepository
import com.example.zaribatodolist.domain.usecase.GetGoogleSignInClientUseCase
import com.example.zaribatodolist.domain.usecase.GetUserInfoUseCase
import com.example.zaribatodolist.domain.usecase.LoginWithGoogleUseCase
import com.example.zaribatodolist.domain.usecase.SaveNewUserUseCase
import com.google.firebase.firestore.auth.User
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {
    @Provides
    fun provideSaveNewUserUseCase(userRepository: UserRepository): SaveNewUserUseCase {
        return SaveNewUserUseCase(userRepository = userRepository)
    }

    @Provides
    fun provideLoginWithGoogleUseCase(authRepository: AuthRepository): LoginWithGoogleUseCase {
        return LoginWithGoogleUseCase(repository = authRepository)
    }

    @Provides
    fun provideGetGoogleSignInClientUseCase(authRepository: AuthRepository): GetGoogleSignInClientUseCase {
        return GetGoogleSignInClientUseCase(repository = authRepository)
    }

    @Provides
    fun provideGetUserInfoUseCase(userRepository: UserRepository): GetUserInfoUseCase {
        return GetUserInfoUseCase(userRepository = userRepository)
    }
}