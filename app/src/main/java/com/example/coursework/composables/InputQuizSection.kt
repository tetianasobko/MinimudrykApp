package com.example.coursework.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.coursework.models.InputQuizExercise

@Composable
fun InputQuizSection(navController: NavController, exercise: InputQuizExercise) {
    val shouldShowRightAnswerDialog = remember { mutableStateOf(false) }
    val shouldShowWrongAnswerDialog = remember { mutableStateOf(false) }

    if (shouldShowRightAnswerDialog.value) {
        RightAnswerDialog(onDismissRequest = { shouldShowRightAnswerDialog.value = false }, navController = navController)
    }

    if (shouldShowWrongAnswerDialog.value) {
        WrongAnswerDialog(onDismissRequest = { shouldShowWrongAnswerDialog.value = false }, navController = navController)
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
            OutlinedTextField(
                value = answer,
                onValueChange = { answer = it },
                shape = RoundedCornerShape(8.dp)
            )
            Button(
                onClick = {
                    if (answer.text == exercise.correctAnswer) {
                        exercise.complete()
                        shouldShowRightAnswerDialog.value = true
                    } else {
                        shouldShowWrongAnswerDialog.value = true
                    }
                },
                enabled = answer.text.isNotBlank(),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(text = "Answer")
            }
        }
    }
}