package com.example.coursework.models

data class MathTopic(
    val id: Int,
    val name: String,
    val exercises: MutableList<Exercise> = mutableListOf()
) {
    fun getProgress(): Int {
        var count = 0
        for(exercise in exercises) {
            if (exercise.isCompleted) count++
        }
        return count * 100 / exercises.size
    }
}
