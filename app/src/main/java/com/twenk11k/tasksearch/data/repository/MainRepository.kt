package com.twenk11k.tasksearch.data.repository

import android.content.Context
import androidx.annotation.WorkerThread
import com.google.gson.Gson
import com.twenk11k.tasksearch.data.model.Filter
import com.twenk11k.tasksearch.data.model.Results
import com.twenk11k.tasksearch.data.model.TaskItem
import com.twenk11k.tasksearch.data.model.TaskSearchRequest
import com.twenk11k.tasksearch.data.network.TaskSearchService
import com.twenk11k.tasksearch.db.TaskSearchDao
import com.twenk11k.tasksearch.extension.removeNonAlphaNumericCharacters
import com.twenk11k.tasksearch.util.Utils.isConnected
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val taskSearchService: TaskSearchService,
    private val taskSearchDao: TaskSearchDao,
    private val context: Context
) {

    @WorkerThread
    fun getTaskSearch(
        query: String,
        filter: Filter,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) = flow {
        try {
            val list = arrayListOf<TaskItem>()
            if (query.isNotEmpty()) {
                if (isConnected(context)) {
                    val response = taskSearchService.fetchTaskSearchResponse(
                        filter = Gson().toJson(TaskSearchRequest(query, filter.status))
                    )
                    response.body()?.results?.let {
                        list.addAll(generateTaskItemList(it))
                        taskSearchDao.insertTaskItemList(list)
                        emit(list)
                    }
                } else {
                    val modifiedQuery = "${query.trim().removeNonAlphaNumericCharacters()}%"
                    val response = if (filter == Filter.ALL) {
                        taskSearchDao.getTaskItemList(modifiedQuery)
                    } else {
                        taskSearchDao.getTaskItemListByStatus(modifiedQuery, filter.status!!)
                    }
                    emit(response)
                }
            } else {
                emit(list)
            }
        } catch (e: Exception) {
            onError(e.message)
        }
    }.onStart { onStart() }.onCompletion { onComplete() }.flowOn(Dispatchers.IO)

    private fun generateTaskItemList(results: Results): List<TaskItem> {
        val list = arrayListOf<TaskItem>()
        results.tasks.forEach { task ->
            results.sections.forEach { section ->
                if (section.id == task.sectionId) {
                    results.projects.forEach { project ->
                        if (section.projectId == project.id) {
                            list.add(TaskItem(task.id, task.name, project.name, task.status))
                        }
                    }
                }
            }
        }
        return list
    }

}