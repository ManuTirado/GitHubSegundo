package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ColorTexto extends AppCompatActivity {
    EditText txtTexto;
    Button btnAzul, btnRojo, btnVerde;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_texto);

        txtTexto = findViewById(R.id.txtTextoColor);
        btnAzul = findViewById(R.id.btnAzul);
        btnRojo = findViewById(R.id.btnRojo);
        btnVerde = findViewById(R.id.btnVerde);

        btnAzul.setOnClickListener(this::onClickAzul);
        btnRojo.setOnClickListener(this::onClickRojo);
        btnVerde.setOnClickListener(this::onClickVerde);
    }

    public void onClickAzul(View view) {
        txtTexto.setTextColor(getResources().getColor(R.color.blue));
        txtTexto.setHintTextColor(getResources().getColor(R.color.blue));
    }
    public void onClickRojo(View view) {
        txtTexto.setTextColor(getResources().getColor(R.color.red));
        txtTexto.setHintTextColor(getResources().getColor(R.color.red));
    }
    public void onClickVerde(View view) {
        txtTexto.setTextColor(getResources().getColor(R.color.green));
        txtTexto.setHintTextColor(getResources().getColor(R.color.green));
    }

}