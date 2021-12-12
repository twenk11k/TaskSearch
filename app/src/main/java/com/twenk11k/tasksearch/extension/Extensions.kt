package com.twenk11k.tasksearch.extension

import com.twenk11k.tasksearch.data.model.Results
import com.twenk11k.tasksearch.data.model.TaskItem

fun String.removeNonAlphaNumericCharacters(): String {
    return this.toCharArray()
        .filter { it.isWhitespace() || it.isLetterOrDigit() }
        .joinToString(separator = "")
}

fun Results.generateTaskItemList(): List<TaskItem> {
    val list = arrayListOf<TaskItem>()
    this.tasks.forEach { task ->
        this.sections.forEach sections@{ section ->
            if (section.id == task.sectionId) {
                this.projects.forEach { project ->
                    if (section.projectId == project.id) {
                        list.add(TaskItem(task.id, task.name, project.name, task.status))
                        return@sections
                    }
                }
            }
        }
    }
    return list
}