package com.example.coursework.models

data class OptionsQuizExercise(
    override val id: Int,
    override val name: String,
    override val description: String,
    override val hint: String,
    override var isCompleted: Boolean,
    val options: List<String>,
    val correctOption: Int
) : Exercise(id, name, ExerciseType.OptionsQuiz, description, hint, isCompleted) {
}