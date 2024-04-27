package com.example.coursework.composables.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.coursework.R
import com.example.coursework.ui.theme.lightBlue

@Composable
fun WrongAnswerDialog(onDismissRequest: () -> Unit) {
    Dialog(
        onDismissRequest = onDismissRequest, properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false
        )
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ), elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            ), modifier = Modifier
                .wrapContentHeight()
//                .fillMaxWidth(.8f)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.sad_cat),
                    contentDescription = "ThumbsUp"
                )
                Text(text = "Ой-йой...")
                Text(text = "Схоже щось не так.\nСпробуй ще раз!", textAlign = TextAlign.Center)
                Button(
                    onClick = onDismissRequest, shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(lightBlue)
                ) {
                    Text(text = "Спробувати ще раз", color = Color.White)
                }
            }

        }
    }
}