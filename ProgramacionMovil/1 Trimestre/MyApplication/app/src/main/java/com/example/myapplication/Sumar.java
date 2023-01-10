package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Sumar extends AppCompatActivity implements View.OnClickListener {
    EditText txt1, txt2;
    Button btnSumar;
    TextView txtResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sumar);

        txt1 = findViewById(R.id.txtNumero1);
        txt2 = findViewById(R.id.txtNumero2);
        txtResultado = findViewById(R.id.txtResultado);
        btnSumar = findViewById(R.id.btnSumar);
        btnSumar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Float num1,num2;
        if (txt1.getText().toString().isEmpty()) {
            num1 = 0.f;
        } else {
            num1 = Float.valueOf(txt1.getText().toString());
        }
        if (txt2.getText().toString().isEmpty()) {
            num2 = 0.f;
        } else {
            num2 = Float.valueOf(txt2.getText().toString());
        }
        float res = num1 + num2;
        txtResultado.setText("La suma es " + res);
    }
}