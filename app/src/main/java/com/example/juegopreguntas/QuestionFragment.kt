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

    // Variables para los datos de la pregunta
    private var questionText: String? = null
    private var options: List<String>? = null
    private var correctAnswerIndex: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Obtener los datos de la pregunta del Bundle
        arguments?.let {
            questionText = it.getString("question_text")
            options = it.getStringArrayList("options")
            correctAnswerIndex = it.getInt("correct_answer_index")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout del fragmento
        val view = inflater.inflate(R.layout.fragment_question, container, false)

        // Inicializar los elementos de la interfaz
        questionTextView = view.findViewById(R.id.question_text_view)
        optionButton1 = view.findViewById(R.id.option_button_1)
        optionButton2 = view.findViewById(R.id.option_button_2)
        optionButton3 = view.findViewById(R.id.option_button_3)
        optionButton4 = view.findViewById(R.id.option_button_4)

        // Cargar los datos de la pregunta
        loadQuestion()

        // Configurar los clics de los botones
        optionButton1.setOnClickListener { checkAnswer(0) }
        optionButton2.setOnClickListener { checkAnswer(1) }
        optionButton3.setOnClickListener { checkAnswer(2) }
        optionButton4.setOnClickListener { checkAnswer(3) }

        return view
    }

    private fun loadQuestion() {
        questionTextView.text = questionText
        options?.let {
            optionButton1.text = it[0]
            optionButton2.text = it[1]
            optionButton3.text = it[2]
            optionButton4.text = it[3]
        }
    }

    private fun checkAnswer(selectedOption: Int) {
        if (selectedOption == correctAnswerIndex) {
            // Respuesta correcta
        } else {
            // Respuesta incorrecta
        }

        // Después de seleccionar una respuesta, reemplazar con el siguiente fragmento
        val nextQuestionFragment = createQuestionFragment("Próxima pregunta...", listOf("Opc1", "Opc2", "Opc3", "Opc4"), 0)
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, nextQuestionFragment)
            .addToBackStack(null)
            .commit()
    }

    companion object {
        fun createQuestionFragment(question: String, options: List<String>, correctAnswerIndex: Int): QuestionFragment {
            val fragment = QuestionFragment()
            val args = Bundle()
            args.putString("question_text", question)
            args.putStringArrayList("options", ArrayList(options))
            args.putInt("correct_answer_index", correctAnswerIndex)
            fragment.arguments = args
            return fragment
        }
    }
}

