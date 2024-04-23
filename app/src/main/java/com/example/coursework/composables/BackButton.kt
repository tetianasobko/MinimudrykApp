package com.example.coursework.composables

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun BackButton(navController: NavController, color: Color) {
    IconButton(
        onClick = { navController.navigateUp() },
        modifier = Modifier.size(40.dp)
    ) {
        Icon(
            Icons.Default.KeyboardArrowLeft,
            contentDescription = "Back Button", tint = color
        )
    }
}