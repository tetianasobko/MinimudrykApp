@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.coursework.composables.quizes

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.coursework.composables.dialogs.RightAnswerDialog
import com.example.coursework.composables.dialogs.WrongAnswerDialog
import com.example.coursework.data.TopicsRepository
import com.example.coursework.models.InputQuizExercise
import com.example.coursework.ui.theme.darkGrey
import com.example.coursework.ui.theme.trueBlue

@Composable
fun InputQuizSection(navController: NavController, exercise: InputQuizExercise) {
    val context = LocalContext.current
    val topicsRepository = TopicsRepository(context)

    val shouldShowRightAnswerDialog = remember { mutableStateOf(false) }
    val shouldShowWrongAnswerDialog = remember { mutableStateOf(false) }

    if (shouldShowRightAnswerDialog.value) {
        RightAnswerDialog(onDismissRequest = { shouldShowRightAnswerDialog.value = false }, navController = navController)
    }

    if (shouldShowWrongAnswerDialog.value) {
        WrongAnswerDialog(onDismissRequest = { shouldShowWrongAnswerDialog.value = false })
    }

    var answer by remember { mutableStateOf(TextFieldValue()) }
    Column(
        verticalArrangement = Arrangement.spacedBy(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(contentAlignment = Alignment.BottomCenter, modifier = Modifier.weight(1f)) {
            QuizCard(navController, exercise)
        }
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
                .padding(vertical = 15.dp, horizontal = 15.dp)
        ) {
            TextField(
                value = answer,
                onValueChange = { answer = it },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.border(BorderStroke(width = 2.dp, color = trueBlue), RoundedCornerShape(8.dp)),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
            )
            Button(
                onClick = {
                    if (answer.text == exercise.correctAnswer) {
                        shouldShowRightAnswerDialog.value = true
                        exercise.complete()
                        topicsRepository.updateExercise(exercise)
                    } else {
                        shouldShowWrongAnswerDialog.value = true
                    }
                },
                colors = ButtonDefaults.buttonColors(trueBlue),
                enabled = answer.text.isNotBlank(),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(text = "Answer", color = if (answer.text.isNotBlank()) Color.White else darkGrey)
            }
        }
    }
}