package com.example.coursework.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.coursework.composables.ExerciseGrid
import com.example.coursework.composables.TopAppBar
import com.example.coursework.data.TopicsRepository

@Composable
fun ExercisesScreen(navController: NavController, topicId: Int) {
    val context = LocalContext.current
    val topicsRepository = TopicsRepository(context)
    val topic = topicsRepository.findTopic(topicId)
    Column {
        TopAppBar(navController, topic.name)
        ExerciseGrid(navController, topic)
    }
}