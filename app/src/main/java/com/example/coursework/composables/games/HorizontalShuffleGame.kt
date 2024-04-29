package com.example.coursework.composables.games

import android.app.Activity
import android.content.pm.ActivityInfo
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
import com.example.coursework.ui.theme.lightBlue
import com.example.coursework.ui.theme.lightGrey
import com.example.coursework.ui.theme.trueBlue
import kotlin.random.Random

@Composable
fun HorizontalShuffleGame(navController: NavController, exercise: Exercise) {

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

    var firstPlayerPosition by remember {
        mutableStateOf(arrayOf(mutableStateOf(Pair(1, 1)), mutableStateOf(Pair(2, 1))))
    }

    var secondPlayerPosition by remember {
        mutableStateOf(arrayOf(mutableStateOf(Pair(1, 10)), mutableStateOf(Pair(2, 10))))
    }

    var clicked by remember { mutableStateOf(Pair(1, 1)) }

    var goSecond by remember {
        mutableStateOf(true)
    }

    fun updatePosition(
        firstPlayer: Pair<Int, Int>,
        secondPlayer: Pair<Int, Int>,
        end: Boolean
    ): Pair<Int, Int> {

        val resultPosition = if (secondPlayer.second > firstPlayer.second + 2) {
            if (end) {
                Pair(secondPlayer.first, firstPlayer.second + 1)
            } else {
                Pair(secondPlayer.first, firstPlayer.second + 2)
            }
        } else if (secondPlayer.second == firstPlayer.second + 2) {
            Pair(secondPlayer.first, firstPlayer.second + 1)
        } else {
            secondPlayer
        }

        return resultPosition
    }

    fun check(): Boolean {
        return (firstPlayerPosition[0].value.second + 1 == secondPlayerPosition[0].value.second
                && firstPlayerPosition[1].value.second + 1 == secondPlayerPosition[1].value.second)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.padding(horizontal = 10.dp).fillMaxHeight(.5f)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(10),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),

            ) {
            items(20) {
                val index = indexToMatrixx(it)
                val color =
                    when (index) {
                        firstPlayerPosition[0].value, firstPlayerPosition[1].value -> trueBlue
                        secondPlayerPosition[0].value, secondPlayerPosition[1].value -> green
                        clicked -> lightBlue
                        else -> lightGrey
                    }
                Box(modifier = Modifier
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(8.dp))
                    .background(color)
                    .clickable {
                        if ((index.first == firstPlayerPosition[0].value.first
                                    && index.second > firstPlayerPosition[0].value.second
                                    && index.second < secondPlayerPosition[0].value.second)
                            || (index.first == firstPlayerPosition[1].value.first
                                    && index.second > firstPlayerPosition[1].value.second
                                    && index.second < secondPlayerPosition[1].value.second)
                        ) {
                            clicked = index
                        }
                    })
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = {
                var actionRow = when (clicked.first) {
                    firstPlayerPosition[0].value.first -> 0
                    firstPlayerPosition[1].value.first -> 1
                    else -> -1
                }

                var endGame = false

                if (actionRow != -1 && (clicked != firstPlayerPosition[0].value || clicked != firstPlayerPosition[0].value)) {
                    if (goSecond) {
                        goSecond = false
                    }

                    firstPlayerPosition[actionRow].value = clicked

                    if (check()) {
                        shouldShowRightAnswerDialog.value = true
                        exercise.complete()
                        topicsRepository.updateExercise(exercise)
                    } else {
                        Handler(Looper.getMainLooper()).postDelayed({
                            if (secondPlayerPosition[actionRow].value.second - 1 != firstPlayerPosition[actionRow].value.second) {
                                endGame = false
                            } else {
                                actionRow = (actionRow + 1) % 2
                                endGame = true
                            }
                            secondPlayerPosition[actionRow].value = updatePosition(
                                firstPlayerPosition[actionRow].value,
                                secondPlayerPosition[actionRow].value,
                                endGame
                            )
                            if (check()) {
                                Handler(Looper.getMainLooper()).postDelayed(
                                    {
                                        shouldShowWrongAnswerDialog.value = true
                                    },
                                    500
                                )
                            }

                        }, 2000)
                    }

                }
            }, colors = ButtonDefaults.buttonColors(trueBlue)) {
                Text(text = "Зробити хід", color = Color.White)
            }
            Button(
                onClick = {
                    secondPlayerPosition[0].value = updatePosition(
                        firstPlayerPosition[0].value,
                        secondPlayerPosition[0].value,
                        false
                    )
                }, enabled = goSecond
            ) {
                Text(text = "Почати другим")

            }
        }

    }
}

fun calculated(selected: Pair<Int, Int>): Pair<Int, Int> {
    return if (selected.first != selected.second) {
        if (selected.first < selected.second) {
            Pair(selected.first, selected.first)
        } else {
            Pair(selected.second, selected.second)
        }
    } else listOf(
        Pair(selected.first - 1, selected.second), Pair(selected.first, selected.second - 1)
    )[Random.nextInt(2)]

}

fun indexToMatrixx(index: Int): Pair<Int, Int> {
    val row = index / 10
    val col = index % 10
    return Pair(row + 1, col + 1)
}

