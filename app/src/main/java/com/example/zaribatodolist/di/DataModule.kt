package com.example.zaribatodolist.di

import android.content.Context
import com.example.zaribatodolist.data.repository.AuthRepositoryImpl
import com.example.zaribatodolist.data.repository.ListRepositoryImpl
import com.example.zaribatodolist.data.repository.TasksRepositoryImpl
import com.example.zaribatodolist.data.repository.UserRepositoryImpl
import com.example.zaribatodolist.domain.repository.AuthRepository
import com.example.zaribatodolist.domain.repository.ListRepository
import com.example.zaribatodolist.domain.repository.TaskRepository
import com.example.zaribatodolist.domain.repository.UserRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
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
    fun provideFirestore(): FirebaseFirestore = Firebase.firestore

    @Provides
    @Singleton
    fun provideTaskRepository(fireStore: FirebaseFirestore): TaskRepository =
        TasksRepositoryImpl(
            fireStore
        )

    @Provides
    @Singleton
    fun provideAuthRepository(@ApplicationContext context: Context): AuthRepository =
        AuthRepositoryImpl(context = context)

    @Provides
    @Singleton
    fun provideUserRepository(): UserRepository = UserRepositoryImpl()

    @Provides
    @Singleton
    fun provideListRepository(): ListRepository = ListRepositoryImpl()

}
