package com.anyvision.facekeyexample.activities.logged;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.anyvision.facekeyexample.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ChamadoGraficoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chamado_grafico);

        //pega os valores do grafico
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int sharedValorTotalPorcentagem = sharedPreferences.getInt("chamado_gestao_valor_total" , MODE_PRIVATE);
        int size = sharedPreferences.getInt("chamado_gestao_controle_sala_size", MODE_PRIVATE);
        final ArrayList<String> listaGestaoControle = new ArrayList<String>(size);

        for (int i = 0; i < size; i++) {
            listaGestaoControle.add(sharedPreferences.getString("chamado_gestao_controle_sala" + "_" + i, null));
        }

        PieChart pieChart = findViewById(R.id.pie_chart);
        pieChart.setUsePercentValues(true);

        Description desc = new Description();
        desc.setText("Total de ocupação");
//        desc.setTextSize(25f);

        desc.setTextSize(50f);

        pieChart.setDescription(desc);

        pieChart.setHoleRadius(25f);
        pieChart.setTransparentCircleRadius(25f);

        List<PieEntry> value = new ArrayList<>();

        //teste
        for (String li : listaGestaoControle ) {

            String[] valorGestao = li.split(";");

            String nome = valorGestao[0].toUpperCase();
            double valor = Double.parseDouble(valorGestao[1]);

            double valorPorcentagem = ((double) valor / sharedValorTotalPorcentagem) * 100;
           float converterValorFinal = (float)valorPorcentagem;

            value.add(new PieEntry(converterValorFinal, nome));
        }

//        value.add(new PieEntry(40f, "Jan"));
//        value.add(new PieEntry(60f, "Fev"));

        PieDataSet pieDataSet = new PieDataSet(value, "Ocupação");
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
       // pieDataSet.setColors(ColorTemplate.JOYFUL_COLORS);
        //pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        //feio
        pieDataSet.setColors(ColorTemplate.PASTEL_COLORS); //roxo verde azul
//        pieDataSet.setColors(ColorTemplate.LIBERTY_COLORS);
        //pieDataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);// amarelo feio nem tirei print



        pieChart.animateXY(1400, 1400);
    }

    public static void startActivity(Context from) {
        Intent intent = new Intent(from, ChamadoGraficoActivity.class);
        from.startActivity(intent);
    }
}