package com.example.coursework.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.coursework.composables.InputQuizSection
import com.example.coursework.composables.TopPanel
import com.example.coursework.data.TopicsRepository
import com.example.coursework.models.InputQuizExercise

@Composable
fun InputQuizScreen(topicId: Int, exerciseId: Int) {
    val context = LocalContext.current
    val topicsRepository = TopicsRepository(context)
    val exercise = topicsRepository.findTopic(topicId).findExercise(exerciseId) as InputQuizExercise
    Box {
        TopPanel(exercise.name)
        InputQuizSection(exercise)
    }
}