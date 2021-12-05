package com.twenk11k.tasksearch.di

import com.twenk11k.tasksearch.data.network.TaskSearchService
import com.twenk11k.tasksearch.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideMainRepository(
        taskSearchService: TaskSearchService
    ) = MainRepository(taskSearchService)

}