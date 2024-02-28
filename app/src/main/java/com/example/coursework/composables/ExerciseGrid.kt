package com.example.coursework.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ExerciseButton() {
    Button(
        onClick = { /*TODO*/ },
        shape = RoundedCornerShape(8.dp),
        contentPadding = PaddingValues(15.dp),
        modifier = Modifier.aspectRatio(1f)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(text = "Exercise name", modifier = Modifier.align(Alignment.BottomStart))
            Icon(
                Icons.Default.CheckCircle,
                contentDescription = "Is completed",
                modifier = Modifier.align(Alignment.TopEnd)
            )
        }
    }
}

@Composable
fun ExerciseGrid() {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(160.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        contentPadding = PaddingValues(15.dp)
    ) {
        items(5) {
            ExerciseButton()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExercisesPreview() {
    ExerciseGrid()
}