package com.example.loginfirebase

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

enum class ProviderType {
    BASIC
}

class HomeActivity : AppCompatActivity() {

    private lateinit var email: String;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar?.hide()
        // Setup
        val bundle = intent.extras
        email = bundle?.getString("email").toString()
        val provider = bundle?.getString("provider")
    }

    fun onClickBtnInfo(view: View) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Info")
            .setCancelable(true)
            .setMessage("Correo: $email\n\n\n\nCreado por Manuel Tirado García")
            .setPositiveButton("Ok", DialogInterface.OnClickListener { dialog, which ->
                // Write a message to the database
                val database = Firebase.database("https://pruebasmanufirebase-default-rtdb.europe-west1.firebasedatabase.app/")

                val ddbbPunt = database. getReference("puntuaciones")
                ddbbPunt.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        val value = dataSnapshot.getValue<String>()
                        Log.d("", "Value is: $value")
                    }

                    override fun onCancelled(error: DatabaseError) {
                        // Failed to read value
                        Log.w("", "Failed to read value.", error.toException())
                    }
                })

                ddbbPunt.setValue("Prueba2")
                ddbbPunt.child("Prueba2").child("dificultad").setValue(2)
                ddbbPunt.child("Prueba2").child("fechaHora").setValue("14/02")
                ddbbPunt.child("Prueba2").child("numeroPulsaciones").setValue(3)
                ddbbPunt.child("Prueba2").child("puntuacion").setValue(4)
            })
        builder.create().show()
    }

    fun onClickBtnLogOut(view: View) {
        FirebaseAuth.getInstance().signOut()
        onBackPressed()
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
                        }
                        1 -> {
                            intent.putExtra("dificultad", 1)
                        }
                        2 -> {
                            intent.putExtra("dificultad", 2)
                        }
                        3 -> {
                            intent.putExtra("dificultad", 3)
                        }
                    }
                    startActivity(intent)
                })
        builder.create().show()
    }
}