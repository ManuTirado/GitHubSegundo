package com.example.loginroom

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import android.widget.TextView
import java.util.concurrent.Executors

class DetallesUsuario : AppCompatActivity() {

    private lateinit var tvId: TextView
    private lateinit var tvNombre: TextView
    private lateinit var tvApellidos: TextView
    private lateinit var tvUsuario: TextView
    private lateinit var tvContrasena: TextView
    private lateinit var ivFoto: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalles_usuario)

        val user: Usuario = intent.getSerializableExtra("usuario") as Usuario

        tvId = findViewById(R.id.tvId)
        tvNombre = findViewById(R.id.tvNombre)
        tvApellidos = findViewById(R.id.tvApellidos)
        tvUsuario = findViewById(R.id.tvUsuario)
        tvContrasena = findViewById(R.id.tvContrasena)
        ivFoto = findViewById(R.id.ivFoto)

        // Declaring executor to parse the URL
        val executor = Executors.newSingleThreadExecutor()
        // Once the executor parses the URL
        // and receives the image, handler will load it
        // in the ImageView
        val handler = Handler(Looper.getMainLooper())
        // Initializing the image
        var image: Bitmap? = null

        // Only for Background process (can take time depending on the Internet speed)
        executor.execute {
            // Image URL
            val imageURL = user.foto

            // Tries to get the image and post it in the ImageView
            // with the help of Handler
            try {
                val `in` = java.net.URL(imageURL).openStream()
                image = BitmapFactory.decodeStream(`in`)
                // Only for making changes in UI
                handler.post {
                    ivFoto.setImageBitmap(image)
                }
            }
            // If the URL doesnot point to
            // image or any other kind of failure
            catch (e: Exception) {
                e.printStackTrace()
            }
        }

        tvId.text = (user.id.toString())
        tvNombre.text = (user.nombre)
        tvApellidos.text = user.apellidos
        tvUsuario.text = user.usuario
        tvContrasena.text = user.contrasena
    }
}