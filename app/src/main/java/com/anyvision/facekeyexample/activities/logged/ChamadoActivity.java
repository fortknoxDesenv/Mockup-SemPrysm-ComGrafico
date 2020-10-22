package com.anyvision.facekeyexample.activities.logged;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.anyvision.facekeyexample.R;
import com.anyvision.facekeyexample.utils.Enum;

import java.util.ArrayList;

public class ChamadoActivity extends AppCompatActivity {

    private Button btnCFTV;
    private Button btbAlarmeChamado;
    private Button btnSistemaIncendio;
    private Button btnIluminacao;
    private Button btnArCondicionado;
    private Button btnGestao;
    private String typeAccount;
    private int qtdClicksCFTV;
    private int qtdClicksAlarme;
    private int qtdClicksIncendio;
    private int qtdClicksHVAC;
    private int qtdClicksArCondicionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chamado);

        SharedPreferences preferences = getSharedPreferences(Enum.SharedPrivate.GRAFICO_CHAMADO.toString(), MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear().commit();

        btnCFTV = findViewById(R.id.btnCFTV);
        btbAlarmeChamado = findViewById(R.id.btbAlarmeChamado);
        btnSistemaIncendio = findViewById(R.id.btnSistemaIncendio);
        btnIluminacao = findViewById(R.id.btnHVAC);
        btnArCondicionado = findViewById(R.id.btnArCondicionado);
        btnGestao = findViewById(R.id.btnGestao);

        btnCFTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    qtdClicksCFTV++;
                    Toast.makeText(ChamadoActivity.this, Enum.Chamado.CFTV.toString() +" "+qtdClicksCFTV, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btbAlarmeChamado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    qtdClicksAlarme++;
                    Toast.makeText(ChamadoActivity.this, Enum.Chamado.ALARME.toString()+ " "+qtdClicksAlarme, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btnSistemaIncendio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    qtdClicksIncendio++;
                    Toast.makeText(ChamadoActivity.this, "SISTEMA INCÊNDIO "+ qtdClicksIncendio, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btnIluminacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                 qtdClicksHVAC ++;
                 Toast.makeText(ChamadoActivity.this, Enum.Chamado.HVAC.toString()+" "+ qtdClicksHVAC, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btnArCondicionado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                 qtdClicksArCondicionado ++;
                 Toast.makeText(ChamadoActivity.this, Enum.Chamado.ARCONDICIONADO.toString()+" "+ qtdClicksArCondicionado, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

//        btnGestao.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//                    qtdClicksHVAC ++;
//                    Toast.makeText(ChamadoActivity.this, String.valueOf(qtdClicksHVAC), Toast.LENGTH_SHORT).show();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
    }

    public static void startActivity(Context from) {
        Intent intent = new Intent(from, ChamadoActivity.class);
        from.startActivity(intent);
    }

    public void onBackPressed() {
        SalvarListaGrafico();
        //auth.requestToken(Enum.request.aprovaReprovaExtesao.toString(), Enum.LogarSemSesame.GRAFICO_GESTAO.toString());
        ComandosActivity.startActivity(ChamadoActivity.this);
    }

    public void SalvarListaGrafico() {

        int valorTotal = 0;
        ArrayList<String> lista = new ArrayList<String>();

        if (qtdClicksCFTV > 0) {
            lista.add(Enum.Chamado.CFTV.toString() + ";" + qtdClicksCFTV);
            valorTotal += qtdClicksCFTV;
        }

        if (qtdClicksAlarme > 0) {
            lista.add(Enum.Chamado.ALARME.toString() + ";" + qtdClicksAlarme);
            valorTotal += qtdClicksAlarme;
        }

        if (qtdClicksIncendio > 0) {
            lista.add(Enum.Chamado.INCENDIO.toString() + ";" + qtdClicksIncendio);
            valorTotal += qtdClicksIncendio;
        }

        if (qtdClicksHVAC > 0) {
            lista.add(Enum.Chamado.HVAC.toString() + ";" + qtdClicksHVAC);
            valorTotal += qtdClicksHVAC;
        }

        if (qtdClicksArCondicionado > 0) {
            lista.add(Enum.Chamado.ARCONDICIONADO.toString() + ";" + qtdClicksArCondicionado);
            valorTotal += qtdClicksArCondicionado;
        }

        SharedPreferences preferences = getSharedPreferences(Enum.SharedPrivate.GRAFICO_CHAMADO.toString(), MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        if (lista.size() > 0) {
            editor.putInt(Enum.SharedPrivate.CHAMADO_GESTAO_VALOR_TOTAL.toString(), valorTotal);
            editor.putInt(Enum.SharedPrivate.CHAMADO_GESTAO_CONTROLE_SALA_SIZE.toString(), lista.size());

            for (int i = 0; i < lista.size(); i++) {
                editor.putString(Enum.SharedPrivate.GRAFICO_CHAMADO.toString() + "_" + i, lista.get(i));
            }
            editor.apply();
        }
    }
}
