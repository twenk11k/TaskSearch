package com.twenk11k.tasksearch.data.repository

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
        status: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) = flow {
        try {
            val list = arrayListOf<TaskItem>()
            if (query.isNotEmpty()) {
                val response = taskSearchService.fetchTaskSearchResponse(
                    filter = Gson().toJson(
                        TaskSearchRequest(
                            text = query,
                            status = if (status != 0) status else null
                        )
                    )
                )
                response.body()?.results?.let {
                    it.tasks.forEach { task ->
                        it.sections.forEach { section ->
                            if (section.id == task.sectionId) {
                                it.projects.forEach { project ->
                                    if (section.projectId == project.id) {
                                        list.add(TaskItem(task.name, project.name))
                                    }
                                }
                            }
                        }
                    }
                }
            }
            emit(list)
        } catch (e: Exception) {
            onError(e.message)
        }
    }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(Dispatchers.IO)

}