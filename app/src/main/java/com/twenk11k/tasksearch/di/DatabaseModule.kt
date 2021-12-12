package com.twenk11k.tasksearch.di

import android.app.Application
import androidx.room.Room
import com.twenk11k.tasksearch.R
import com.twenk11k.tasksearch.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            application.getString(R.string.database_name)
        ).fallbackToDestructiveMigration().build()
    }

}