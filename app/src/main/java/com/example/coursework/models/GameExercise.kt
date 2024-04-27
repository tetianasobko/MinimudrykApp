package com.example.coursework.models

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.coursework.composables.games.CalculatorGame
import com.example.coursework.composables.games.MultiplicativeRaceGame

data class GameExercise(
    override var id: Long,
    override val name: String,
    override val mathTopicId: Long,
    override var description: String,
    override var isCompleted: Boolean,
    val game: String
) : Exercise(id, name, ExerciseType.GameType, mathTopicId, description, "", isCompleted) {
    @Composable
    fun getGame(navController: NavController) {
        if (this.game == "Calculator") {
            CalculatorGame(navController = navController, this)
        } else if (this.game == "MultiplicativeRace") {
            MultiplicativeRaceGame(navController = navController, exercise = this)
        }
    }
}