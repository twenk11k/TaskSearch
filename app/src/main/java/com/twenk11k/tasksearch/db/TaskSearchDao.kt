package com.twenk11k.tasksearch.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.twenk11k.tasksearch.data.model.TaskItem

@Dao
interface TaskSearchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTaskItemList(taskItemList: List<TaskItem>)

}