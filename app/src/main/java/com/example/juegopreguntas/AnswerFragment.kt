package com.example.juegopreguntas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class AnswerFragment : Fragment() {

    private lateinit var feedbackTextView: TextView
    private lateinit var nextButton: Button
    private var isCorrect: Boolean = false
    private var correctAnswer: String? = null
    private var questionIndex: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Obtener los datos del Bundle
        arguments?.let {
            isCorrect = it.getBoolean("is_correct")
            correctAnswer = it.getString("correct_answer")
            questionIndex = it.getInt("question_index")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_answer, container, false)

        // Inicializar las vistas
        feedbackTextView = view.findViewById(R.id.feedback_text_view)
        nextButton = view.findViewById(R.id.next_button)

        // Mostrar el mensaje de retroalimentación
        if (isCorrect) {
            feedbackTextView.text = "¡Felicidades! Respuesta correcta."
        } else {
            feedbackTextView.text = "Te equivocaste. La respuesta correcta es: $correctAnswer"
        }

        // Configurar el botón "Siguiente" para cargar la siguiente pregunta
        nextButton.setOnClickListener {
            val nextQuestionIndex = questionIndex + 1
            val nextQuestionFragment = QuestionFragment.createQuestionFragment(nextQuestionIndex)

            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, nextQuestionFragment)
                .addToBackStack(null)
                .commit()
        }

        return view
    }

    companion object {
        fun createAnswerFragment(isCorrect: Boolean, correctAnswer: String?, questionIndex: Int): AnswerFragment {
            val fragment = AnswerFragment()
            val args = Bundle()
            args.putBoolean("is_correct", isCorrect)
            args.putString("correct_answer", correctAnswer)
            args.putInt("question_index", questionIndex)
            fragment.arguments = args
            return fragment
        }
    }
}

