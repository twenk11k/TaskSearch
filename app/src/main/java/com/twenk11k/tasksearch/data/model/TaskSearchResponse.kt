package com.twenk11k.tasksearch.data.model

data class TaskSearchResponse(
    val results: Results,
) {
    data class Results(
        val tasks: List<Task>,
        val sections: List<Section>,
        val projects: List<Project>
    )
}
