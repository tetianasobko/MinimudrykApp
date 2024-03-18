package com.example.coursework.data

import android.content.Context
import com.example.coursework.models.Exercise
import com.example.coursework.models.MathTopic
import java.io.FileInputStream
import kotlin.io.path.Path
import kotlin.io.path.absolutePathString

class TopicsRepository(private val context: Context) {
    private val fileName = "com/example/coursework/data/topics.json"
    private var topics = listOf<MathTopic>()
    init {
        readTopicsFromJson()
    }

    fun findTopic(id: Int) = topics.first { it.id == id }
    fun getTopicsAll() = topics
    private fun readTopicsFromJson() {
        val filePath = Path("app/src/main/java/com/example/coursework/data/topics.json").absolutePathString()
        val jsonSerializer = JsonSerializer()
        topics = jsonSerializer.deserializeMathTopics(filePath)
    }

    fun updateExercise(exercise: Exercise) {
        val filePath = Path("app/src/main/java/com/example/coursework/data/topics.json").absolutePathString()
        val jsonSerializer = JsonSerializer()
        jsonSerializer.updateExerciseInMathTopic(exercise, filePath)

    }

}