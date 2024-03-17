package com.example.coursework.data

import android.content.Context
import com.example.coursework.models.Exercise
import com.example.coursework.models.MathTopic

class TopicsRepository(private val context: Context) {
    private val fileName = "topics.json"
    private var topics = listOf<MathTopic>()
    init {
        readTopicsFromJson()
    }

    fun findTopic(id: Int) = topics.first { it.id == id }
    fun getTopicsAll() = topics
    private fun readTopicsFromJson() {
        val file = context.assets.open(fileName)
        val jsonSerializer = JsonSerializer()
        topics = jsonSerializer.deserializeMathTopics(file)
    }

    fun updateExercise(mathTopicId: Int, exercise: Exercise) {
        val file = context.assets.open(fileName)
        val jsonSerializer = JsonSerializer()
        jsonSerializer.updateExerciseInMathTopic(mathTopicId, exercise, file)

    }

}