package com.example.coursework.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.coursework.composables.OptionsQuizSection
import com.example.coursework.composables.TopPanel
import com.example.coursework.data.TopicsRepository
import com.example.coursework.models.OptionsQuizExercise

@Composable
fun OptionsQuizScreen(topicId: Int, exerciseId: Int) {
    val context = LocalContext.current
    val topicsRepository = TopicsRepository(context)
    val exercise = topicsRepository.findTopic(topicId).findExercise(exerciseId) as OptionsQuizExercise
    Box {
        TopPanel(exercise.name)
        OptionsQuizSection(exercise)

    }
}


@Preview(showBackground = true)
@Composable
fun Prev() {

    OptionsQuizScreen(topicId = 1, exerciseId = 11)

}