package com.example.coursework.models

data class InputQuizExercise(
    override var id: Long,
    override val name: String,
    override val mathTopicId: Long,
    override val description: String,
    override val hint: String,
    override var isCompleted: Boolean,
    val correctAnswer: String?
) : Exercise(id, name, ExerciseType.InputQuizType, mathTopicId, description, hint, isCompleted) {
}