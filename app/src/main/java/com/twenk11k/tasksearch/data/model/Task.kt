package com.twenk11k.tasksearch.data.model

import com.google.gson.annotations.SerializedName

data class Task(
    val name: String,
    @SerializedName("section_id")
    val sectionId: Int
)