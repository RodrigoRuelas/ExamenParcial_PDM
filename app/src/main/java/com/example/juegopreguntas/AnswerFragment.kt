package com.example.juegopreguntas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class AnswerFragment : Fragment() {

    private lateinit var feedbackTextView: TextView

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

        // Mostrar el mensaje de retroalimentación
        if (isCorrect) {
            feedbackTextView.text = "¡Felicidades! Respuesta correcta."
        } else {
            feedbackTextView.text = "Te equivocaste. La respuesta correcta es: $correctAnswer"
        }

        return view
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

