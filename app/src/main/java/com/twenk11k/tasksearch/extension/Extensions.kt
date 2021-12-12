package com.twenk11k.tasksearch.extension

fun String.removeNonAlphaNumericCharacters(): String {
    return this.toCharArray()
        .filter { it.isLetterOrDigit() }
        .joinToString(separator = "")
}