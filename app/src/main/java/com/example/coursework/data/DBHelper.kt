package com.example.coursework.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.coursework.models.Exercise
import com.example.coursework.models.ExerciseType
import com.example.coursework.models.GameExercise
import com.example.coursework.models.InputQuizExercise
import com.example.coursework.models.MathTopic
import com.example.coursework.models.OptionsQuizExercise

class DBHelper(context: Context) : SQLiteOpenHelper(
    context,
    DATABASE_NAME,
    null,
    DATABASE_VERSION
) {
    companion object {
        private const val DATABASE_NAME = "app.db"
        private const val DATABASE_VERSION = 1

        const val TOPICS_TABLE_NAME = "topics"
        const val EXERCISES_TABLE_NAME = "exercises"
        const val ANSWERS_TABLE_NAME = "answers"

        const val TOPIC_ID = "id"
        const val TOPIC_NAME = "name"

        const val EXERCISE_ID = "id"
        const val EXERCISE_NAME = "name"
        const val EXERCISE_TYPE = "type"
        const val EXERCISE_TOPIC_ID = "mathTopicId"
        const val EXERCISE_DESCRIPTION = "description"
        const val EXERCISE_HINT = "hint"
        const val EXERCISE_IS_COMPLETED = "isCompleted"
        const val EXERCISE_CORRECT_ANSWER = "correctAnswer"
        const val EXERCISE_OPTIONS = "options"
        const val EXERCISE_CORRECT_OPTION = "correctOption"
        const val EXERCISE_GAME_NAME = "game"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTopicsTableQuery = "CREATE TABLE $TOPICS_TABLE_NAME (" +
                "$TOPIC_ID INTEGER PRIMARY KEY," +
                "$TOPIC_NAME TEXT NOT NULL" +
                ")"
        db?.execSQL(createTopicsTableQuery)

        val createExercisesTableQuery = "CREATE TABLE $EXERCISES_TABLE_NAME (" +
                "$EXERCISE_ID INTEGER PRIMARY KEY," +
                "$EXERCISE_NAME TEXT NOT NULL," +
                "$EXERCISE_TYPE INTEGER NOT NULL," +
                "$EXERCISE_TOPIC_ID INTEGER NOT NULL," +
                "$EXERCISE_DESCRIPTION TEXT NOT NULL," +
                "$EXERCISE_HINT TEXT NOT NULL," +
                "$EXERCISE_IS_COMPLETED INTEGER NOT NULL," +
                "FOREIGN KEY($EXERCISE_TOPIC_ID) REFERENCES $TOPICS_TABLE_NAME($TOPIC_ID)" +
                ")"
        db?.execSQL(createExercisesTableQuery)

        val createAnswersTableQuery = "CREATE TABLE $ANSWERS_TABLE_NAME (" +
                "$EXERCISE_ID INTEGER PRIMARY KEY," +
                "$EXERCISE_CORRECT_ANSWER TEXT," +
                "$EXERCISE_OPTIONS TEXT," +
                "$EXERCISE_CORRECT_OPTION INTEGER," +
                "$EXERCISE_GAME_NAME TEXT," +
                "FOREIGN KEY($EXERCISE_ID) REFERENCES $EXERCISES_TABLE_NAME($EXERCISE_ID)" +
                ")"
        db?.execSQL(createAnswersTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun addTopic(mathTopic: MathTopic): Long {
        val db = writableDatabase
        val contentValues = ContentValues().apply {
            put(TOPIC_NAME, mathTopic.name)
        }
        val id = db.insert(TOPICS_TABLE_NAME, null, contentValues)
        mathTopic.id = id
        return id
    }

    fun addExercise(exercise: Exercise): Long {
        val db = writableDatabase
        val contentValues = ContentValues().apply {
            put(EXERCISE_NAME, exercise.name)
            put(EXERCISE_DESCRIPTION, exercise.description)
            put(EXERCISE_HINT, exercise.hint)
            put(EXERCISE_IS_COMPLETED, exercise.isCompleted)
            put(EXERCISE_TOPIC_ID, exercise.mathTopicId)
            put(EXERCISE_TYPE, exercise.type.value)
        }
        val id = db.insert(EXERCISES_TABLE_NAME, null, contentValues)
        exercise.id = id

        when (exercise.type) {
            ExerciseType.InputQuizType -> {
                val answerContentValues = ContentValues().apply {
                    put(EXERCISE_CORRECT_ANSWER, (exercise as InputQuizExercise).correctAnswer)
                }
                db.insert(ANSWERS_TABLE_NAME, null, answerContentValues)
            }

            ExerciseType.OptionsQuizType -> {
                val answerContentValues = ContentValues().apply {
                    exercise as OptionsQuizExercise
                    put(EXERCISE_OPTIONS, exercise.options.joinToString(","))
                    put(EXERCISE_CORRECT_OPTION, exercise.correctOption)
                }
                db.insert(ANSWERS_TABLE_NAME, null, answerContentValues)
            }

            ExerciseType.GameType -> {
                val answerContentValues = ContentValues().apply {
                    exercise as GameExercise
                    put(EXERCISE_GAME_NAME, exercise.game)
                }
                db.insert(ANSWERS_TABLE_NAME, null, answerContentValues)
            }

            else -> {
                throw IllegalArgumentException("Unknown exercise type")
            }
        }

        return id
    }

    fun updateExercise(exercise: Exercise): Int {
        val db = writableDatabase
        val contentValues = ContentValues().apply {
            put(EXERCISE_NAME, exercise.name)
            put(EXERCISE_DESCRIPTION, exercise.description)
            put(EXERCISE_HINT, exercise.hint)
            put(EXERCISE_IS_COMPLETED, exercise.isCompleted)
            put(EXERCISE_TOPIC_ID, exercise.mathTopicId)
            put(EXERCISE_TYPE, exercise.type.value)
        }
        val selection = "$EXERCISE_ID = ?"
        val selectionArgs = arrayOf(exercise.id.toString())
        val rowsAffected = db.update(EXERCISES_TABLE_NAME, contentValues, selection, selectionArgs)

        when (exercise.type) {
            ExerciseType.InputQuizType -> {
                val answerContentValues = ContentValues().apply {
                    put(EXERCISE_CORRECT_ANSWER, (exercise as InputQuizExercise).correctAnswer)
                }
                val selection = "$EXERCISE_ID = ?"
                val selectionArgs = arrayOf(exercise.id.toString())
                db.update(ANSWERS_TABLE_NAME, answerContentValues, selection, selectionArgs)
            }

            ExerciseType.OptionsQuizType -> {
                val answerContentValues = ContentValues().apply {
                    exercise as OptionsQuizExercise
                    put(EXERCISE_OPTIONS, exercise.options.joinToString(","))
                    put(EXERCISE_CORRECT_OPTION, exercise.correctOption)
                }
                val selection = "$EXERCISE_ID = ?"
                val selectionArgs = arrayOf(exercise.id.toString())
                db.update(ANSWERS_TABLE_NAME, answerContentValues, selection, selectionArgs)
            }

            ExerciseType.GameType -> {
                val answerContentValues = ContentValues().apply {
                    exercise as GameExercise
                    put(EXERCISE_GAME_NAME, exercise.game)
                }
                val selection = "$EXERCISE_ID = ?"
                val selectionArgs = arrayOf(exercise.id.toString())
                db.update(ANSWERS_TABLE_NAME, answerContentValues, selection, selectionArgs)
            }

            else -> {
                throw IllegalArgumentException("Unknown exercise type")
            }
        }

        return rowsAffected
    }

    fun getAllMathTopics(): List<MathTopic> {
        val topics = mutableListOf<MathTopic>()
        val db = readableDatabase
        val cursor = db.query("topics", null, null, null, null, null, null)
        while (cursor.moveToNext()) {
            val id = cursor.getLong(cursor.getColumnIndexOrThrow(TOPIC_ID))
            val name = cursor.getString(cursor.getColumnIndexOrThrow(TOPIC_NAME))
            val mathTopic = MathTopic(id, name)
            topics.add(mathTopic)
        }
        cursor.close()
        return topics
    }

    fun deleteFromTopics() {
        val db = writableDatabase
        val createAnswersTableQuery = "DELETE FROM $TOPICS_TABLE_NAME"
        db?.execSQL(createAnswersTableQuery)
    }

    fun getExercisesByTopicId(mathTopicId: Long): List<Exercise> {
        val exercises = mutableListOf<Exercise>()
        val db = readableDatabase
        val selection = "$EXERCISE_TOPIC_ID = ?"
        val selectionArgs = arrayOf(mathTopicId.toString())
        val cursor = db.query(
            EXERCISES_TABLE_NAME,
            null,
            selection,
            selectionArgs,
            null,
            null,
            null
        )
        while (cursor.moveToNext()) {
            val id = cursor.getLong(cursor.getColumnIndexOrThrow(EXERCISE_ID))
            val name = cursor.getString(cursor.getColumnIndexOrThrow(EXERCISE_NAME))
            val typeValue = cursor.getInt(cursor.getColumnIndexOrThrow(EXERCISE_TYPE))
            val type = ExerciseType.entries.find { it.value == typeValue }
            val mathTopicId = cursor.getLong(cursor.getColumnIndexOrThrow(EXERCISE_TOPIC_ID))
            val description = cursor.getString(cursor.getColumnIndexOrThrow(EXERCISE_DESCRIPTION))
            val hint = cursor.getString(cursor.getColumnIndexOrThrow(EXERCISE_HINT))
            val isCompleted =
                cursor.getInt(cursor.getColumnIndexOrThrow(EXERCISE_IS_COMPLETED)) != 0

            val exercise = when (type) {
                ExerciseType.InputQuizType -> {
                    val correctAnswer =
                        db.query(
                            ANSWERS_TABLE_NAME,
                            arrayOf(EXERCISE_CORRECT_ANSWER),
                            "$EXERCISE_ID = ?",
                            arrayOf(id.toString()),
                            null,
                            null,
                            null
                        ).use {
                            if (it.moveToFirst()) {
                                it.getString(it.getColumnIndexOrThrow(EXERCISE_CORRECT_ANSWER))
                            } else {
                                null
                            }
                        }
                    InputQuizExercise(
                        id,
                        name,
                        mathTopicId,
                        description,
                        hint,
                        isCompleted,
                        correctAnswer
                    )
                }

                ExerciseType.OptionsQuizType -> {
                    val optionsCursor =
                        db.query(
                            ANSWERS_TABLE_NAME,
                            arrayOf(EXERCISE_OPTIONS),
                            "$EXERCISE_ID = ?",
                            arrayOf(id.toString()),
                            null,
                            null,
                            null
                        )
                    val options = mutableListOf<String>()
                    while (optionsCursor.moveToNext()) {
                        val optionsString = optionsCursor.getString(
                            optionsCursor.getColumnIndexOrThrow(EXERCISE_OPTIONS)
                        )

                        options.addAll(optionsString.split(","))
                    }
                    optionsCursor.close()

                    val correctOption =
                        db.query(
                            ANSWERS_TABLE_NAME,
                            arrayOf(EXERCISE_CORRECT_OPTION),
                            "$EXERCISE_ID = ?",
                            arrayOf(id.toString()),
                            null,
                            null,
                            null
                        ).use {
                            if (it.moveToFirst()) {
                                it.getInt(it.getColumnIndexOrThrow(EXERCISE_CORRECT_OPTION))
                            } else {
                                null
                            }
                        }
                    if (correctOption != null) {
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
                    } else {

                    }
                }

                ExerciseType.GameType -> {
                    val game =
                        db.query(
                            ANSWERS_TABLE_NAME,
                            arrayOf(EXERCISE_GAME_NAME),
                            "$EXERCISE_ID = ?",
                            arrayOf(id.toString()),
                            null,
                            null,
                            null
                        ).use {
                            if (it.moveToFirst()) {
                                it.getString(it.getColumnIndexOrThrow(EXERCISE_GAME_NAME))
                            } else {
                                null
                            }
                        }
                    if (game != null) {
                        GameExercise(
                            id,
                            name,
                            mathTopicId,
                            description,
                            isCompleted,
                            game
                        )
                    } else {

                    }

                }


                else -> {
                    throw IllegalArgumentException("Unknown exercise type")
                }
            }

            exercises.add(exercise as Exercise)
        }

        cursor.close()
        return exercises
    }

    fun dropDatabase() {
        val db = writableDatabase
        val dropAnswers = "DROP TABLE $ANSWERS_TABLE_NAME;"
        db?.execSQL(dropAnswers)

        val dropExercises = "DROP TABLE $EXERCISES_TABLE_NAME;"
        db?.execSQL(dropExercises)

        val dropTopics = "DROP TABLE $TOPICS_TABLE_NAME;"
        db?.execSQL(dropTopics)
    }

    fun seedDatabase() {
        val dbHelper = writableDatabase
        // Clear existing data
        dbHelper.delete(EXERCISES_TABLE_NAME, null, null)
        dbHelper.delete(TOPICS_TABLE_NAME, null, null)

        // Insert math topics
        val mathTopicId1 = addTopic(MathTopic(id = 1, name = "Задачі"))
        val mathTopicId2 = addTopic(MathTopic(id = 2, name = "Логіка"))
        val mathTopicId3 = addTopic(MathTopic(id = 3, name = "Алгебра"))

        // Insert exercises
        val exercises = arrayListOf(
            InputQuizExercise(
                id = 1,
                name = "Вікова математика",
                mathTopicId = mathTopicId1,
                description = "Син вдвічі молодший за батька. Він народився, коли батькові було 24 роки. Скільки років синові?",
                hint = "Порівняйте вік батька та сина. Врахуйте, коли син народився.",
                isCompleted = false,
                correctAnswer = "24"
            ),
            OptionsQuizExercise(
                id = 2,
                name = "Трикутникова загадка",
                mathTopicId = mathTopicId1,
                description = "В рівнобедреному трикутнику один з кутів дорівнює 45 градусів. Який це трикутник – гострокутний, тупокутний чи прямокутний?",
                hint = "Згадайте властивості кутів у трикутнику.",
                isCompleted = false,
                options = listOf("Гострокутний", "Тупокутний", "Прямий"),
                correctOption = 0
            ),
            InputQuizExercise(
                id = 3,
                name = "Ступінь сімки 1",
                mathTopicId = mathTopicId1,
                description = "Знайдіть останню цифру числа 7^7",
                hint = "Досліджуйте останні цифри при піднятті сімки в ступені.",
                isCompleted = false,
                correctAnswer = "3"
            ),
            InputQuizExercise(
                id = 4,
                name = "Ступінь сімки 2",
                mathTopicId = mathTopicId1,
                description = "Знайдіть останню цифру числа 7^(7^7)",
                hint = "Досліджуйте останні цифри при піднятті сімки в ступені.",
                isCompleted = false,
                correctAnswer = "3"
            ),
            InputQuizExercise(
                id = 5,
                name = "Ступінь сімки 3",
                mathTopicId = mathTopicId1,
                description = "Знайдіть останню цифру числа 7^(7^(7^7))",
                hint = "Досліджуйте останні цифри при піднятті сімки в ступені.",
                isCompleted = false,
                correctAnswer = "3"
            ),
            InputQuizExercise(
                id = 6,
                name = "Магічна дошка чисел 1",
                mathTopicId = mathTopicId1,
                description = "На дошці записані числа 1,2,3,...,20,21. Можна стерти будь-які два числа a і b і записати замість них число ab. Яке число отримається після 20 таких дій?",
                hint = "Якщо стерти два числа і записати їхнє добуток, як це впливає на суму чисел на дошці?",
                isCompleted = false,
                correctAnswer = "20!"
            ),
            InputQuizExercise(
                id = 7,
                name = "Магічна дошка чисел 2",
                mathTopicId = mathTopicId1,
                description = "На дошці записані числа 1,2,3,...,20,21. Можна стерти будь-які два числа a і b і записати замість них число a+b. Якечисло отримається після 20 таких дій?",
                hint = "Якщо стерти два числа і записати їхню суму, як це впливає на суму чисел на дошці?",
                isCompleted = false,
                correctAnswer = "231"
            ),
            InputQuizExercise(
                id = 8,
                name = "Магічна дошка чисел 3",
                mathTopicId = mathTopicId1,
                description = "На дошці записані числа 1,2,3,...,20,21. Можна стерти будь-які два числа a і b і записати замість них число a+b-2. Яке число отримається після 20 таких дій?",
                hint = "Якщо стерти два числа і записати їхню різницю, зменшену на 2, як це впливає на суму чисел на дошці?",
                isCompleted = false,
                correctAnswer = "191"
            ),
            OptionsQuizExercise(
                id = 9,
                name = "Шахова прогулянка коня",
                mathTopicId = mathTopicId1,
                description = "Чи може за 1000 ходів кінь потрапити з клітинки а1 в а8?",
                hint = "Рухайте кінь так, щоб визначити, чи можливо йому потрапити з а1 в а8 за 1000 ходів.",
                isCompleted = false,
                options = listOf("Так", "Ні"),
                correctOption = 0
            ),
            OptionsQuizExercise(
                id = 10,
                name = "Шахова мандри слона",
                mathTopicId = mathTopicId1,
                description = "Чи може за 1000 ходів слон потрапити з клітинки а1 в а8?",
                hint = "Рухайте слона так, щоб визначити, чи можливо йому потрапити з а1 в а8 за 1000 ходів.",
                isCompleted = false,
                options = listOf("Так", "Ні"),
                correctOption = 1
            ),
            OptionsQuizExercise(
                id = 11,
                name = "Геометрія з Гомером",
                mathTopicId = mathTopicId1,
                description = "Гомер Сімпсон вважає, що якщо є два прямокутники. Площа і периметр першого прямокутника більші за площу і переметр другого. Тоді можна вирізати з першого другой. Чи правий Гомер?",
                hint = "Розгляньте умову Гомера та подумайте, чи це завжди відбувається з будь-якими прямокутниками.",
                isCompleted = false,
                options = listOf("Так", "Ні"),
                correctOption = 1
            ),
            OptionsQuizExercise(
                id = 12,
                name = "Головоломка з цифрами",
                mathTopicId = mathTopicId1,
                description = "Чи існує натуральне число, в якого сума цифр більше, ніж сума цифр його квадрата?",
                hint = "Порівняйте суму цифр числа з сумою цифр його квадрата. Пригадайте властивості чисел та їхніх квадратів.",
                isCompleted = false,
                options = listOf("Так", "Ні"),
                correctOption = 0
            ),
            GameExercise(
                id = 13,
                name = "Лабіринт Прямокутника",
                mathTopicId = 2,
                description = "У прямокутнику 5 на 9 в лівону нижньому кутку стоїть фішка. Гравці по черзі пересувають її на будь-яку кількість клітинок або вгору, або вправо. Виграє той, хто поставить фішку в правий верхній кут.",
                game = "RectangularPuzzle",
                isCompleted = false,
            ),
            GameExercise(
                id = 14,
                name = "Множинна гонка до 1000",
                mathTopicId = mathTopicId2,
                description = "Перший гравець називає ціле число від 2 до 9. Другий гравець множить це число на довільне ціле число від 2 до 9. Потім перший множить результат на будь-яке ціле число від 2 до 9, і так далі. Виграє той, хто першим отримає добуток більший, ніж 1000.",
                game = "MultiplicativeRace",
                isCompleted = false,
            ),
            GameExercise(
                id = 15,
                name = "Алгебра",
                mathTopicId = mathTopicId3,
                description = "description",
                game = "Calculator",
                isCompleted = false,
            )
        )

        // Insert exercises into the database
        for (exercise in exercises) {
            val exerciseId = addExercise(exercise)
            exercise.id = exerciseId

            // Associate the exercise with the math topic
            val mathTopicId = if (exercise.mathTopicId == 0L) {
                mathTopicId1
            } else {
                exercise.mathTopicId
            }

            val contentValues = ContentValues().apply {
                put(EXERCISE_TOPIC_ID, mathTopicId)
            }

            val selection = "${EXERCISE_ID} = ?"
            val selectionArgs = arrayOf(exerciseId.toString())

            dbHelper.update(
                EXERCISES_TABLE_NAME,
                contentValues,
                selection,
                selectionArgs
            )
        }
    }
}