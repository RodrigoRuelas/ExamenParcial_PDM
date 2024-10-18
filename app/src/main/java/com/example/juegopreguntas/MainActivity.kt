package com.example.juegopreguntas

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Cargar WelcomeFragment al iniciar la actividad
        if (savedInstanceState == null) {
            loadFragment(WelcomeFragment())
        }
    }

    private fun loadFragment(fragment: Fragment) {
        // Método para cargar fragmentos en el contenedor
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

}