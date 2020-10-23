package com.anyvision.facekeyexample.activities.logged;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anyvision.facekeyexample.R;
import com.anyvision.facekeyexample.prysm.Authentication;
import com.anyvision.facekeyexample.utils.Enum;
import com.anyvision.facekeyexample.utils.SolicitHistReprovedAdapter;

import java.util.ArrayList;

public class SolicitationHistoryReproved extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private ImageButton btnVoltar;
    private ImageButton btnHistBackSolicit_Reproved;
    private Authentication auth;
    private ArrayList<String> listSolicitHistory;
    private ProgressBar progressBar;
    private static Thread solicitationHistThread = null;
    private static boolean key = false;
    private TextView txtMenuAprovReprov;
    private TextView txtSolicitation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitation_history);
        btnVoltar = findViewById(R.id.btnHistBackSolicit_Reproved);
        progressBar = findViewById(R.id.progressBarHistory);
        progressBar.setVisibility(View.GONE);
        txtMenuAprovReprov = findViewById(R.id.txtMenuAprovReprov);
        txtMenuAprovReprov.setText(getString(R.string.REPROVADO));
        btnHistBackSolicit_Reproved = findViewById(R.id.btnHistBackSolicit);
        btnHistBackSolicit_Reproved.setVisibility(View.GONE);
        txtSolicitation = findViewById(R.id.txtSolicitation);
        txtSolicitation.setVisibility(View.GONE);

        final RecyclerView recyclerView = findViewById(R.id.recyclerViewHistorySolicitation);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String statusSolicitacao = preferences.getString("StatusSolicitacao", null);

        listSolicitHistory = new ArrayList<String>();

        if (statusSolicitacao != null && statusSolicitacao.equals(Enum.StatusSolicitacao.REPROVADO.toString())){
            listSolicitHistory.add("Solicitação");
            key = false;
        }

        if ((listSolicitHistory == null) || listSolicitHistory.size() == 0) {
            progressBar.setVisibility(View.VISIBLE);
            key = true;
        }

        adapter = new SolicitHistReprovedAdapter(listSolicitHistory, SolicitationHistoryReproved.this);
        recyclerView.setAdapter(adapter);

        solicitationHistThread = new Thread() {
            @Override
            public void run() {
                try {
                    while (key) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    if (listSolicitHistory == null || listSolicitHistory.size() == 0) {
                                        progressBar.setVisibility(View.GONE);
                                        txtSolicitation.setVisibility(View.VISIBLE);
                                        key = false;
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        solicitationHistThread.start();
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SolicitationExtensionActivity.startActivity(SolicitationHistoryReproved.this);
            }
        });
    }

    public void onPause() {
        super.onPause();
    }

    public void onStop() {
        super.onStop();
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public static void startActivity(Context from) {
        Intent intent = new Intent(from, SolicitationHistoryReproved.class);
        from.startActivity(intent);
    }
}
