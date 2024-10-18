package com.example.juegopreguntas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class AnswerFragment : Fragment() {

    private lateinit var resultTextView: TextView
    private lateinit var nextButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_answer, container, false)

        resultTextView = view.findViewById(R.id.feedback_text_view)
        nextButton = view.findViewById(R.id.next_button)

        // Obtener los argumentos de la pregunta
        val isCorrect = arguments?.getBoolean("is_correct") ?: false
        val correctAnswer = arguments?.getString("correct_answer")

        // Mostrar el resultado
        resultTextView.text = if (isCorrect) {
            "¡Felicitaciones! Respuesta correcta."
        } else {
            "Te equivocaste. La respuesta correcta es: $correctAnswer"
        }

        // Configurar el botón "Siguiente"
        nextButton.setOnClickListener {
            // Aquí puedes cargar el siguiente QuestionFragment
            loadNextQuestion()
        }

        return view
    }

    private fun loadNextQuestion() {
        // Crear la siguiente pregunta
        val nextQuestionFragment = QuestionFragment1.createQuestionFragment(
            "¿Cuál es la capital de Italia?", // Nueva pregunta
            listOf("Roma", "Milán", "Nápoles", "Turín"), // Opciones
            0 // Índice de la respuesta correcta
        )

        // Usar el FragmentManager para realizar la transacción
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, nextQuestionFragment)
            .addToBackStack(null)
            .commit()
    }

    companion object {
        fun createAnswerFragment(isCorrect: Boolean, correctAnswer: String?): AnswerFragment {
            val fragment = AnswerFragment()
            val args = Bundle()
            args.putBoolean("is_correct", isCorrect)
            args.putString("correct_answer", correctAnswer)
            fragment.arguments = args
            return fragment
        }
    }
}

