package com.example.coursework.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TopicButton() {
    Button(
        onClick = { /*TODO*/ },
        shape = RoundedCornerShape(8.dp),
        contentPadding = PaddingValues(17.dp, 10.dp, 12.dp, 10.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Text(text = "Topic name")
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    LinearProgressIndicator()
                    Text(text = "50%")
                }
            }
            Icon(
                Icons.Filled.KeyboardArrowRight,
                contentDescription = "Arrow",
                modifier = Modifier.align(
                    Alignment.CenterEnd
                )
            )
        }

    }
}

@Preview
@Composable
fun TopicsPreview() {
    TopicButton()
}