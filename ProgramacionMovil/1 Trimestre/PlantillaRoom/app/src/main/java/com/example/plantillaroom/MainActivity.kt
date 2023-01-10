package com.example.plantillaroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.plantillaroom.Room.Puntuaciones
import com.example.plantillaroom.Room.PuntuacionesApp
import com.example.plantillaroom.Room.TaskAdapter
import com.example.plantillaroom.Room.TaskEntity
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnPuntuaciones = findViewById<Button>(R.id.btnIrAPuntuaciones)
        btnPuntuaciones.setOnClickListener {
            val intent = Intent(this, Puntuaciones::class.java)
            startActivity(intent)
        }
    }
}