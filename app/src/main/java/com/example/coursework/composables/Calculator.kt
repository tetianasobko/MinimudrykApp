package com.example.coursework.composables

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.coursework.data.TopicsRepository
import com.example.coursework.models.Exercise
import javax.script.ScriptEngineManager
import kotlin.random.Random

@Composable
fun Calculator(navController: NavController, exercise: Exercise) {
    val context = LocalContext.current
    val topicsRepository = TopicsRepository(context)

    val shouldShowRightAnswerDialog = remember { mutableStateOf(false) }

    if (shouldShowRightAnswerDialog.value) {
        RightAnswerDialog(
            onDismissRequest = { shouldShowRightAnswerDialog.value = false },
            navController = navController
        )
    }

    var expression by remember {
        mutableStateOf("")
    }
    var equation by remember {
        mutableStateOf("")
    }
    val buttonSpacing = 10.dp
    val finalNumber by remember { mutableIntStateOf(Random.nextInt(500)) }
    val startNumber by remember { mutableIntStateOf(Random.nextInt(1, finalNumber / 2)) }
    var currentNumber by remember { mutableDoubleStateOf(0.0) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(buttonSpacing),
        horizontalAlignment = Alignment.Start
    ) {

        Text(
            text = "$finalNumber = ",
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Light,
            fontSize = 40.sp,
        )
        LazyRow(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth(),
            reverseLayout = true
        ) {
            item {
                Text(
                    text = expression,
                    textAlign = TextAlign.End,
                    fontWeight = FontWeight.Light,
                    fontSize = 40.sp,
                    maxLines = 2
                )
            }


        }
        Text(
            text = "$currentNumber",
            modifier = Modifier
                .padding(5.dp)
                .align(Alignment.End)
        )

        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .height(
                    2
                        .dp
                )
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
        ) {
            CalculatorButton(
                symbol = "$startNumber",
                modifier = Modifier
                    .aspectRatio(1f)
                    .weight(1f)
                    .clickable {
                        if (expression.isNotEmpty()) {
                            val lastChar = expression.last()
                            // if last char is not a operator, add a multiplication operator before the parenthesis
                            if (lastChar !in "$startNumber") {
                                if (lastChar == ')') expression += "×"
                                expression += "$startNumber"
                            }
                        } else {
                            expression += "$startNumber"
                        }

                        equation = transformExpression(expression)

                        try {
                            currentNumber = evaluate(equation)
                        } catch (_: Exception) {
                        }
                    }
            )
            CalculatorButton(
                symbol = "AC",
                modifier = Modifier
                    .aspectRatio(1f)
                    .weight(1f)
                    .clickable {
                        expression = ""
                    }
            )

            CalculatorButton(
                symbol = "(",
                modifier = Modifier
                    .aspectRatio(1f)
                    .weight(1f)
                    .clickable {
                        if (expression.isNotEmpty()) {
                            val lastChar = expression.last()
                            // if last char is not a operator, add a multiplication operator before the parenthesis
                            if (lastChar !in "+-×÷^√(") {
                                expression += "×"
                            }
                        }
                        expression += "("
                    }
            )
            CalculatorButton(
                symbol = ")",
                modifier = Modifier
                    .aspectRatio(1f)
                    .weight(1f)
                    .clickable {
                        val open = expression.count { it == '(' }
                        val closed = expression.count { it == ')' }
                        if (expression.isNotEmpty() && open >= closed + 1) {
                            val lastChar = expression.last()
                            // if last char is not a operator, add a multiplication operator before the parenthesis
                            if (lastChar !in "+-×÷^√(") {
                                expression += ")"
                            }
                        }
                    }
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
        ) {
            CalculatorButton(
                symbol = "+",
                modifier = Modifier
                    .aspectRatio(1f)
                    .weight(1f)
                    .clickable {
                        expression = arithmeticOperation('+', expression)
                    }
            )
            CalculatorButton(
                symbol = "-",
                modifier = Modifier
                    .aspectRatio(1f)
                    .weight(1f)
                    .clickable {
                        expression = arithmeticOperation('-', expression)
                    }
            )
            CalculatorButton(
                symbol = "×",
                modifier = Modifier
                    .aspectRatio(1f)
                    .weight(1f)
                    .clickable {
                        expression = arithmeticOperation('×', expression)
                    }
            )
            CalculatorButton(
                symbol = "÷",
                modifier = Modifier
                    .aspectRatio(1f)
                    .weight(1f)
                    .clickable {
                        expression = arithmeticOperation('÷', expression)
                    }
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
        ) {
            CalculatorButton(
                symbol = "^",
//                        color = PrussianBlue,
                modifier = Modifier
                    .aspectRatio(1f)
                    .weight(1f)
                    .clickable {
                        if (expression.isNotEmpty()) {
                            val lastChar = expression.last()

                            if (lastChar in "+-×÷^ ") {
                                expression = expression.dropLast(1)
                                expression += "^("
                            }
                            // if last char is an operator, replace it with the new operator
                            if (lastChar in "0123456789") {
                                expression += "^("
                            }
                        }

                    }
            )

            CalculatorButton(
                symbol = "√",
                modifier = Modifier
                    .aspectRatio(1f)
                    .weight(1f)
                    .clickable {
                        if (expression.isNotEmpty()) {
                            val lastChar = expression.last()
                            if (lastChar in "0123456789)") {
                                expression += "×"
                            }
                        }
                        expression += "√("
                    }
            )
            CalculatorButton(
                symbol = "Del",
                modifier = Modifier
                    .aspectRatio(1f)
                    .weight(1f)
                    .clickable {
                        if (expression.isNotEmpty()) {
                            if (expression.last() !in "0123456789") {
                                expression = expression.dropLast(1)
                            } else {
                                while (expression.last() in "0123456789") {
                                    expression = expression.dropLast(1)
                                    if (expression.isEmpty()) {
                                        break
                                    }
                                }
                            }

                            equation = transformExpression(expression)

                            while (equation.last() !in "0123456789") {
                                equation = equation.dropLast(1)
                            }

                            equation = checkBrackets(equation)

                            try {
                                currentNumber = evaluate(equation)
                            } catch (_: Exception) {
                            }
                        }
                    }
            )
            CalculatorButton(
                symbol = "✓",
                color = Color.Blue,
                modifier = Modifier
                    .aspectRatio(1f)
                    .weight(1f)
                    .clickable {
                        equation = transformExpression(expression)

                        try {
                            currentNumber = evaluate(equation)

                            if (currentNumber == finalNumber.toDouble()) {
                                shouldShowRightAnswerDialog.value = true
                                exercise.isCompleted
                                topicsRepository.updateExercise(exercise)
                            }
                        } catch (e: Exception) {
                            Toast
                                .makeText(context, "Invalid Input", Toast.LENGTH_LONG)
                                .show()
                        }
                    }
            )
        }
    }

}

fun evaluate(equation: String): Double {
    val engine = ScriptEngineManager().getEngineByName("rhino")

    return engine
        .eval(equation)
        .toString()
        .toDouble()
}

private fun transformExpression(expression: String): String {
    if (expression.isEmpty()) {
        return "0"
    }
    var result = expression.replace("×", "*")
        .replace("÷", "/")
        .replace("√", "Math.sqrt")

    result = checkBrackets(result)

    val regex = Regex("(\\d+)\\s*\\^\\s*\\(([^()]+)\\)")
    result = result.replace(regex) { power ->
        val base = power.groupValues[1]
        val exponent = power.groupValues[2]
        "Math.pow($base, $exponent)"
    }
    return result
}

fun checkBrackets(equation: String): String {
    var result = equation
    while (result.count { it == '(' } - result.count { it == ')' } != 0) {
        result += ")"
    }
    return result
}

fun arithmeticOperation(symbol: Char, expression: String): String {
    var result = expression
    if (expression.isNotEmpty()) {
        val lastChar = expression.last()
        if (lastChar in "+-×÷^√") {
            result = expression.dropLast(1)
        }
        if (lastChar != '(') {
            result += symbol
        } else if (symbol == '-') {
            result += symbol
        }
    }
    return result
}

@Composable
fun CalculatorButton(
    symbol: String,
    modifier: Modifier = Modifier,
    color: Color = Color.Gray,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(color)
            .then(modifier)
    ) {
        Text(
            text = symbol,
            fontSize = 36.sp,
            color = Color.White,
        )
    }
}