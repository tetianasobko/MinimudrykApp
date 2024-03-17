package com.example.coursework.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.coursework.composables.OptionsQuizSection
import com.example.coursework.composables.TopPanel
import com.example.coursework.data.TopicsRepository
import com.example.coursework.models.OptionsQuizExercise

@Composable
fun OptionsQuizScreen(navController: NavController, topicId: Int, exerciseId: Int) {
    val context = LocalContext.current
    val topicsRepository = TopicsRepository(context)
    val exercise = topicsRepository.findTopic(topicId).findExercise(exerciseId) as OptionsQuizExercise
    Box {
        TopPanel(navController, exercise.name)
        OptionsQuizSection(navController, exercise)
    }
}