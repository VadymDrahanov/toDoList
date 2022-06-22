package com.example.zaribatodolist.di

import com.example.zaribatodolist.domain.repository.UserRepository
import com.example.zaribatodolist.domain.usecase.SaveNewUserUseCase
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
}