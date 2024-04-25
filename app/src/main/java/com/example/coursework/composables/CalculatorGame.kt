package com.example.coursework.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.coursework.models.Exercise

@Composable
fun CalculatorGame(navController: NavController, exercise: Exercise) {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth().weight(1f)) {
        ExerciseDescriptionCard(description = exercise.description)
        }
        Box(contentAlignment = Alignment.BottomCenter, modifier = Modifier.fillMaxWidth()) {
            Calculator(navController = navController, exercise = exercise)
        }
    }
}