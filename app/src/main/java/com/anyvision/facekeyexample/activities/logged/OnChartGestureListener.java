//package com.anyvision.facekeyexample.activities.logged;
//
//import android.view.MotionEvent;
//
//import com.github.mikephil.charting.listener.ChartTouchListener;
//
//public interface OnChartGestureListener {
//
//    //* Retornos de chamada quando um gesto de toque é iniciado no gráfico (ACTION_DOWN)
//    // * @param lastPerformedGesture
//    void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture);
//    //* Retornos de chamada quando um gesto de toque termina no gráfico (ACTION_UP, ACTION_CANCEL)
//
//    //* @param lastPerformedGesture
//    void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture);
//
//    // * Retornos de chamada quando o gráfico é compactado.
//    public void onChartLongPressed(MotionEvent me);
//
//    //* Callbacks quando o gráfico é tocado duas vezes.
//
//    public void onChartDoubleTapped(MotionEvent me);
//
//    //* Callbacks quando o gráfico é tocado uma vez.
//
//    public void onChartSingleTapped(MotionEvent me);
//
//    //* Callbacks, em seguida, um gesto de arremesso é feito no gráfico.
//
////             * @param velocityX
////     * @param velocityY
//
//    public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY);
//
//    //* Retornos de chamada quando o gráfico é dimensionado / ampliado por meio de gesto de pinça de zoom.
////             * @param scaleX scalefactor no eixo x
////     * @param scaleY scalefactor no eixo y
//
//    public void onChartScale(MotionEvent me, float scaleX, float scaleY);
//
//    //* Retornos de chamada quando o gráfico é movido / traduzido por meio do gesto de arrastar.
//
//    //* @param dX distância de translação no eixo x
//    //* @param dY distância de translação no eixo y
//
//    public void onChartTranslate(MotionEvent me, float dX, float dY);
//}
//
