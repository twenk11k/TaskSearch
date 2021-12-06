package com.twenk11k.tasksearch.repository

import androidx.annotation.WorkerThread
import com.google.gson.Gson
import com.twenk11k.tasksearch.data.model.TaskItem
import com.twenk11k.tasksearch.data.model.TaskSearchRequest
import com.twenk11k.tasksearch.data.network.TaskSearchService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class MainRepository @Inject constructor(private val taskSearchService: TaskSearchService) {

    @WorkerThread
    fun getTaskSearch(
        query: String,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) = flow {
        try {
            val response = taskSearchService.fetchTaskSearchResponse(
                filter = Gson().toJson(
                    TaskSearchRequest(
                        text = query
                    )
                )
            )
            response.body()?.results?.let {
                val list = ArrayList<TaskItem>()
                for (curr in it.tasks) {
                    var projectName = ""
                    for (currSection in it.sections) {
                        if (currSection.id == curr.sectionId) {
                            for (currProject in it.projects) {
                                if (currSection.projectId == currProject.id) {
                                    projectName = currProject.name
                                }
                                break
                            }
                            break
                        }
                    }
                    if (projectName.isNotBlank()) {
                        list.add(TaskItem(curr.name, projectName))
                    }
                }
                emit(list)
            }
        } catch (e: Exception) {
            onError(e.message)
        }
    }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(Dispatchers.IO)

}