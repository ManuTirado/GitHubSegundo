package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class TamanoTexto extends AppCompatActivity {
    EditText texto;
    ImageButton btnDisTamano, btnAumTamano;

    int tamanoTexto = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tamano_texto);

        texto = findViewById(R.id.txtTextoTamanoTexto);
        btnDisTamano = findViewById(R.id.btnDisminuirTamanoTexto);
        btnAumTamano = findViewById(R.id.btnAumentarTamanoTexto);

        if (tamanoTexto == 0){
            tamanoTexto = 20;
            texto.setTextSize(tamanoTexto);
        }


    }

    public void dismuniurTamanoTexto(View view) {
        if (tamanoTexto > 10){
            tamanoTexto = tamanoTexto - 1;
            texto.setTextSize(tamanoTexto);
        }
    }

    public void aumentarTamanoTexto(View view) {
        if (tamanoTexto < 30){
            tamanoTexto = tamanoTexto + 1;
            texto.setTextSize(tamanoTexto);
        }
    }
}