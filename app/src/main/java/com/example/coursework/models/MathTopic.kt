package com.example.coursework.models

data class MathTopic(
    var id: Long,
    val name: String,
    var exercises: List<Exercise> = mutableListOf()
) {
    fun findExercise(id: Long) = exercises.first { it.id == id }

    fun getProgress(): Int {
        var count = 0
        for(exercise in exercises) {
            if (exercise.isCompleted) count++
        }
        if (exercises.size != 0) {
            return count * 100 / exercises.size
        } else {
            return  0
        }
    }
}
