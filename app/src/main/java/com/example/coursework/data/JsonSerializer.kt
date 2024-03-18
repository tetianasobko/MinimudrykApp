package com.example.coursework.data

import com.example.coursework.models.*
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

class JsonSerializer {
    private val gson = Gson()

    fun updateExerciseInMathTopic(updatedExercise: Exercise, filePath: String) {
        val fileInputStream = FileInputStream(filePath)

        val mathTopics = deserializeMathTopics(filePath)

        fileInputStream.close()

        val mathTopicToUpdate = mathTopics.find { it.id == updatedExercise.mathTopicId }

        if (mathTopicToUpdate != null) {
            val updatedExercises = mathTopicToUpdate.exercises.map { existingExercise ->
                if (existingExercise.id == updatedExercise.id) {
                    updatedExercise
                } else {
                    existingExercise
                }
            }

            val updatedMathTopic = mathTopicToUpdate.copy(exercises = updatedExercises)

            val updatedMathTopics = mathTopics.map { existingMathTopic ->
                if (existingMathTopic.id == mathTopicToUpdate.id) {
                    updatedMathTopic
                } else {
                    existingMathTopic
                }
            }
            val jsonArray = JsonArray()

            updatedMathTopics.forEach { mathTopic ->
                val jsonTopic = JsonObject()
                jsonTopic.addProperty("id", mathTopic.id)
                jsonTopic.addProperty("name", mathTopic.name)

                val jsonExercises = JsonArray()
                mathTopic.exercises.forEach { exercise ->
                    jsonExercises.add(serializeExercise(exercise))
                }

                jsonTopic.add("exercises", jsonExercises)
                jsonArray.add(jsonTopic)
            }

            val jsonString = jsonArray.toString()
            val fileOutputStream = FileOutputStream(filePath)
            fileOutputStream.write(jsonString.toByteArray())
        } else {
            println("Math topic with ID ${updatedExercise.mathTopicId} not found.")
        }
    }

private fun serializeExercise(exercise: Exercise): JsonObject {
    val jsonExercise = JsonObject()
    jsonExercise.addProperty("id", exercise.id)
    jsonExercise.addProperty("name", exercise.name)
    jsonExercise.addProperty("type", exercise.type.ordinal)
    jsonExercise.addProperty("mathTopicId", exercise.mathTopicId)
    jsonExercise.addProperty("description", exercise.description)
    jsonExercise.addProperty("hint", exercise.hint)
    jsonExercise.addProperty("isCompleted", exercise.isCompleted)

    // Additional properties for specific exercise types
    when (exercise) {
        is InputQuizExercise -> {
            jsonExercise.addProperty("correctAnswer", exercise.correctAnswer)
        }

        is OptionsQuizExercise -> {
            jsonExercise.add("options", exercise.options.toJsonArray())
            jsonExercise.addProperty("correctOption", exercise.correctOption)
        }
    }

    return jsonExercise
}

    private fun List<String>.toJsonArray(): JsonArray {
        val jsonArray = JsonArray()
        this.forEach { jsonArray.add(it) }
        return jsonArray
    }


    fun deserializeMathTopics(filePath: String): List<MathTopic> {
        val fileInputStream = FileInputStream(filePath)

        val mathTopics = mutableListOf<MathTopic>()

        try {
            val jsonString = fileInputStream.bufferedReader().use { it.readText() }
            fileInputStream.close()
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
        val mathTopicId = (exercise["mathTopicId"] as Double).toInt()
        val description = exercise["description"] as String
        val hint = exercise["hint"] as String
        val isCompleted = exercise["isCompleted"] as Boolean

        val type = ExerciseType.entries.find { it.value == typeValue }

        return when (type) {
            ExerciseType.InputQuizType -> {
                val correctAnswer = exercise["correctAnswer"] as String
                InputQuizExercise(id, name, mathTopicId, description, hint, isCompleted, correctAnswer)
            }

            ExerciseType.OptionsQuizType -> {
                val options = exercise["options"] as List<String>
                val correctOption = (exercise["correctOption"] as Double).toInt()
                OptionsQuizExercise(
                    id,
                    name,
                    mathTopicId,
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