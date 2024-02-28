package com.example.coursework.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.coursework.composables.TopicList

@Composable
fun TopicsScreen(navController: NavController) {
    TopicList(navController)
}