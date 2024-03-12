package com.example.coursework.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun TopPanel(exerciseName: String) {
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .fillMaxHeight(.35f)
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomEnd = 15.dp, bottomStart = 15.dp))
            .background(Color.Gray)
    ) {
        IconButton(
            onClick = { },
            modifier = Modifier.align(Alignment.TopStart)
        ) {
            Icon(
                Icons.Default.KeyboardArrowLeft,
                contentDescription = "Back Button", tint = Color.White
            )
        }
        Text(text = exerciseName, color = Color.White, modifier = Modifier
            .padding(vertical = 9.dp))
    }
}

