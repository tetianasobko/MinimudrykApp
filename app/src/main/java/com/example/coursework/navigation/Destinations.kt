package com.example.coursework.navigation

interface Destinations {
    val route: String
}

object Topics : Destinations {
    override val route = "TopicsScreen"
}

object Exercises : Destinations {
    override val route = "ExercisesScreen"
}