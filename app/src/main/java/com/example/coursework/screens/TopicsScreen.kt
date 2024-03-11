package com.example.coursework.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.coursework.composables.TopicList
import com.example.coursework.data.JsonSerializer
import com.example.coursework.data.TopicsRepository
import com.google.gson.Gson

@Composable
fun TopicsScreen(navController: NavController) {
    val context = LocalContext.current
    val topicsRepository = TopicsRepository(context)

    TopicList(navController, topicsRepository.getTopicsAll())
}