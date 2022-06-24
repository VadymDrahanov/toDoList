package com.example.zaribatodolist.di

import com.example.zaribatodolist.domain.repository.AuthRepository
import com.example.zaribatodolist.domain.repository.UserRepository
import com.example.zaribatodolist.domain.usecase.*
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

    @Provides
    fun provideSignOutUseCase(authRepository: AuthRepository): SignOutUseCase {
        return SignOutUseCase(authRepository = authRepository)
    }

    @Provides
    fun provideGetUserFromStorage(userRepository: UserRepository): GetUserInfoFromStorage {
        return GetUserInfoFromStorage(userRepository = userRepository)
    }
}