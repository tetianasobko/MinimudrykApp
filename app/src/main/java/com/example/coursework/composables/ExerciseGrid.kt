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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.coursework.R
import com.example.coursework.models.Exercise
import com.example.coursework.models.ExerciseType
import com.example.coursework.models.MathTopic
import com.example.coursework.navigation.Exercises
import com.example.coursework.navigation.InputQuiz
import com.example.coursework.navigation.OptionsQuiz

@Composable
fun ExerciseButton(navController: NavController, exercise: Exercise) {
    Button(
        onClick = {
            try {
                navController.navigate(
                when(exercise.type) {
                    ExerciseType.OptionsQuizType -> OptionsQuiz.route + "/${exercise.mathTopicId}" + "/${exercise.id}"
                    ExerciseType.InputQuizType -> InputQuiz.route + "/${exercise.mathTopicId}" + "/${exercise.id}"
                    ExerciseType.GameType -> Exercises.route + "/${exercise.mathTopicId}"
                })

            }
            catch (e:Exception) {
                val x = 0
            }
        },
        shape = RoundedCornerShape(8.dp),
        contentPadding = PaddingValues(15.dp),
        modifier = Modifier.aspectRatio(1f)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(text = exercise.name , modifier = Modifier.align(Alignment.BottomStart))
            Icon(
                painter = painterResource(id = R.drawable.circle),
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
            ExerciseButton(navController, exercise)
        }
    }
}