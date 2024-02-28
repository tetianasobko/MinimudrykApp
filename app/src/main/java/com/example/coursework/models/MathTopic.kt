package com.example.coursework.models

data class MathTopic(
    val id: Int,
    val name: String,
    val exercises: MutableList<Exercise> = mutableListOf()
) {
}