package com.twenk11k.tasksearch.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.twenk11k.tasksearch.data.model.TaskItem

@Database(entities = [TaskItem::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun taskSearchDao(): TaskSearchDao

}