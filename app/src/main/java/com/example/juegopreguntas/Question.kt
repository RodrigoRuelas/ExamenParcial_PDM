package com.example.juegopreguntas

data class Question(
    val text: String,
    val options: List<String>,
    val correctAnswerIndex: Int
)

