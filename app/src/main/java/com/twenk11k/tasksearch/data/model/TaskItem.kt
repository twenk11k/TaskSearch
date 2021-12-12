package com.twenk11k.tasksearch.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "tasks")
data class TaskItem(
    @PrimaryKey val id: Int,
    val name: String,
    val project: String,
    val status: Int
) : Parcelable
