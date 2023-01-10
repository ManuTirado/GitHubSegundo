package com.example.ej34v2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class MainActivity : AppCompatActivity(), comunicador {
    private val TAG = "MyActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onClickButtonRestar() {
        val valor = this.findViewById<TextView>(R.id.txtAzul)

    }

    override fun onClickButtonSumar() {
        val valor = this.findViewById<TextView>(R.id.txtAzul)

    }
}