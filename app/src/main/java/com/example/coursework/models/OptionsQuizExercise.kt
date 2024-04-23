package com.example.coursework.models

data class OptionsQuizExercise(
    override var id: Long,
    override val name: String,
    override val mathTopicId: Long,
    override val description: String,
    override val hint: String,
    override var isCompleted: Boolean,
    val options: List<String>,
    val correctOption: Int
) : Exercise(id, name, ExerciseType.OptionsQuizType, mathTopicId, description, hint, isCompleted) {
}