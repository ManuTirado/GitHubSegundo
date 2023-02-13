package com.example.loginfirebase

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    enum class ProviderType {
        CORREO
    }

    private val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Analytics Event
        val analytics: FirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("msg", "Integración de Firebase completa")
        analytics.logEvent("InitScreen", bundle)

        // SetUp
        setUp()
    }

    private fun setUp() {
        title = "Autenticación"
        val signUpButton = findViewById<Button>(R.id.signUpButton)
        val logInButton = findViewById<Button>(R.id.logInButton)
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)

        signUpButton.setOnClickListener {
            if (emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()) {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                    emailEditText.text.toString(),
                    passwordEditText.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        pedirUsuario(
                            emailEditText.context,
                            it.result.user?.email ?: "",
                            ProviderType.CORREO
                        )
                    } else {
                        showAlert()
                    }
                }
            }
        }

        logInButton.setOnClickListener {
            if (emailEditText.text.isNotEmpty() && passwordEditText.text.isNotEmpty()) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    emailEditText.text.toString(),
                    passwordEditText.text.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        showHome(it.result.user?.email ?: "", ProviderType.CORREO)
                    } else {
                        showAlert()
                    }
                }
            }
        }
    }

    private fun pedirUsuario(cont: Context, email: String, provider: ProviderType) {
        var usu:String
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Nombre de usuario")
        builder.setMessage("Ingrese el nombre de usuario para completar el registro")
        val input = EditText(cont)
        builder.setView(input)
        builder.setPositiveButton("Aceptar", DialogInterface.OnClickListener { dialog, which ->
            usu = input.text.toString()
            db.collection("users").document(email).set(
                hashMapOf(
                    "provider" to provider,
                    "nombreUsuario" to usu
                )
            )
            Log.d("LoginActivity", "Usuario: " + usu)
            Log.d("LoginActivity", "email: " + email)
            showHome(email, provider)
        })
        builder.setCancelable(false)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome(email: String, provider: ProviderType) {
        val homeIntent = Intent(this, HomeActivity::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        startActivity(homeIntent)
    }
}