package com.example.loginfirebase

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.example.loginfirebase.LoginActivity.Companion.db
import com.example.loginfirebase.LoginActivity.Companion.mGoogleSignInClient
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity() {
    companion object {
        lateinit var email: String
        lateinit var usuario: String
    }

    private val TAG = "HomeActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar?.hide()
        // Setup
        obtenerNombreUsuario(email)
    }

    fun onClickBtnInfo(view: View) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Info")
            .setCancelable(true)
            .setMessage("Correo: $email\nUsuario: $usuario\n\n\n\nCreado por Manuel Tirado García")
            .setPositiveButton("Ok", DialogInterface.OnClickListener { dialog, which -> })
        builder.create().show()
    }

    private fun obtenerNombreUsuario(email: String) {
        val user = FirebaseAuth.getInstance().currentUser
        if (user?.providerData?.get(user.providerData.size - 1)?.providerId != "google.com") {
            db.collection("users").document(email).get()
                .addOnSuccessListener { document ->
                    if (document != null) {
                        Log.d(TAG, "Usuario: ${document.get("nombreUsuario")}")
                        usuario = document.get("nombreUsuario").toString()
                    } else {
                        Log.d("HomeActivity", "No such document")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d(TAG, "get failed with ", exception)
                }
        } else {
            usuario = user.displayName.toString()
        }
    }

    fun onClickBtnLogOut(view: View) {
        FirebaseAuth.getInstance().signOut()
        mGoogleSignInClient?.signOut()
        onBackPressedDispatcher.onBackPressed()
    }

    fun onClickBtnPuntuaciones(view: View) {
        val intent = Intent(this, Puntuaciones::class.java)
        startActivity(intent)
    }

    fun onClickBtnJugar(view: View) {
        val intent = Intent(this, Juego::class.java)
        intent.putExtra("usuario", usuario)
        intent.putExtra("email", email)
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

    override fun onBackPressed() {
        return
    }
}