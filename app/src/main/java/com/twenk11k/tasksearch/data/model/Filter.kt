package com.twenk11k.tasksearch.data.model

enum class Filter(val status: Int?) {
    ALL(null),
    ACTIVE(1),
    ARCHIVED(18)
}