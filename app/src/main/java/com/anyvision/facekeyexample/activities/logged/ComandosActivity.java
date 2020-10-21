package com.anyvision.facekeyexample.activities.logged;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.anyvision.facekeyexample.R;
import com.anyvision.facekeyexample.activities.LoginActivity;

public class ComandosActivity extends AppCompatActivity {

    private Button btnChamado;
    private Button btnGestaoGrafico;
    private Button btnComandosMainActivity;
    private Button btnRegional;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comandos);

        btnChamado = findViewById(R.id.btnTelaChamado);
        btnGestaoGrafico = findViewById(R.id.btnGestaoGraficos);
        btnComandosMainActivity = findViewById(R.id.btnTelaComandosMainActivity);
        btnRegional = findViewById(R.id.btnTelaSolicitationActivity);

        btnChamado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ChamadoActivity.startActivity(ComandosActivity.this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btnGestaoGrafico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ChamadoGraficoActivity.startActivity(ComandosActivity.this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btnComandosMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.startActivity(ComandosActivity.this);
            }
        });

        btnRegional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SolicitationExtensionActivity.startActivity(ComandosActivity.this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void startActivity(Context from) {
        Intent intent = new Intent(from, ComandosActivity.class);
        from.startActivity(intent);
    }

    public void onBackPressed() {
        LoginActivity.startActivity(ComandosActivity.this);
    }
}