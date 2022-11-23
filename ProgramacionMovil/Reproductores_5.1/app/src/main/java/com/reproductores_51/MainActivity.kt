package com.reproductores_51

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {

//    var listView: ListView = ListView(this)
//    var listaMultimedia: ArrayList<String> = ArrayList()
//    lateinit var listaAdapter: ArrayAdapter<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnVerVideo:Button = findViewById(R.id.btnVerVideo)
        btnVerVideo.setOnClickListener {
            val reproductor = Intent(this, Reproductor::class.java)
            startActivity(reproductor)
        }

//
//        listView = findViewById(R.id.lvElementosReproducibles)
//        listView.setOnItemClickListener()
//
//        listaAdapter = ArrayAdapter(this, R.layout.activity_main, listaMultimedia)
//
//
//        listaMultimedia.add("https://www.youtube.com/watch?v=pd8XB1PIpfk&ab_channel=DannyValdiviezoCeli")
//        listaMultimedia.add("https://www.youtube.com/watch?v=5aAmhF6fRuI&ab_channel=Zazzaelitaliano")
//        listaMultimedia.add("https://www.youtube.com/watch?v=VTA7cgyQwMQ&ab_channel=IlloJuan")
//
//        listView.adapter = listaAdapter
    }
    

}




