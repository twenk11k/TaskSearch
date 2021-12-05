package com.twenk11k.tasksearch.data.network

import com.twenk11k.tasksearch.data.model.TaskSearchResponse
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface TaskSearchService {

    @POST("search")
    suspend fun fetchTaskSearchResponse(
        @Query("filter") filter: String,
        @Query("response_format") responseFormat: String = "object"
    ): Response<TaskSearchResponse>

}