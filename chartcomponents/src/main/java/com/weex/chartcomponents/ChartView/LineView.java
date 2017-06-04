package com.weex.chartcomponents.ChartView;


import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

import zjhcsoft.com.wx_statistics.R;
import zjhcsoft.com.wx_statistics.Util.ColorGet;
import zjhcsoft.com.wx_statistics.Util.MyValueFormatter;
import zjhcsoft.com.wx_statistics.Util.StringUtil;
import zjhcsoft.com.wx_statistics.bean.Data_collection;


public class LineView extends LinearLayout {

    private LineChart mChart;
    private Context context;
    private int[] colors;

    public LineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }


    protected  LineView(Context context) {
        super(context);
        initView(context);
    }

    public void initView(Context context){
        this.context=context;
        LayoutInflater.from(context).inflate(R.layout.line_view,this,true);
        mChart = (LineChart) findViewById(R.id.Chart);
        mChart.setDescription("");
        mChart.setBackgroundColor(Color.WHITE);
        mChart.setDrawGridBackground(false);
//        mChart.setDrawBarShadow(false);

        // draw bars behind lines
//        mChart.setDrawOrder(new CombinedChart.DrawOrder[] {
//                /*CombinedChart.DrawOrder.BAR, */CombinedChart.DrawOrder.LINE
//        });

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setDrawLabels(false);
        rightAxis.setDrawGridLines(false);
//        rightAxis.setTextSize(16f);
        rightAxis.setDrawAxisLine(true);
//        rightAxis.enableGridDashedLine(10f, 10f, 0f);
//        rightAxis.setStartAtZero(false);

        YAxis leftAxis = mChart.getAxisLeft();
//        leftAxis.setDrawGridLines(false);
        leftAxis.setStartAtZero(false);
        leftAxis.setDrawGridLines(true);
        leftAxis.setTextSize(16f);
//        leftAxis.setValueFormatter(new MyValueFormatter(1));

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setTextSize(14f);
//        xAxis.setSpaceBetweenLabels(9);

        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);
        l.setForm(Legend.LegendForm.SQUARE);
//        l.setFormSize(9f);
//        l.setTextSize(11f);
//        l.setXEntrySpace(4f);
//        最大最小
//        leftAxis.setAxisMaxValue(220f);
//        leftAxis.setAxisMinValue(-50f);
        colors =new ColorGet().get25Colors();
    }

    public void setChart(Data_collection datac,int[] ints) {
//        LineData data = new LineData(datac.getXString());
//        data.setData(generateLineData(datac,ints));
        mChart.setData(generateLineData(datac,ints));
//        if(StringUtil.ContainNum(datac.getXString()[0])&&datac.getXString().length>10){
//            mChart.getXAxis().setSpaceBetweenLabels(9);
//        }
        mChart.invalidate();
        mChart.animateX(3000);
    }

    private LineData generateLineData(Data_collection datac,int[] ints ) {

        LineData d = new LineData();
        d.addXValue("");
        for (int a = 0; a < datac.getXString().length; a++) {
            d.addXValue(datac.getXString()[a]);
        }
        d.addXValue("");
        for (int i = 0; i < datac.getDataset().size(); i++) {

        ArrayList<Entry> entries = new ArrayList<Entry>();
        for (int index = 1; index < datac.getDataset().get(i).getDatas().size()+1; index++)
            try{
//                entries.add(new Entry(Float.parseFloat(datac.getDataset().get(i).getDatas().get(index-1).toString()), index));
                entries.add(new Entry(datac.getDataset().get(i).getDatas().get(index-1), index));
            }catch (Exception e){

            }

        LineDataSet set = new LineDataSet(entries, datac.getDataset().get(i).getSubtitle());
        set.setColor(colors[ints[i]]);
        set.setLineWidth(2.5f);
        set.setCircleColor(colors[ints[i]]);
        set.setCircleSize(5f);
        set.setFillColor(colors[ints[i]]);
        set.setDrawCubic(true);
        set.setCubicIntensity(0.1f);
        set.setDrawValues(true);
        set.setValueTextSize(14f);
        set.setValueFormatter(new MyValueFormatter(datac.getDecimal()));
        set.setValueTextColor(colors[ints[i]]);

//        set.setAxisDependency(YAxis.AxisDependency.LEFT);

        d.addDataSet(set);
        }
        return d;
    }

}
