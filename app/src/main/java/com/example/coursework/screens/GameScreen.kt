package com.example.coursework.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.coursework.composables.Calculator
import com.example.coursework.composables.TopBar
import com.example.coursework.data.TopicsRepository
import com.example.coursework.models.GameExercise
import com.example.coursework.models.InputQuizExercise

@Composable
fun GameScreen(navController: NavController, topicId: Long, exerciseId: Long) {
    val context = LocalContext.current
    val topicsRepository = TopicsRepository(context)
    val exercise = topicsRepository.findTopic(topicId).findExercise(exerciseId) as GameExercise
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(navController = navController, text = exercise.name, color = Color.DarkGray)
        exercise.getGame(navController = navController)

    }
}