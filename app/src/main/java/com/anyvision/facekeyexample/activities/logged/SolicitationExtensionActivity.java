package com.anyvision.facekeyexample.activities.logged;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anyvision.facekeyexample.R;
import com.anyvision.facekeyexample.activities.LoginActivity;
import com.anyvision.facekeyexample.models.GetVariables;
import com.anyvision.facekeyexample.models.MessageTopic;
import com.anyvision.facekeyexample.prysm.Authentication;
import com.anyvision.facekeyexample.utils.Enum;
import com.anyvision.facekeyexample.utils.NewAdapter;

import java.util.ArrayList;

public class SolicitationExtensionActivity extends AppCompatActivity {

    private Authentication auth;
    private static String name;
    private static String newValue;
    private ArrayList<String> listaDescriptions;
    private static ArrayList<String> liHistApproved;
    private static ArrayList<String> liHistReproved;
    private RecyclerView.Adapter adapter;
    private static boolean enableProgressBarVisible = true;
    private Paint p = new Paint();
    private int LEFT = 4;
    private int RIGHT = 8;
    private int aprovado = 1;
    private int reprovado = 2;
    private Button btnAprovado;
    private Button btnReprovado;
    private TextView serverLocalUrl;
    private TextView txtSolicitation;
    private static boolean key = true;
    private static Thread solicitationThread = null;
    private static ProgressBar progressBar;
    private static boolean AllowGetlistSolicitation;
    private static boolean refresh;
    private static Activity finishSolicitationExtension;
    private static boolean active = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solicitation_extension);
        btnAprovado = findViewById(R.id.btnAprovado);
        btnReprovado = findViewById(R.id.btnReprovado);
        key = false;
        progressBar = findViewById(R.id.progressBarSolicit);
        progressBar.setVisibility(View.GONE);
        txtSolicitation = findViewById(R.id.txtSolicitation);
        txtSolicitation.setVisibility(View.GONE);

        btnAprovado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SolicitationHistoryApproved.startActivity(SolicitationExtensionActivity.this);
            }
        });

        btnReprovado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SolicitationHistoryReproved.startActivity(SolicitationExtensionActivity.this);
            }
        });

        final RecyclerView recyclerView = findViewById(R.id.recyclerViewMain);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String statusSolicitacao = preferences.getString("StatusSolicitacao", null);
        listaDescriptions = new ArrayList<String>();

        if (statusSolicitacao != null && statusSolicitacao.equals(Enum.StatusSolicitacao.AGUARDANDO.toString())) {
            listaDescriptions.add("Solicitação");
            key = false;
        } else {
            progressBar.setVisibility(View.VISIBLE);
            key = true;
        }

        adapter = new NewAdapter(listaDescriptions, SolicitationExtensionActivity.this);

        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        solicitationThread = new Thread() {
            @Override
            public void run() {
                try {
                    while (key) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (listaDescriptions == null || listaDescriptions.size() == 0) {
                                    try {
                                        progressBar.setVisibility(View.GONE);
                                        txtSolicitation.setVisibility(View.VISIBLE);
                                        key = false;
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        solicitationThread.start();

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

                try {
                    if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {

                        Bitmap icon;
                        View itemView = viewHolder.itemView;
                        float height = (float) itemView.getBottom() - (float) itemView.getTop();
                        float width = height / 3;

                        if (dX > 0) {
                            p.setColor(Color.parseColor("#388E3C"));
                            RectF background = new RectF((float) itemView.getLeft(), (float) itemView.getTop() + 2, dX, (float) itemView.getBottom() - 2);
                            c.drawRect(background, p);
                            icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_ok_new);
                            RectF icon_dest = new RectF((float) itemView.getLeft() + width + 40, (float) itemView.getTop() + width, (float) itemView.getLeft() + 2 * width + 40, (float) itemView.getBottom() - width);
                            c.drawBitmap(icon, null, icon_dest, p);

                        } else {
                            p.setColor(Color.parseColor("#D32F2F"));
                            RectF background = new RectF((float) itemView.getRight() + dX, (float) itemView.getTop() + 2, (float) itemView.getRight(), (float) itemView.getBottom() - 2);
                            c.drawRect(background, p);
                            icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_no_g);
                            RectF icon_dest = new RectF((float) itemView.getRight() - 2 * width - 40, (float) itemView.getTop() + width + 5, (float) itemView.getRight() - width - 40, (float) itemView.getBottom() - width);
                            c.drawBitmap(icon, null, icon_dest, p);
                        }
                    }
                    super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                try {
                        int pos = viewHolder.getAdapterPosition();
                        String nmAgenciaSelecionada = String.valueOf(listaDescriptions.get(pos));

                        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.clear().commit();

                        if (direction == LEFT) {
                            String title = "Solicitação Reprovada";
                            String message = "A extensão não foi aceita";
                            String topic = "AGENCIA";

                            new MessageTopic(topic, title, message);
                        }

                        if (direction == RIGHT) {
                            String title = "Solicitação Aprovada";
                            String message = "A extensão foi aceita";
                            String topic = "AGENCIA";

                            new MessageTopic(topic, title, message);
                        }

                        //if left then reprovado else aprovado
                        if (direction == LEFT) {
                            editor.putString("StatusSolicitacao", Enum.StatusSolicitacao.REPROVADO.toString());
                        } else {
                            editor.putString("StatusSolicitacao", Enum.StatusSolicitacao.APROVADO.toString());
                        }
                        editor.apply();
                        listaDescriptions.remove(pos);
                        adapter.notifyItemRemoved(pos);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        new ItemTouchHelper(simpleCallback).attachToRecyclerView(recyclerView);
    }

    public void onBackPressed() {
        ComandosActivity.startActivity(SolicitationExtensionActivity.this);
        GetVariables.getInstance().setSpTypeAccount("REGIONAL");
    }

    public void onResume() {
        super.onResume();
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
        Intent intent = new Intent(from, SolicitationExtensionActivity.class);
        from.startActivity(intent);
    }
}
