package com.example.loginroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.loginroom.Room.LoginApp
import com.example.loginroom.Room.clsUsuario
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class Login : AppCompatActivity() {

    private lateinit var etUsuario: EditText
    private lateinit var etContrasena: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnRegistar: Button
    private lateinit var usuarios: MutableCollection<clsUsuario>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //addTask(clsUsuario(usuario = "mtirado", contrasena = "1234", nombre = "Manuel", apellidos = "Tirado García", foto = "https://raw.githubusercontent.com/ManuelTirado/Fotos/main/roberto.jpg"))

        etUsuario = findViewById(R.id.etUsuario_Login)
        etContrasena = findViewById(R.id.etContrasena_Login)
        btnLogin = findViewById(R.id.btnLogin_Login)
        btnRegistar = findViewById(R.id.btnRegistrar_Login)

        btnLogin.setOnClickListener {
            if ((etUsuario.text.isEmpty() || etUsuario.text.isBlank()) && (etContrasena.text.isEmpty() || etContrasena.text.isBlank())) {
                Toast.makeText(
                    this,
                    "No ha introducido el usuario ni la contraseña",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (etUsuario.text.isEmpty() || etUsuario.text.isBlank()) {
                Toast.makeText(this, "No ha introducido el usuario", Toast.LENGTH_SHORT).show()
            } else if (etContrasena.text.isEmpty() || etContrasena.text.isBlank()) {
                Toast.makeText(this, "No ha introducido la contraseña", Toast.LENGTH_SHORT).show()
            } else {
                //Comprobar que existe
                var encontrado:Boolean = false
                var i:Int = 0
                while (!encontrado && i < usuarios.size) {
                    if (usuarios.elementAt(i).usuario.equals(etUsuario.text.toString())) {
                        // Comprobar contraseña
                        if (usuarios.elementAt(i).contrasena.equals(etContrasena.text.toString())) {
                            // Ir a detalles de la persona
                            val intent = Intent(this, DetallesUsuario::class.java)
                            intent.putExtra("usuario", Usuario(usuarios.elementAt(i)))
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show()
                        }
                        encontrado = true
                    }
                    i++
                }
                if (!encontrado) {
                    Toast.makeText(this, "El usuario no existe en la BBDD", Toast.LENGTH_SHORT).show()
                }
            }
        }

        btnRegistar.setOnClickListener {
            // Ir a la página de registrarse
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        getAllUsers()
    }

    fun getAllUsers() = runBlocking {
        usuarios = LoginApp.database.taskDao().getAllTasks()
    }
}