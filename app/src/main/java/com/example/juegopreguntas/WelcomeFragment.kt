package com.example.juegopreguntas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

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
                // Ocultar el mensaje de error si todo está bien y continuar con el juego
                errorTextView.visibility = View.GONE

                // Iniciar el juego llamando a MainActivity
                val intent = Intent(activity, MainActivity::class.java)
                intent.putExtra("PLAYER_NAME", playerName)
                startActivity(intent)
            }
        }

        return view
    }
}