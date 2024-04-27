package com.example.coursework.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.coursework.models.MathTopic
import com.example.coursework.navigation.Exercises
import com.example.coursework.ui.theme.darkGrey
import com.example.coursework.ui.theme.green
import com.example.coursework.ui.theme.lightGrey
import com.example.coursework.ui.theme.seaSalt

@Composable
fun TopicButton(navController: NavController, topic: MathTopic) {
    Button(
        onClick = { navController.navigate(Exercises.route + "/${topic.id}") },
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(lightGrey),
        contentPadding = PaddingValues(17.dp, 10.dp, 0.dp, 10.dp),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier.weight(9f)
            ) {
                Text(text = topic.name, color = darkGrey)
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    LinearProgressIndicator(
                        progress = topic.getProgress() / 100f,
                        color = green,
                        trackColor = seaSalt,
                        modifier = Modifier
                            .height(7.dp)
                            .weight(6f)
                            .clip(RoundedCornerShape(8.dp))
                    )
                    Text(text = "${topic.getProgress()}%", color = darkGrey,
                        textAlign = TextAlign.Right,
                        modifier = Modifier.weight(1f))
                }
            }
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    Icons.Filled.KeyboardArrowRight,
                    contentDescription = "Arrow",
                    modifier = Modifier
                        .weight(1f)
                        .align(
                            Alignment.CenterVertically
                        )
                )
            }
        }
    }
}

@Composable
fun TopicList(navController: NavController, topics: List<MathTopic>) {
    LazyColumn(
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        items(topics) { topic ->
            TopicButton(navController, topic)
        }
    }
}