package com.example.coursework.models

data class InputQuizExercise(
    override val id: Int,
    override val name: String,
    override val description: String,
    override val hint: String,
    override var isCompleted: Boolean,
    val correctAnswer: String
) : Exercise(id, name, ExerciseType.InputQuiz, description, hint, isCompleted) {
}