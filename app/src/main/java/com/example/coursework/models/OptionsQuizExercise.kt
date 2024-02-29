package com.example.coursework.models

class OptionsQuizExercise(
    override val id: Int,
    override val name: String,
    override val type: ExerciseType,
    override val topicId: Int,
    override val description: String,
    override val hint: String,
    override val isCompleted: Boolean,
    val options: List<String>,
    val correctOption: Int
) : Exercise {
}