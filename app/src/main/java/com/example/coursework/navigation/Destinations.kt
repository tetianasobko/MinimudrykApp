package com.example.coursework.navigation

interface Destinations {
    val route: String
}

object Topics : Destinations {
    override val route = "TopicsScreen"
}

object Exercises : Destinations {
    override val route = "ExercisesScreen"
    const val argTopicId = "topicId"
}

object OptionsQuiz: Destinations {
    override val route: String = "OptionsQuizScreen"
    const val argTopicId = "topicId"
    const val argExerciseId = "exerciseId"
}

object InputQuiz: Destinations {
    override val route: String = "InputQuizScreen"
    const val argTopicId = "topicId"
    const val argExerciseId = "exerciseId"
}

object Games : Destinations {
    override val route = "GameScreen"
    const val argTopicId = "topicId"
    const val argExerciseId = "exerciseId"
}
