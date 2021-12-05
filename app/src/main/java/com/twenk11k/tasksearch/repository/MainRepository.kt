package com.twenk11k.tasksearch.repository

import com.twenk11k.tasksearch.data.network.TaskSearchService
import javax.inject.Inject

class MainRepository @Inject constructor(private val taskSearchService: TaskSearchService)