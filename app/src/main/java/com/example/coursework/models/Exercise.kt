package com.example.coursework.models

open class Exercise(
    open var id: Long,
    open val name: String,
    open val type: ExerciseType,
    open val mathTopicId: Long,
    open val description: String,
    open val hint: String,
    open var isCompleted: Boolean
) {
    fun complete() {
        isCompleted = true
    }
}