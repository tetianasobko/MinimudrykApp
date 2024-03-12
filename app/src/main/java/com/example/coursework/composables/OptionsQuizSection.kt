package com.example.coursework.composables

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
import androidx.compose.ui.unit.dp
import com.example.coursework.models.OptionsQuizExercise
import kotlin.random.Random

@Composable
fun OptionsQuizSection(exercise: OptionsQuizExercise) {
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
            QuizCard(exercise)
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
                        colors = ButtonDefaults.buttonColors(if (option == selectedOption) Color.Magenta else Color.LightGray),
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
                onClick = { /*TODO*/ },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(text = "Answer")
            }
        }
    }
}