package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class AlinearTexto extends AppCompatActivity {

    TextView txtTexto;
    ImageButton btnAlinearIzq, getBtnAlinearDcha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alinear_texto);

        txtTexto = findViewById(R.id.txtTextoAlinear);
        btnAlinearIzq = findViewById(R.id.btnAlinearIzq);
        getBtnAlinearDcha = findViewById(R.id.btnAlinearDcha);
    }

    public void AlinearTextoIzq (View view) {
        txtTexto.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
    }

    public void AlinearTextoDcha (View view) {
        txtTexto.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
    }
}