package com.example.coursework.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.coursework.composables.ExerciseDescriptionCard
import com.example.coursework.composables.TopBar
import com.example.coursework.data.TopicsRepository
import com.example.coursework.models.GameExercise
import com.example.coursework.ui.theme.darkGrey

@Composable
fun GameScreen(navController: NavController, topicId: Long, exerciseId: Long) {
    val context = LocalContext.current
    val topicsRepository = TopicsRepository(context)
    val exercise = topicsRepository.findTopic(topicId).findExercise(exerciseId) as GameExercise
    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Box(contentAlignment = Alignment.BottomCenter, modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter)) {
                exercise.getGame(navController = navController)
            }
            Column(verticalArrangement = Arrangement.spacedBy(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                TopBar(navController = navController, text = exercise.name, color = darkGrey)
                ExerciseDescriptionCard(description = exercise.description)
            }
        }

    }
}