package com.example.coursework.data

import com.example.coursework.models.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.io.InputStream

class JsonSerializer {
    private val gson = Gson()

//    fun write(out: JsonWriter, exercise: Exercise) {
//        throw UnsupportedOperationException("This method is not supported")
//    }

    fun deserializeMathTopics(inputStream: InputStream): List<MathTopic> {
        val mathTopics = mutableListOf<MathTopic>()

        try {
            val jsonString = inputStream.bufferedReader().use { it.readText() }
            val listType = object : TypeToken<List<Map<String, *>>>() {}.type
            val jsonList = gson.fromJson<List<Map<String, *>>>(jsonString, listType)

            jsonList.forEach { item ->
                if (item.containsKey("id") && item.containsKey("name") && item.containsKey("exercises")) {
                    val id = (item["id"] as Double).toInt()
                    val name = item["name"] as String

                    val jsonExercises = item["exercises"] as List<*>
                    val exercises = mutableListOf<Exercise>()

                    jsonExercises.forEach { exercise ->
                        exercises.add(deserializeExercise(gson.toJson(exercise)))
                    }
                    val mathTopic = MathTopic(id, name, exercises)
                    mathTopics.add(mathTopic)
                }
            }

            return mathTopics
        } catch (e: IOException) {
            // add logger
        } catch (e: Exception) {
            // add logger
        }
        return mathTopics
    }

    private fun deserializeExercise(jsonString: String): Exercise {
        val exercise = gson.fromJson(jsonString, Map::class.java) as Map<String, *>

        val id = (exercise["id"] as Double).toInt()
        val name = exercise["name"] as String
        val typeValue = (exercise["type"] as Double).toInt()
        val description = exercise["description"] as String
        val hint = exercise["hint"] as String
        val isCompleted = exercise["isCompleted"] as Boolean

        val type = ExerciseType.entries.find { it.value == typeValue }

        return when (type) {
            ExerciseType.InputQuizType -> {
                val correctAnswer = exercise["correctAnswer"] as String
                InputQuizExercise(id, name, description, hint, isCompleted, correctAnswer)
            }

            ExerciseType.OptionsQuizType -> {
                val options = exercise["options"] as List<String>
                val correctOption = (exercise["correctOption"] as Double).toInt()
                OptionsQuizExercise(
                    id,
                    name,
                    description,
                    hint,
                    isCompleted,
                    options,
                    correctOption
                )
            }

            ExerciseType.GameType -> {
                // TODO: Implement GameExercise deserialization
                throw UnsupportedOperationException("GameExercise deserialization is not implemented")
            }

            else -> {
                throw IllegalArgumentException("Unknown exercise type")
            }
        }
    }
}