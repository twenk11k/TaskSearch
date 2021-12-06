package com.twenk11k.tasksearch.ui.main

import com.twenk11k.tasksearch.data.model.TaskItem

interface TaskItemClickListener {
    fun showTaskDetails(taskItem: TaskItem)
}