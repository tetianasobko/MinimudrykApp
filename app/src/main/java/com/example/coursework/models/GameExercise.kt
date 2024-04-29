package com.example.coursework.models

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.coursework.composables.games.CalculatorGame
import com.example.coursework.composables.games.HorizontalShuffleGame
import com.example.coursework.composables.games.MultiplicativeRaceGame
import com.example.coursework.composables.games.RectangularPuzzleGame

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
        when(this.game) {
            "Calculator" -> CalculatorGame(navController = navController, exercise = this)
            "MultiplicativeRace" -> MultiplicativeRaceGame(navController = navController, exercise = this)
            "RectangularPuzzle" -> RectangularPuzzleGame(navController = navController, exercise = this)
            "HorizontalShuffle" -> HorizontalShuffleGame(navController = navController, exercise = this)
        }
    }
}