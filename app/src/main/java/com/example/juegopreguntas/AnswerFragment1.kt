package com.example.juegopreguntas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class AnswerFragment1 : Fragment() {

    private lateinit var feedbackTextView: TextView
    private lateinit var nextButton: Button

    private var isCorrect: Boolean = false
    private var correctAnswer: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Obtener los datos del Bundle
        arguments?.let {
            isCorrect = it.getBoolean("is_correct")
            correctAnswer = it.getString("correct_answer")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_answer, container, false)

        // Inicializar las vistas
        feedbackTextView = view.findViewById(R.id.feedback_text_view)
        nextButton = view.findViewById(R.id.next_button) // Asegúrate de tener este botón en tu XML

        // Mostrar el mensaje de retroalimentación
        if (isCorrect) {
            feedbackTextView.text = "¡Felicidades! Respuesta correcta."
        } else {
            feedbackTextView.text = "Te equivocaste. La respuesta correcta es: $correctAnswer"
        }

        // Configurar el botón "Siguiente"
        nextButton.setOnClickListener {
            loadNextQuestion() // Llamar a la función para cargar la siguiente pregunta
        }

        return view
    }

    private fun loadNextQuestion() {
        // Crear la siguiente pregunta
        val nextQuestionFragment = QuestionFragment2.createQuestionFragment2(
            "¿Cuál es la capital de Alemania?", // Nueva pregunta
            listOf("Berlín", "Munich", "Colonia", "Frankfurt"), // Opciones
            0 // Índice de la respuesta correcta
        )

        // Usar el FragmentManager para realizar la transacción
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, nextQuestionFragment)
            .addToBackStack(null)
            .commit()
    }

    companion object {
        fun createAnswerFragment1(isCorrect: Boolean, correctAnswer: String?): AnswerFragment1 {
            val fragment = AnswerFragment1()
            val args = Bundle()
            args.putBoolean("is_correct", isCorrect)
            args.putString("correct_answer", correctAnswer)
            fragment.arguments = args
            return fragment
        }
    }
}

