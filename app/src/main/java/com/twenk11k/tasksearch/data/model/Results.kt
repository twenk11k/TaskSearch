package com.twenk11k.tasksearch.data.model

data class Results(
    val tasks: List<Task>,
    val sections: List<Section>,
    val projects: List<Project>
)