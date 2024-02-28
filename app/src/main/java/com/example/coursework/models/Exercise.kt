package com.example.coursework.models

data class Exercise(
    val id: Int,
    val name: String,
    val topicId: Int,
    val description: String,
    val hint: String,
    val isCompleted: Boolean
) {
}