package com.example.coursework.composables.quizes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.coursework.R
import com.example.coursework.composables.dialogs.HintDialog
import com.example.coursework.models.Exercise
import com.example.coursework.ui.theme.darkGrey
import com.example.coursework.ui.theme.lightGrey

@Composable
fun QuizCard(navController: NavController, exercise: Exercise) {
    val shouldShowHintDialog = remember { mutableStateOf(false) }

    if (shouldShowHintDialog.value) {
        HintDialog(
            navController = navController,
            onDismissRequest = { shouldShowHintDialog.value = false },
            exercise.hint
        )
    }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ), elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ), modifier = Modifier
            .fillMaxWidth(.9f)
            .defaultMinSize(minHeight = 150.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp),
            horizontalAlignment = Alignment.End,
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
        ) {
            Button(
                onClick = { shouldShowHintDialog.value = true },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(lightGrey),
                modifier = Modifier
                    .width(60.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.hint),
                    contentDescription = "Hint",
                    tint = darkGrey,
                )
            }
            Text(
                text = exercise.description,
                color = darkGrey,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 20.dp)
            )
        }
    }
}