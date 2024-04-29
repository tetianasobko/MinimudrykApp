package com.example.coursework.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.coursework.composables.ExerciseGrid
import com.example.coursework.composables.TopBar
import com.example.coursework.data.TopicsRepository
import com.example.coursework.navigation.Exercises
import com.example.coursework.navigation.Topics
import com.example.coursework.ui.theme.darkGrey

@Composable
fun ExercisesScreen(navController: NavController, topicId: Long) {
    val context = LocalContext.current
    val topicsRepository = TopicsRepository(context)
    val topic = topicsRepository.findTopic(topicId)
    Column {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .shadow(
                    elevation = 1.dp,
                    shape = RoundedCornerShape(bottomEnd = 15.dp, bottomStart = 15.dp),
                )
                .background(Color.White)
        ) {
            TopBar(
                onClick = {navController.navigate(Topics.route)}, text = topic.name, color = darkGrey)
        }
        ExerciseGrid(navController, topic)
    }
}