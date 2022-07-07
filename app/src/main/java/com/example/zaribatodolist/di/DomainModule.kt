package com.example.zaribatodolist.di

import com.example.zaribatodolist.domain.repository.AuthRepository
import com.example.zaribatodolist.domain.repository.ListRepository
import com.example.zaribatodolist.domain.repository.TaskRepository
import com.example.zaribatodolist.domain.repository.UserRepository
import com.example.zaribatodolist.domain.usecase.authrepo.GetGoogleSignInClientUseCase
import com.example.zaribatodolist.domain.usecase.authrepo.LoginWithGoogleUseCase
import com.example.zaribatodolist.domain.usecase.authrepo.SignOutUseCase
import com.example.zaribatodolist.domain.usecase.listrepo.*
import com.example.zaribatodolist.domain.usecase.logic.FilterTaskByListUseCase
import com.example.zaribatodolist.domain.usecase.logic.FilterTasksUseCase
import com.example.zaribatodolist.domain.usecase.taskrepo.*
import com.example.zaribatodolist.domain.usecase.userrepo.*
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

    @Provides
    fun provideAddNewTaskUseCase(taskRepository: TaskRepository): AddNewTaskUseCase {
        return AddNewTaskUseCase(taskRepository = taskRepository)
    }

    @Provides
    fun provideGetUserTasksUseCase(taskRepository: TaskRepository): GetUserTasksUseCase {
        return GetUserTasksUseCase(taskRepository = taskRepository)
    }

    @Provides
    fun provideGetUserTasksFromStorageUseCase(taskRepository: TaskRepository): GetTasksFromStorage {
        return GetTasksFromStorage(tasksRepo = taskRepository)
    }

    @Provides
    fun provideTasksObserverUseCase(taskRepository: TaskRepository): ObservTasksUseCase {
        return ObservTasksUseCase(repo = taskRepository)
    }

    @Provides
    fun provideUpdateTaskCompletionUseCase(taskRepository: TaskRepository): UpdateTaskCompletionUseCase {
        return UpdateTaskCompletionUseCase(taskRepository = taskRepository)
    }

    @Provides
    fun provideUpdateTaskNoteUseCase(taskRepository: TaskRepository): UpdateTaskNoteUseCase {
        return UpdateTaskNoteUseCase(repo = taskRepository)
    }

    @Provides
    fun provideTasksFilterUseCase(taskRepository: TaskRepository): ObservAndFilterTasksUseCase {
        return ObservAndFilterTasksUseCase(repo = taskRepository)
    }

    @Provides
    fun provideGetListsUseCase(listRepository: ListRepository): GetListsUseCase {
        return GetListsUseCase(listRepository = listRepository)
    }

    @Provides
    fun provideCreateNewListUseCase(listRepository: ListRepository): CreateNewListUseCase {
        return CreateNewListUseCase(listRepository = listRepository)
    }


    @Provides
    fun provideObserveListUseCase(listRepository: ListRepository): ObserveListsUseCase {
        return ObserveListsUseCase(repository = listRepository)
    }

    @Provides
    fun provideGetCurrentListUseCase(listRepository: ListRepository): GetCurrentListUseCase {
        return GetCurrentListUseCase(repository = listRepository)
    }

    @Provides
    fun provideSetCurrentListUseCase(listRepository: ListRepository): SetCurrentListUseCase  {
        return SetCurrentListUseCase(repository = listRepository)
    }

    @Provides
    fun provideObserveCurrentListUseCase(listRepository: ListRepository): ObserveCurrentListUseCase  {
        return ObserveCurrentListUseCase(repository = listRepository)
    }

    @Provides
    fun provideFilterTaskUseCase(): FilterTasksUseCase  {
        return FilterTasksUseCase()
    }

    @Provides
    fun provideFilterTaskByListUseCase(): FilterTaskByListUseCase  {
        return FilterTaskByListUseCase()
    }

    @Provides
    fun provideRemoveTasksUseCase(taskRepository: TaskRepository) : RemoveTasksUseCase {
        return RemoveTasksUseCase(repository = taskRepository)
    }

    @Provides
    fun provideAddTaskToUserDocUseCase(userRepository: UserRepository) : AddNewTaskToUserUseCase {
        return AddNewTaskToUserUseCase(userRepository = userRepository)
    }

    @Provides
    fun provideShareTasksUseCase(userRepository: UserRepository) : ShareTasksUseCase {
        return ShareTasksUseCase(userRepository = userRepository)
    }
}