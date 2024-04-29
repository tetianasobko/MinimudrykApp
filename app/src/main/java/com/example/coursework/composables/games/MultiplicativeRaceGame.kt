package com.example.coursework.composables.games

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.coursework.R
import com.example.coursework.composables.dialogs.RightAnswerDialog
import com.example.coursework.composables.dialogs.WrongAnswerDialog
import com.example.coursework.data.TopicsRepository
import com.example.coursework.models.Exercise
import com.example.coursework.navigation.Exercises
import com.example.coursework.navigation.Games
import com.example.coursework.ui.theme.darkGrey
import com.example.coursework.ui.theme.mediumGrey
import com.example.coursework.ui.theme.trueBlue

@Composable
fun MultiplicativeRaceGame(navController: NavController, exercise: Exercise) {
    val textColor = darkGrey

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
                try {
                    shouldShowWrongAnswerDialog.value = false
                    navController.navigate(Games.route + "/${exercise.mathTopicId}" + "/${exercise.id}")
                } catch (e: Exception ){
                    var i =9
                }
            },
        )
    }
    var currentNumber by remember {
        mutableIntStateOf(1)
    }

    var result by remember {
        mutableStateOf("")
    }

    var multiplier by remember {
        mutableIntStateOf(1)
    }

    var action by remember {
        mutableStateOf("")
    }

    var turn by remember {
        mutableStateOf(false)
    }

    fun makeMove(number: Int) {
        multiplier = number
        action = "$multiplier"
    }

    fun calculateAction() {
        if (turn && currentNumber <= 1000) {
            Handler(Looper.getMainLooper()).postDelayed(
                {
                    when (currentNumber) {
                        in 2..9 -> {
                            val number = 25 / currentNumber - 1
                            if (number in 2..9) {
                                makeMove(number)
                            } else {
                                makeMove(2)
                            }
                        }

                        in 25..54 -> {
                            val number = 111 / currentNumber - 1
                            if (number in 2..9) {
                                makeMove(number)
                            } else {
                                makeMove(2)
                            }
                        }

                        in 111..999 -> {
                            val number = 1000 / currentNumber + 1
                            if (number in 2..9) {
                                makeMove(number)
                            } else {
                                makeMove(2)
                            }
                        }

                        else -> makeMove(2)
                    }
                    currentNumber *= multiplier
                    result = "$currentNumber"
                    turn = false
                    if (currentNumber > 1000) {
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
    }

    val buttonSpacing = 10.dp
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(buttonSpacing),
        ) {
            Text(
                text = result,
                color = textColor,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                fontWeight = FontWeight.Medium,
                fontSize = 40.sp,
            )
            calculateAction()
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
            )
            Row {
                Text(
                    text = "Хід:",
                    color = textColor,
                    fontSize = 15.sp,
                    modifier = Modifier.weight(1f)
                )
                Text(
                    text = action,
                    color = textColor,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Right,
                    modifier = Modifier.weight(1f)
                )
            }
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(buttonSpacing),
                    modifier = Modifier.weight(3.3f)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                    ) {
                        CalculatorButton(
                            symbol = "7",
                            modifier = Modifier
                                .aspectRatio(1f)
                                .weight(1f)
                                .clickable {
                                    makeMove(7)
                                },
                            color = mediumGrey
                        )
                        CalculatorButton(
                            symbol = "8",
                            modifier = Modifier
                                .aspectRatio(1f)
                                .weight(1f)
                                .clickable {
                                    makeMove(8)
                                },
                            color = mediumGrey
                        )
                        CalculatorButton(
                            symbol = "9",
                            modifier = Modifier
                                .aspectRatio(1f)
                                .weight(1f)
                                .clickable {
                                    makeMove(9)
                                },
                            color = mediumGrey
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                    ) {
                        CalculatorButton(
                            symbol = "4",
                            modifier = Modifier
                                .aspectRatio(1f)
                                .weight(1f)
                                .clickable {
                                    makeMove(4)
                                },
                            color = mediumGrey
                        )
                        CalculatorButton(
                            symbol = "5",
                            modifier = Modifier
                                .aspectRatio(1f)
                                .weight(1f)
                                .clickable {
                                    makeMove(5)
                                },
                            color = mediumGrey
                        )
                        CalculatorButton(
                            symbol = "6",
                            modifier = Modifier
                                .aspectRatio(1f)
                                .weight(1f)
                                .clickable {
                                    makeMove(6)
                                },
                            color = mediumGrey
                        )

                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
                    ) {
                        IconButton(
                            onClick = {
                                currentNumber = 1
                                multiplier = 1
                                result = ""
                                action = ""
                            },
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .background(darkGrey)
                                .aspectRatio(1f)
                                .weight(1f)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.restart),
                                contentDescription = "Restart",
                                tint = Color.White
                            )

                        }
                        CalculatorButton(
                            symbol = "2",
                            modifier = Modifier
                                .aspectRatio(1f)
                                .weight(1f)
                                .clickable {
                                    makeMove(2)
                                },
                            color = mediumGrey
                        )
                        CalculatorButton(
                            symbol = "3",
                            modifier = Modifier
                                .aspectRatio(1f)
                                .weight(1f)
                                .clickable {
                                    makeMove(3)
                                },
                            color = mediumGrey
                        )
                    }
                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(buttonSpacing),
                    modifier = Modifier.weight(1f)
                ) {
                    CalculatorButton(
                        symbol = "=",
                        color = trueBlue,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .clickable {
                                if (multiplier != 1) {
                                    currentNumber *= multiplier
                                    result = "$currentNumber"
                                    turn = true

                                    if (currentNumber > 1000) {
                                        shouldShowRightAnswerDialog.value = true
                                        exercise.complete()
                                        topicsRepository.updateExercise(exercise)
                                    }
                                }
                            }
                    )
                }
            }
        }
    }
}