package com.example.coursework.data

import android.adservices.topics.Topic
import android.content.Context
import com.example.coursework.models.Exercise
import com.example.coursework.models.MathTopic

class TopicsRepository(private val context: Context) {
    private val fileName = "topics.json"
    private val db = DBHelper(context)
    private var topics = listOf<MathTopic>()
    init {
        getAllTopicsFromDB()
    }

    fun findTopic(id: Long) = topics.first { it.id == id }
    fun getTopicsAll() = topics
    private fun getAllTopicsFromDB() {
        val topics = db.getAllMathTopics()

        if (topics.isEmpty()) {
            db.seedDatabase()
        }

        for (topic in topics) {
            topic.exercises = db.getExercisesByTopicId(topic.id)
        }

        this.topics = topics
    }

    fun updateExercise(exercise: Exercise) {
        db.updateExercise(exercise)
    }
}