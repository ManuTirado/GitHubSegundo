package com.example.ej34

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), comunicador {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onClickButtonRestar() {
        val valor = this.findViewById<TextView>(R.id.txtAzul)
        valor.setText((valor.text.toString().toInt()-1).toString())
    }

    override fun onClickButtonSumar() {
        val valor = this.findViewById<TextView>(R.id.txtAzul)
        valor.setText((valor.text.toString().toInt()+1).toString())
    }
}