package com.twenk11k.tasksearch.data.model

import com.google.gson.annotations.SerializedName

data class Task(
    val id: Int,
    val name: String,
    val status: Int,
    @SerializedName("section_id") val sectionId: Int
)