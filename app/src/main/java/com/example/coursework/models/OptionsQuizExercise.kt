package com.example.coursework.models

data class OptionsQuizExercise(
    override var id: Long,
    override val name: String,
    override val mathTopicId: Long,
    override var description: String,
    override val hint: String,
    override var isCompleted: Boolean,
    val options: List<String>,
    var correctOption: Int
) : Exercise(id, name, ExerciseType.OptionsQuizType, mathTopicId, description, hint, isCompleted) {
}