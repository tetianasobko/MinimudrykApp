package com.example.coursework.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.coursework.models.MathTopic
import com.example.coursework.navigation.Exercises

@Composable
fun TopicButton(navController: NavController, topic: MathTopic) {
    Button(
        onClick = { navController.navigate(Exercises.route + "/${topic.id}") },
        shape = RoundedCornerShape(8.dp),
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
                Text(text = topic.name)
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    LinearProgressIndicator(
                        progress = 100f / topic.getProgress(),
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(8.dp))
                    )
                    Text(text = "${topic.getProgress()}")
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
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(topics) { topic ->
            TopicButton(navController, topic)
        }
    }
}