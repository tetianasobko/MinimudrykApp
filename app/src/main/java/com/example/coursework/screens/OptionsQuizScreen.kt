package com.example.coursework.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.coursework.composables.quizes.OptionsQuizSection
import com.example.coursework.composables.TopBar
import com.example.coursework.data.TopicsRepository
import com.example.coursework.models.OptionsQuizExercise
import com.example.coursework.navigation.Exercises
import com.example.coursework.ui.theme.trueBlue

@Composable
fun OptionsQuizScreen(navController: NavController, topicId: Long, exerciseId: Long) {
    val context = LocalContext.current
    val topicsRepository = TopicsRepository(context)
    val exercise = topicsRepository.findTopic(topicId).findExercise(exerciseId) as OptionsQuizExercise
    Box {
        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .fillMaxHeight(.35f)
                .fillMaxWidth()
                .clip(RoundedCornerShape(bottomEnd = 15.dp, bottomStart = 15.dp))
                .background(trueBlue)
        ) {
            TopBar(
                onClick = {navController.navigate(Exercises.route + "/${exercise.mathTopicId}")}, text = exercise.name, color = Color.White)
        }
        OptionsQuizSection(navController, exercise)
    }
}