package com.example.coursework.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController

@Composable
fun RightAnswerDialog(navController: NavController, onDismissRequest: () -> Unit) {
    Dialog(
        onDismissRequest = onDismissRequest , properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ), elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            ), modifier = Modifier
                .fillMaxWidth(.8f)
                .height(200.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Icon(Icons.Default.ThumbUp, contentDescription = "ThumbsUp")
                Text(text = "Вітаюююююю")
                Text(text = "Ти файно справився з тестом!")
                Button(onClick = {
                    navController.navigateUp()
                }, shape = RoundedCornerShape(8.dp)) {
                    Text(text = "Продовжити")
                }
            }

        }
    }
}
//
//@Preview(showBackground = true)
//@Composable
//fun Prev() {
//    RightAnswerDialog()
//}