package com.example.simonssays

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.room.Room
import com.example.simonssays.Room.TasksDatabase

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
    }

    fun onClickBtnPuntuaciones(view: View) {
        val intent = Intent(this, Puntuaciones::class.java)
        startActivity(intent)
    }

    fun onClickBtnJugar(view: View) {
        val intent = Intent(this, Juego::class.java)

        // Muestro un mensaje con 4 opciones, y según la escogida inicio la actividad Juego pasándoles un long por Bundle
        // Este long hace referencia al tiempo de pausa entre pulsaciones del juego, a menor sea, mayor será la dificultad
        val builder = AlertDialog.Builder(this)
        val dificultades = arrayOf("Fácil", "Normal", "Difícil", "Psicosis")
        builder.setTitle("Seleccione la dificultad")
            .setItems(dificultades,
                DialogInterface.OnClickListener { _, which ->
                    // The 'which' argument contains the index position
                    // of the selected item
                    when (which) {
                        0 -> {
                            intent.putExtra("dificultad", 0)
                            startActivity(intent)
                        }
                        1 -> {
                            intent.putExtra("dificultad", 1)
                            startActivity(intent)
                        }
                        2 -> {
                            intent.putExtra("dificultad", 2)
                            startActivity(intent)
                        }
                        3 -> {
                            intent.putExtra("dificultad", 3)
                            startActivity(intent)
                        }
                    }
                })
        builder.create().show()
    }
}