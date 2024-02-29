package com.example.coursework.models

interface Exercise {
    val id: Int
    val name: String
    val type: ExerciseType
    val topicId: Int
    val description: String
    val hint: String
    val isCompleted: Boolean
}