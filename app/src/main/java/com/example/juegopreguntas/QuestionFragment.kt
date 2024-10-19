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
        Question("1. ¿En qué año se declaró la independencia de Perú?", listOf("1942", "1821", "1825", "1830"), 1),
        Question("2. ¿Cuál es la capital de Perú?", listOf("Arequipa", "Cusco", "Lima", "Tacna"), 2),
        Question("3. ¿Qué sitio arqueológico es considerado una de las siete maravillas del mundo moderno en Perú?", listOf("Machu Picchu", "Ciudad Sagrada de Caral", "Valle Sagrado de los Incas", "Templo del Sol Coricancha"), 0),
        Question("4. ¿Cuál es el río más largo de Perú?", listOf("Rio Chili", "Río Ucayali", "Rio Amazonas", "Rio Marañon"), 1),
        Question("5. ¿En qué región geográfica del país se encuentra Machu Picchu?", listOf("Cuy chactado", "Causa peruana", "Ceviche", "Soltero de queso"), 2),
        Question("6. Unos de los idiomas oficiales del Peru es ... ", listOf("Shipibe", "Quechua", "Yine", "Chapra"), 1),
        Question("7. ¿Cuál es la montaña más alta de Perú?", listOf("Alpamayo", "Coropuna", "Huayna Picchu", "Huascarán"), 3),
        Question("8. ¿Quién fue el primer virrey del Perú?", listOf("Diego López de Zúñiga y Velasco", "Francisco de Toledo", "Blasco Núñez de Vela", "Antonio de Mendoza"), 2),
        Question("9. ¿Cómo se llama la bebida alcohólica tradicional de Perú?", listOf("Pisco", "Anis", "Vino", "Ron"), 0),
        Question("10. ¿En qué año se fundó la ciudad de Lima?", listOf("28 de julio de 1535", "15 de agosto de 1535", "18 de enero de 1535", "6 de abril de 1535"), 2),
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


