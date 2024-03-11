package com.example.coursework.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.coursework.composables.ExerciseGrid
import com.example.coursework.composables.TopAppBar

@Composable
fun ExercisesScreen(navController: NavController, topicId: Int) {
    Column {
        TopAppBar(navController)
        ExerciseGrid(topicId)
    }
}