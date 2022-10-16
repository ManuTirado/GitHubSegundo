package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class FormatearTexto extends AppCompatActivity {

    TextView texto;
    CheckBox chkBoxNegrita, chkBoxGigantesco, chkBoxMinusculo, chkBoxRojo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formatear_texto);

        texto = findViewById(R.id.txtFormatearTexto);
        chkBoxNegrita = findViewById(R.id.chkBoxNegrita);
        chkBoxGigantesco = findViewById(R.id.chkBoxGigantesco);
        chkBoxMinusculo = findViewById(R.id.chkBoxMinusculo);
        chkBoxRojo = findViewById(R.id.chkBoxRojo);

        chkBoxNegrita.setOnClickListener(view -> {
            if (chkBoxNegrita.isChecked()) {
                texto.setTypeface(null, Typeface.BOLD);
            } else {
                texto.setTypeface(null, Typeface.NORMAL);
            }
        });

        chkBoxGigantesco.setOnClickListener(view -> {
            if (chkBoxGigantesco.isChecked() && !chkBoxMinusculo.isChecked()) {
                texto.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 40F);
            } else if (chkBoxGigantesco.isChecked() && chkBoxMinusculo.isChecked()){
                texto.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 40F);
                chkBoxMinusculo.setChecked(false);
            } else {
                texto.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14F);
            }
        });

        chkBoxMinusculo.setOnClickListener(view -> {
            if (chkBoxMinusculo.isChecked() && !chkBoxGigantesco.isChecked()) {
                texto.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 5F);
            } else if (chkBoxMinusculo.isChecked() && chkBoxGigantesco.isChecked()) {
                texto.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 5F);
                chkBoxGigantesco.setChecked(false);
            } else {
                texto.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 14F);
            }
        });

        chkBoxRojo.setOnClickListener(view -> {
            if (chkBoxRojo.isChecked()) {
                texto.setTextColor(getResources().getColor(R.color.red));
            } else {
                texto.setTextColor(getResources().getColor(R.color.black));
            }
        });
    }


}