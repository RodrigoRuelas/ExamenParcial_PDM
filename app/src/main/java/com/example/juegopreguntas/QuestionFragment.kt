package com.example.juegopreguntas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class QuestionFragment : Fragment() {

    private lateinit var questionTextView: TextView
    private lateinit var optionButton1: Button
    private lateinit var optionButton2: Button
    private lateinit var optionButton3: Button
    private lateinit var optionButton4: Button

    private var questionIndex: Int = 0
    private var questions: List<Question> = listOf(
        Question("¿Cuál es la capital de Francia?", listOf("París", "Londres", "Berlín", "Madrid"), 0),
        Question("¿Cuál es la capital de España?", listOf("Madrid", "Barcelona", "Sevilla", "Valencia"), 0)
        // Agrega más preguntas aquí
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Obtener el índice actual de la pregunta del Bundle
        arguments?.let {
            questionIndex = it.getInt("question_index", 0)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_question, container, false)

        // Inicializar las vistas
        questionTextView = view.findViewById(R.id.question_text_view)
        optionButton1 = view.findViewById(R.id.option_button_1)
        optionButton2 = view.findViewById(R.id.option_button_2)
        optionButton3 = view.findViewById(R.id.option_button_3)
        optionButton4 = view.findViewById(R.id.option_button_4)

        // Cargar la pregunta actual
        loadQuestion()

        // Configurar la lógica de los botones de opciones
        optionButton1.setOnClickListener { checkAnswer(0) }
        optionButton2.setOnClickListener { checkAnswer(1) }
        optionButton3.setOnClickListener { checkAnswer(2) }
        optionButton4.setOnClickListener { checkAnswer(3) }

        return view
    }

    private fun loadQuestion() {
        val currentQuestion = questions[questionIndex]
        questionTextView.text = currentQuestion.text
        optionButton1.text = currentQuestion.options[0]
        optionButton2.text = currentQuestion.options[1]
        optionButton3.text = currentQuestion.options[2]
        optionButton4.text = currentQuestion.options[3]
    }

    private fun checkAnswer(selectedIndex: Int) {
        val isCorrect = selectedIndex == questions[questionIndex].correctAnswerIndex
        val correctAnswer = questions[questionIndex].options[questions[questionIndex].correctAnswerIndex]

        // Navegar a AnswerFragment con los resultados
        val answerFragment = AnswerFragment.createAnswerFragment(isCorrect, correctAnswer, questionIndex)
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, answerFragment)
            .addToBackStack(null)
            .commit()
    }

    companion object {
        fun createQuestionFragment(questionIndex: Int): QuestionFragment {
            val fragment = QuestionFragment()
            val args = Bundle()
            args.putInt("question_index", questionIndex)
            fragment.arguments = args
            return fragment
        }
    }
}


