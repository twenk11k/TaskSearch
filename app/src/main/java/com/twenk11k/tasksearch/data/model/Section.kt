package com.twenk11k.tasksearch.data.model

import com.google.gson.annotations.SerializedName

data class Section(
    val id: Int,
    @SerializedName("project_id") val projectId: Int
)
