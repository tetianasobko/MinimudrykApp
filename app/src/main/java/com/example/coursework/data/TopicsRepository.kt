package com.example.coursework.data

import android.content.Context
import com.example.coursework.models.MathTopic

class TopicsRepository(private val context: Context) {
    private var topics = listOf<MathTopic>()
    init {
        readTopicsFromJson()
    }

    fun getTopic(id: Int) = topics.first() { it.id == id }
    fun getTopicsAll() = topics
    private fun readTopicsFromJson() {
        val file = context.assets.open("topics.json")
        val jsonSerializer = JsonSerializer()
        topics = jsonSerializer.deserializeMathTopics(file)
    }

}