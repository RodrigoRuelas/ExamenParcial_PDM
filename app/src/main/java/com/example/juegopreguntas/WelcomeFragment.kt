package com.example.juegopreguntas

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment

class WelcomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout del fragmento
        val view = inflater.inflate(R.layout.fragment_welcome, container, false)

        // Encontrar los elementos de la interfaz
        val playerNameEditText: EditText = view.findViewById(R.id.player_name_edit_text)
        val startButton: Button = view.findViewById(R.id.start_button)
        val errorTextView: TextView = view.findViewById(R.id.error_text_view)

        // Configurar el clic del botón para validar el nombre
        startButton.setOnClickListener {
            val playerName = playerNameEditText.text.toString().trim()

            if (playerName.isEmpty()) {
                // Mostrar error si no se ha ingresado un nombre
                errorTextView.text = "Por favor, ingresa tu nombre para comenzar el juego."
                errorTextView.visibility = View.VISIBLE
            } else {
                errorTextView.visibility = View.GONE
                // Reemplazar WelcomeFragment con QuestionFragment
                val questionFragment = QuestionFragment()

                // Usar el FragmentManager para realizar la transacción
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, questionFragment)
                    .addToBackStack(null) // Opcional, si quieres permitir volver atrás
                    .commit()
            }
        }

        return view
    }
}
