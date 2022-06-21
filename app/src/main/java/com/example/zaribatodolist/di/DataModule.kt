package com.example.zaribatodolist.di

import android.content.Context
import com.example.zaribatodolist.data.repository.*
import com.example.zaribatodolist.domain.reposotory.AuthRepository
import com.example.zaribatodolist.domain.reposotory.TaskRepository
import com.example.zaribatodolist.domain.reposotory.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideAuthRepository(@ApplicationContext context: Context): AuthRepository =
        AuthRepositoryImpl(context = context)

    @Provides
    @Singleton
    fun provideTaskRepository(): TaskRepository = TasksRepositoryImpl()

    @Provides
    @Singleton
    fun provideUSerRepository(): UserRepository = UserRepositoryImpl()


}
