package com.example.coursework.models

open class Exercise(
    open val id: Int,
    open val name: String,
    open val type: ExerciseType,
    open val mathTopicId: Int,
    open val description: String,
    open val hint: String,
    open var isCompleted: Boolean
) {
    fun complete() {
        isCompleted = true
    }
}