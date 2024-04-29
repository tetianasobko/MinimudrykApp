package com.example.coursework.composables.quizes

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.coursework.composables.dialogs.RightAnswerDialog
import com.example.coursework.composables.dialogs.WrongAnswerDialog
import com.example.coursework.data.TopicsRepository
import com.example.coursework.models.OptionsQuizExercise
import com.example.coursework.navigation.Exercises
import com.example.coursework.ui.theme.darkGrey
import com.example.coursework.ui.theme.lightGrey
import com.example.coursework.ui.theme.trueBlue
import kotlin.random.Random

@Composable
fun OptionsQuizSection(navController: NavController, exercise: OptionsQuizExercise) {
    val context = LocalContext.current
    val topicsRepository = TopicsRepository(context)

    val shouldShowRightAnswerDialog = remember { mutableStateOf(false) }
    val shouldShowWrongAnswerDialog = remember { mutableStateOf(false) }

    if (shouldShowRightAnswerDialog.value) {
        RightAnswerDialog(
            onClick = { navController.navigate(Exercises.route + "/${exercise.mathTopicId}") },
            onDismissRequest = { shouldShowRightAnswerDialog.value = false })
    }

    if (shouldShowWrongAnswerDialog.value) {
        WrongAnswerDialog(onDismissRequest = { shouldShowWrongAnswerDialog.value = false })
    }

    var selectedOption by remember {
        mutableStateOf("")
    }
    val seed by remember { mutableIntStateOf(Random.nextInt(40)) }
    Column(
        verticalArrangement = Arrangement.spacedBy(30.dp),
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
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                exercise.options.shuffled(Random(seed)).forEach() { option ->
                    Button(
                        onClick = {
                            selectedOption = option
                        },
                        colors = ButtonDefaults.buttonColors(lightGrey),
                        border = BorderStroke(
                            3.dp,
                            color = if (option == selectedOption) trueBlue else lightGrey
                        ),
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues(17.dp),
                        modifier = Modifier
                            .fillMaxWidth(.9f)
                    ) {
                        Text(text = option, modifier = Modifier.fillMaxWidth())
                    }
                }
            }
            Button(
                onClick = {
                    if (selectedOption == exercise.options[exercise.correctOption]) {
                        shouldShowRightAnswerDialog.value = true
                        exercise.complete()
                        topicsRepository.updateExercise(exercise)
                    } else {
                        shouldShowWrongAnswerDialog.value = true
                    }
                },
                colors = ButtonDefaults.buttonColors(trueBlue),
                enabled = selectedOption.isNotBlank(),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(
                    text = "Перевірити",
                    color = if (selectedOption.isNotBlank()) Color.White else darkGrey
                )
            }
        }
    }
}