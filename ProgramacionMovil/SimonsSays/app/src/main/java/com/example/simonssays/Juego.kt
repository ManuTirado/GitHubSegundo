package com.example.simonssays

import android.content.DialogInterface
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class Juego : AppCompatActivity() {

    private var patron: MutableList<Int> = mutableListOf(1) // Lista con el patrón a seguir
    private var contPulsaciones: Int = 0

    private var pausa: Long = 500

    private var puntuacion: Int = 0
    private var record: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_juego)

        val bundle = intent.extras
        pausa = bundle?.getLong("pausa") ?: 500

        val btnVerde: Button = findViewById(R.id.btnVerde)
        val btnRojo: Button = findViewById(R.id.btnRojo)
        val btnAmarillo: Button = findViewById(R.id.btnAmarillo)
        val btnAzul: Button = findViewById(R.id.btnAzul)
        btnVerde.setOnClickListener {
            comprobarPulsacion(1)
        }
        btnRojo.setOnClickListener {
            comprobarPulsacion(2)
        }
        btnAmarillo.setOnClickListener {
            comprobarPulsacion(3)
        }
        btnAzul.setOnClickListener {
            comprobarPulsacion(4)
        }

        puntuacion = 0
        record = 0

        actualizarPuntuacion()
        actualizarRecord()

        mirar()

    }

    private fun mirar() {
        desactivarBotones()
        patron.add((1..4).random(Random(System.nanoTime())))
        GlobalScope.launch {
            for (item in patron) {
                iluminarBoton(item)
            }
            contPulsaciones = 0
            activarBotones()
        }
    }


    private suspend fun iluminarBoton(btn: Int) {
        delay(pausa)
        reproducirSonido(R.raw.seleccion)
        when (btn) {
            1 -> {
                val btn: Button = findViewById(R.id.btnVerde)
                btn.setBackgroundColor(resources.getColor(R.color.greenSelected))
                delay(pausa)
                btn.setBackgroundColor(resources.getColor(R.color.green))
            }
            2 -> {
                val btn: Button = findViewById(R.id.btnRojo)
                btn.setBackgroundColor(resources.getColor(R.color.redSelected))
                delay(pausa)
                btn.setBackgroundColor(resources.getColor(R.color.red))
            }
            3 -> {
                val btn: Button = findViewById(R.id.btnAmarillo)
                btn.setBackgroundColor(resources.getColor(R.color.yellowSelected))
                delay(pausa)
                btn.setBackgroundColor(resources.getColor(R.color.yellow))
            }
            4 -> {
                val btn: Button = findViewById(R.id.btnAzul)
                btn.setBackgroundColor(resources.getColor(R.color.blueSelected))
                delay(pausa)
                btn.setBackgroundColor(resources.getColor(R.color.blue))
            }

        }
    }

    private fun actualizarPuntuacion() {
        val txtPuntuacion: TextView = findViewById(R.id.txtPuntuacion)
        txtPuntuacion.text = puntuacion.toString()
    }

    private fun actualizarRecord() {
        val txtRecord: TextView = findViewById(R.id.txtRecord)
        txtRecord.text = record.toString()
    }

    private fun comprobarPulsacion(seleccion: Int) {
        if (seleccion == patron[contPulsaciones]) {
            reproducirSonido(R.raw.correcto)

            if (contPulsaciones == patron.size - 1) {
                puntuacion++
                actualizarPuntuacion()
                mirar()
            } else {
                contPulsaciones++
            }
        } else {
            reproducirSonido(R.raw.incorrecto)

            mostrarMensjaPerdedor()

            if (puntuacion > record) {
                record = puntuacion
            }
            puntuacion = 0
            actualizarPuntuacion()
            actualizarRecord()
        }
    }

    private fun mostrarMensjaPerdedor() {
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(false)
        builder.setTitle("Perdiste")
        builder.setMessage("Pulse OK para volver a jugar")
            .setPositiveButton("OK",
                DialogInterface.OnClickListener { _, _ ->
                    // START THE GAME!
                    patron = mutableListOf(1)
                    mirar()
                })
        // Create the AlertDialog object and return it
        builder.create().show()
    }

    private fun reproducirSonido(sonido: Int) {
        var mediaPlayer: MediaPlayer? = MediaPlayer.create(this, sonido)
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.stop()
            }
            mediaPlayer.start()
            mediaPlayer.setOnCompletionListener {
                MediaPlayer.OnCompletionListener { mediaPlayer -> mediaPlayer.release() }
            }
        }
    }

    private fun desactivarBotones() {
        val btnVerde: Button = findViewById(R.id.btnVerde)
        val btnRojo: Button = findViewById(R.id.btnRojo)
        val btnAmarillo: Button = findViewById(R.id.btnAmarillo)
        val btnAzul: Button = findViewById(R.id.btnAzul)

        btnVerde.isClickable = false
        btnRojo.isClickable = false
        btnAmarillo.isClickable = false
        btnAzul.isClickable = false
    }

    private fun activarBotones() {
        val btnVerde: Button = findViewById<Button?>(R.id.btnVerde)
        val btnRojo: Button = findViewById(R.id.btnRojo)
        val btnAmarillo: Button = findViewById(R.id.btnAmarillo)
        val btnAzul: Button = findViewById(R.id.btnAzul)

        btnVerde.isClickable = true
        btnRojo.isClickable = true
        btnAmarillo.isClickable = true
        btnAzul.isClickable = true
    }
}