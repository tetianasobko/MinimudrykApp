package com.example.coursework.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.coursework.ui.theme.CourseworkTheme

@Composable
fun TopBar(onClick: () -> Unit, text: String, color: Color) {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterStart) {
        BackButton(onClick = onClick, color = color)
        Text(text = text, color = color, style = MaterialTheme.typography.titleLarge, modifier =  Modifier.align(Alignment.Center).padding(top = 10.dp, bottom = 15.dp, start = 30.dp, end = 30.dp), maxLines = 1, overflow = TextOverflow.Ellipsis)
    }
}