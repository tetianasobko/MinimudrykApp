package com.example.coursework.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.coursework.R
import com.example.coursework.models.Exercise
import com.example.coursework.models.ExerciseType
import com.example.coursework.models.MathTopic
import com.example.coursework.navigation.Games
import com.example.coursework.navigation.InputQuiz
import com.example.coursework.navigation.OptionsQuiz
import com.example.coursework.ui.theme.darkGrey
import com.example.coursework.ui.theme.green
import com.example.coursework.ui.theme.lightGrey
import com.example.coursework.ui.theme.seaSalt

@Composable
fun ExerciseButton(navController: NavController, exercise: Exercise, topic: MathTopic) {
    var textColor = darkGrey
    var buttonColor = lightGrey
    var icon = R.drawable.circle
    if(exercise.isCompleted) {
        textColor = seaSalt
        buttonColor = green
        icon = R.drawable.completed
    }
    Button(
        onClick = {
            navController.navigate(
                when (exercise.type) {
                    ExerciseType.OptionsQuizType -> OptionsQuiz.route + "/${topic.id}" + "/${exercise.id}"
                    ExerciseType.InputQuizType -> InputQuiz.route + "/${topic.id}" + "/${exercise.id}"
                    ExerciseType.GameType -> Games.route + "/${topic.id}" + "/${exercise.id}"
                }
            )
        },
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(buttonColor),
        contentPadding = PaddingValues(15.dp),
        modifier = Modifier.aspectRatio(1f)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(text = exercise.name, color = textColor, maxLines = 2, overflow = TextOverflow.Ellipsis, modifier = Modifier.align(Alignment.BottomStart))
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "Checkmark",
                modifier = Modifier.align(Alignment.TopEnd)
            )
        }
    }
}

@Composable
fun ExerciseGrid(navController: NavController, topic: MathTopic) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(160.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        contentPadding = PaddingValues(15.dp)
    ) {
        items(topic.exercises) { exercise ->
            ExerciseButton(navController, exercise, topic)
        }
    }
}