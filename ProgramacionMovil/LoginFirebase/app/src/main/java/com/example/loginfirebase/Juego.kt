package com.example.loginfirebase

import android.content.DialogInterface
import android.media.MediaPlayer
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.random.Random

class Juego : AppCompatActivity() {

    private var patron: MutableList<Int> = mutableListOf(1) // Lista con el patrón a seguir
    private var contPulsaciones: Int = 0

    private var pausa: Long = 500
    private var dificultad: Int = 0

    private var puntuacion: Int = 0
    private var record: Int = 0
    private var numPulsacionesTotales: Int = 0

    private val db = Firebase.firestore
    private lateinit var usuario:String
    private lateinit var email:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_juego)
        supportActionBar?.hide()

        // Recojo el bundle con el long pasado desde el HomeActivity
        val bundle = intent.extras
        dificultad = bundle?.getInt("dificultad") ?: 0
        usuario = bundle?.getString("usuario").toString()
        email = bundle?.getString("email").toString()
        when (dificultad) {
            0 -> {
                pausa = 600
            }
            1 -> {
                pausa = 400
            }
            2 -> {
                pausa = 200
            }
            3 -> {
                pausa = 100
            }
            else -> pausa = 300
        }

        // Creo un listener para cada botón, este llamará a comprobar pulsacion pasándole un número por parámetro
        // Este número referencia al botón
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

    /***
     * Desactiva los botones, añade un número aleatorio al patrón y lo muestra.
     * Al acabar vuelve a activar los botones para que el jugador pueda jugar
     */
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

    /***
     * Cambia el color y reproduce un sonido de un botón.
     * Espera un tiempo y vuelve a cambiarle el color por el original
     */
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

    /***
     * Cambia el texto que muestra la puntuación por el valor actual de puntuación
     */
    private fun actualizarPuntuacion() {
        val txtPuntuacion: TextView = findViewById(R.id.txtPuntuacion)
        txtPuntuacion.text = puntuacion.toString()
    }

    /***
     * Cambia el texto que muestra el record por el valor actual del record
     */
    private fun actualizarRecord() {
        val txtRecord: TextView = findViewById(R.id.txtRecord)
        txtRecord.text = record.toString()
    }

    /***
     * Comprueba que el botón pulsado por el jugador sea el que corresponde en el patrón.
     * En casa correcto reproduce un sonido de acierto y continúa.
     * En caso negativo reproduce un sonido de error y muestra un mensaje que al aceptarlo comienza una nueva partida
     */
    private fun comprobarPulsacion(seleccion: Int) {
        if (seleccion == patron[contPulsaciones]) {
            reproducirSonido(R.raw.correcto)

            if (contPulsaciones == patron.size - 1) {
                puntuacion++
                numPulsacionesTotales++
                actualizarPuntuacion()
                mirar()
            } else {
                contPulsaciones++
            }
        } else {
            reproducirSonido(R.raw.incorrecto)

            if (puntuacion > record) {
                registrarPuntuacion()
                record = puntuacion
            } else {
                mostrarMensjaPerdedor()
                numPulsacionesTotales = 0
                puntuacion = 0
                actualizarPuntuacion()
                actualizarRecord()
            }
        }
    }

    private fun registrarPuntuacion() {
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(false)
        builder.setTitle("Guardando...")
        builder.setMessage("¿Quedará tu nombre inmortalizado?")
            .setPositiveButton("OK",
                DialogInterface.OnClickListener { _, _ ->
                    db.collection("puntuaciones").document(email).set(
                        hashMapOf(
                            "nombre" to usuario,
                            "dificultad" to dificultad,
                            "puntuacion" to puntuacion,
                            "numeroPulsaciones" to numPulsacionesTotales,
                            //"fechaHora" to LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/uu kk:mm"))
                            "fechaHora" to LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM"))
                        )
                    )
                    mostrarMensjaPerdedor()
                    numPulsacionesTotales = 0
                    puntuacion = 0
                    actualizarPuntuacion()
                    actualizarRecord()
                })
        // Create the AlertDialog object and return it
        builder.create().show()
    }

    private fun mostrarMensjaPerdedor() {
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(false)
        builder.setTitle("Perdiste")
        builder.setMessage("¿Otra partidita? 🔥")
            .setPositiveButton("Si",
                DialogInterface.OnClickListener { _, _ ->
                    // START THE GAME!
                    patron = mutableListOf(1)
                    mirar()
                })
            .setNegativeButton("No",
                DialogInterface.OnClickListener { _, _ ->
                    // GO BACK TO MENU
                    patron = mutableListOf(1)
                    finish()
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