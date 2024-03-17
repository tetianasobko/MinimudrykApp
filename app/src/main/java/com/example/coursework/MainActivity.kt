package com.example.coursework

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.coursework.navigation.Exercises
import com.example.coursework.navigation.InputQuiz
import com.example.coursework.navigation.OptionsQuiz
import com.example.coursework.navigation.Topics
import com.example.coursework.screens.ExercisesScreen
import com.example.coursework.screens.InputQuizScreen
import com.example.coursework.screens.OptionsQuizScreen
import com.example.coursework.screens.TopicsScreen
import com.example.coursework.ui.theme.CourseworkTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CourseworkTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Topics.route) {
                        composable(Topics.route) {
                            TopicsScreen(navController)
                        }
                        composable(
                            Exercises.route + "/{${Exercises.argTopicId}}",
                            arguments = listOf(navArgument(Exercises.argTopicId) {
                                type = NavType.IntType
                            })
                        ) {
                            val topicId =
                                requireNotNull(it.arguments?.getInt(Exercises.argTopicId)) { "Topic id is null" }
                            ExercisesScreen(navController, topicId)
                        }
                        composable(
                            OptionsQuiz.route + "/{${OptionsQuiz.argTopicId}}" + "/{${OptionsQuiz.argExerciseId}}",
                            arguments = listOf(
                                navArgument(OptionsQuiz.argTopicId) {type = NavType.IntType },
                                navArgument(OptionsQuiz.argExerciseId) {type = NavType.IntType }
                            )
                        ) {
                            val topicId =
                                requireNotNull(it.arguments?.getInt(OptionsQuiz.argTopicId)) { "Topic id is null" }
                            val exerciseId =
                                requireNotNull(it.arguments?.getInt(OptionsQuiz.argExerciseId)) { "Exercise id is null" }
                            OptionsQuizScreen(navController, topicId, exerciseId)
                        }
                        composable(
                            InputQuiz.route + "/{${OptionsQuiz.argTopicId}}" + "/{${OptionsQuiz.argExerciseId}}",
                            arguments = listOf(navArgument(InputQuiz.argTopicId) {
                                type = NavType.IntType
                            }, navArgument(InputQuiz.argExerciseId) {
                                type = NavType.IntType
                            })
                        ) {
                            val topicId =
                                requireNotNull(it.arguments?.getInt(InputQuiz.argTopicId)) { "Topic id is null" }
                            val exerciseId =
                                requireNotNull(it.arguments?.getInt(InputQuiz.argExerciseId)) { "Exercise id is null" }
                            InputQuizScreen(navController, topicId, exerciseId)
                        }
                    }
                }
            }
        }
    }
}