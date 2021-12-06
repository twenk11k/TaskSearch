package com.twenk11k.tasksearch.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TaskItem(val name: String, val project: String) : Parcelable
