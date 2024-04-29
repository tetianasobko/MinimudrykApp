package com.example.coursework.composables.games

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.coursework.composables.dialogs.RightAnswerDialog
import com.example.coursework.composables.dialogs.WrongAnswerDialog
import com.example.coursework.data.TopicsRepository
import com.example.coursework.models.Exercise
import com.example.coursework.navigation.Exercises
import com.example.coursework.navigation.Games
import com.example.coursework.ui.theme.green
import com.example.coursework.ui.theme.lightGrey
import com.example.coursework.ui.theme.trueBlue
import kotlin.random.Random

@Composable
fun RectangularPuzzleGame(navController: NavController, exercise: Exercise) {
    val context = LocalContext.current
    val topicsRepository = TopicsRepository(context)

    val shouldShowRightAnswerDialog = remember { mutableStateOf(false) }

    if (shouldShowRightAnswerDialog.value) {
        RightAnswerDialog(
            onClick = {navController.navigate(Exercises.route + "/${exercise.mathTopicId}")},
            onDismissRequest = { shouldShowRightAnswerDialog.value = false }
        )
    }

    val shouldShowWrongAnswerDialog = remember { mutableStateOf(false) }

    if (shouldShowWrongAnswerDialog.value) {
        WrongAnswerDialog(
            onDismissRequest = {
                shouldShowWrongAnswerDialog.value = false
                navController.navigate(Games.route + "/${exercise.mathTopicId}" + "/${exercise.id}")
            },
        )
    }

    var clicked by remember { mutableStateOf(Pair(5, 9)) }
    var selected by remember { mutableStateOf(Pair(5, 9)) }

    var goSecond by remember {
        mutableStateOf(true)
    }

    fun calculateAction() {
        Handler(Looper.getMainLooper()).postDelayed(
            {
                clicked = calculate(selected)
                selected = clicked

                if (selected == Pair(1, 1)) {
                    Handler(Looper.getMainLooper()).postDelayed(
                        {
                            shouldShowWrongAnswerDialog.value = true
                        },
                        500
                    )
                }
            },
            2000
        )
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.padding(horizontal = 10.dp).fillMaxHeight(.5f)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(9),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)

        ) {
            items(45) {
                val index = indexToMatrix(it)
                Box(
                    modifier = Modifier
                        .aspectRatio(1f)
                        .clip(RoundedCornerShape(8.dp))
                        .background(if (selected == index) trueBlue else if (clicked == index) green else lightGrey)
                        .clickable {
                            if (index.first == selected.first && index.second < selected.second ||
                                index.first < selected.first && index.second == selected.second
                            ) {
                                clicked = index
                            }
                        }
                )
            }
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
            Button(onClick = {
                if (goSecond) {
                    goSecond = false
                }
                if (clicked.first == selected.first && clicked.second < selected.second ||
                    clicked.first < selected.first && clicked.second == selected.second
                ) {
                    selected = clicked

                    if (selected == Pair(1, 1)) {
                        shouldShowRightAnswerDialog.value = true
                        exercise.complete()
                        topicsRepository.updateExercise(exercise)
                    } else {
                        calculateAction()
                    }
                }

            }, colors = ButtonDefaults.buttonColors(trueBlue)) {
                Text(text = "Зробити хід", color = Color.White)
            }
            Button(onClick = {
                calculateAction()
            },
                enabled = goSecond) {
                Text(text = "Почати другим")

            }
        }
    }
}

fun calculate(selected: Pair<Int, Int>): Pair<Int, Int> {
    return if (selected.first != selected.second) {
        if (selected.first < selected.second) {
            Pair(selected.first, selected.first)
        } else {
            Pair(selected.second, selected.second)
        }
    } else listOf(
        Pair(selected.first - 1, selected.second),
        Pair(selected.first, selected.second - 1)
    )[Random.nextInt(2)]

}

fun indexToMatrix(index: Int): Pair<Int, Int> {
    val row = index / 9
    val col = 8 - index % 9
    return Pair(row + 1, col + 1)
}

