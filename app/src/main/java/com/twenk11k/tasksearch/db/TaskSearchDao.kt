package com.twenk11k.tasksearch.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.twenk11k.tasksearch.data.model.TaskItem

@Dao
interface TaskSearchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTaskItemList(taskItemList: List<TaskItem>)

    @Query("SELECT * FROM tasks WHERE name LIKE :query")
    suspend fun getTaskItemList(query: String): List<TaskItem>

    @Query("SELECT * FROM tasks WHERE name LIKE :query AND status IN (:status)")
    suspend fun getTaskItemListByStatus(query: String, status: Int): List<TaskItem>

}