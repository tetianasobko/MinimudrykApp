package com.example.coursework.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ExerciseDescriptionCard(description: String) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.LightGray
        ), modifier = Modifier
            .fillMaxWidth(.8f)
    ) {
        Text(
            text = description,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(15.dp, 25.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}
