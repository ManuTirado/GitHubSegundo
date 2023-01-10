package com.example.loginroom

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.loginroom.Room.LoginApp
import com.example.loginroom.Room.clsUsuario
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class Register : AppCompatActivity() {

    private lateinit var etNombre: EditText
    private lateinit var etApellidos: EditText
    private lateinit var etUsuario: EditText
    private lateinit var etContrasena: EditText
    private lateinit var etFoto: EditText
    private lateinit var btnRegistar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        etNombre = findViewById(R.id.etNombre_Register)
        etApellidos = findViewById(R.id.etApellidos_Register)
        etUsuario = findViewById(R.id.etUsuario_Register)
        etContrasena = findViewById(R.id.etContrasena_Register)
        etFoto = findViewById(R.id.etFoto_Register)
        btnRegistar = findViewById(R.id.btnRegistrar_Register)

        btnRegistar.setOnClickListener {
            if ((etUsuario.text.isEmpty() || etUsuario.text.isBlank()) && (etContrasena.text.isEmpty() || etContrasena.text.isBlank()) && (etUsuario.text.isEmpty() || etUsuario.text.isBlank()) && (etContrasena.text.isEmpty() || etContrasena.text.isBlank()) && (etFoto.text.isEmpty() || etFoto.text.isBlank())) {
                Toast.makeText(
                    this,
                    "No ha introducido ningún campo",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                addTask(
                    clsUsuario(
                        usuario = etUsuario.text.toString(),
                        contrasena = etContrasena.text.toString(),
                        nombre = etNombre.text.toString(),
                        apellidos = etApellidos.text.toString(),
                        foto = etFoto.text.toString()
                    )
                )
                finish()
            }
        }
    }

    fun addTask(task: clsUsuario) = runBlocking {  // Corrutina que añade una tarea a la lista
        launch {
            val id = LoginApp.database.taskDao().addTask(task)   // Inserta un nuevo usuario
            val recoveryTask = LoginApp.database.taskDao().getTaskById(id)   // Recarga la lista
        }
    }
}