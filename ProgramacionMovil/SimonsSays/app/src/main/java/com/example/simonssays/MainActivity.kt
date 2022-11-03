package com.example.simonssays

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickBtnJugar(view: View) {
        val intent = Intent(this, Juego::class.java)

        val builder = AlertDialog.Builder(this)
        val dificultades = arrayOf("Fácil", "Normal", "Difícil", "Psicosis")
        builder.setTitle("Seleccione la dificultad")
            .setItems(dificultades,
                DialogInterface.OnClickListener { _, which ->
                    // The 'which' argument contains the index position
                    // of the selected item
                    when (which) {
                        0 -> {
                            intent.putExtra("pausa", 600L)
                            startActivity(intent)
                        }
                        1 -> {
                            intent.putExtra("pausa", 400L)
                            startActivity(intent)
                        }
                        2 -> {
                            intent.putExtra("pausa", 200L)
                            startActivity(intent)
                        }
                        3 -> {
                            intent.putExtra("pausa", 100L)
                            startActivity(intent)
                        }
                    }
                })
        builder.create().show()
    }
}