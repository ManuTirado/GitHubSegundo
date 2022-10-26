package com.example.simonssays

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class Juego : AppCompatActivity(), Comunicador {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_juego)
    }

    override fun onClickVerde() {
        Toast.makeText(this,"Verde", Toast.LENGTH_SHORT).show()
    }

    override fun onClickRojo() {
        Toast.makeText(this,"Rojo", Toast.LENGTH_SHORT).show()
    }

    override fun onClickAmarillo() {
        Toast.makeText(this,"Amarillo", Toast.LENGTH_SHORT).show()
    }

    override fun onClickAzul() {
        Toast.makeText(this,"Azul", Toast.LENGTH_SHORT).show()
    }
}