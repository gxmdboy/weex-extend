package com.weex.chartcomponents.ChartView;


import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;

import zjhcsoft.com.wx_statistics.R;
import zjhcsoft.com.wx_statistics.Util.ColorGet;
import zjhcsoft.com.wx_statistics.Util.FloatFormat;
import zjhcsoft.com.wx_statistics.bean.Data_collection;
import zjhcsoft.com.wx_statistics.bean.Dataset;


public class CombinedChartView extends LinearLayout {

    private CombinedChart mChart;
    private Context context;
    private int[] colors;

    public CombinedChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }


    protected CombinedChartView(Context context) {
        super(context);
        initView(context);
    }

    public void initView(Context context){
        this.context=context;
        LayoutInflater.from(context).inflate(R.layout.common_chart_view,this,true);
        mChart = (CombinedChart) findViewById(R.id.Chart);
        mChart.setDescription("");
        mChart.setBackgroundColor(Color.WHITE);
        mChart.setDrawGridBackground(false);
//        mChart.setDrawBarShadow(false);

        // draw bars behind lines
        mChart.setDrawOrder(new CombinedChart.DrawOrder[] {
                CombinedChart.DrawOrder.BAR, CombinedChart.DrawOrder.LINE
        });

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setDrawLabels(false);
        rightAxis.setDrawGridLines(false);
        rightAxis.setTextSize(16f);
        rightAxis.setDrawAxisLine(true);
//        rightAxis.enableGridDashedLine(10f, 10f, 0f);
//        rightAxis.setStartAtZero(false);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setStartAtZero(false);
        leftAxis.setDrawGridLines(true);
        leftAxis.setTextSize(16f);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setTextSize(18f);
//        最大最小
//        leftAxis.setAxisMaxValue(220f);
//        leftAxis.setAxisMinValue(-50f);
        colors =new ColorGet().get25Colors();
    }

    public void setChart(Data_collection datac,int[] ints) {
//        LineData data = new LineData(datac.getXString());
//        data.setData(generateLineData(datac,ints));
        mChart.setData(generateData(datac,ints));
        mChart.invalidate();
        mChart.animateX(1000);
    }

    private CombinedData generateData(Data_collection datac,int[] ints) {

        CombinedData cd = new CombinedData();
//        LineData d = new LineData();
        LineData ld = new LineData();
        BarData bd = new BarData();

        for (int a = 0; a < datac.getXString().length; a++) {
            cd.addXValue(datac.getXString()[a]);
        }

        for (int i = 0; i < datac.getDataset().size(); i++) {

            Dataset dataset = datac.getDataset().get(i);

//        ArrayList<Entry> entries = new ArrayList<Entry>();
//
//        for (int index = 0; index < datac.getDataset().get(i).getDatas().size(); index++)
//            try{
//                entries.add(new Entry(Float.parseFloat(datac.getDataset().get(i).getDatas().get(index).toString()), index));
//            }catch (Exception e){
//
//            }

        if (dataset.getIsRate()){
            ld.addDataSet(getLineDataSet(dataset, colors[ints[i]]));
        }else{
            bd.addDataSet(getBarDataSet(dataset, colors[ints[i]]));
        }

//        d.addDataSet(set);
        }
//        Log.i("ld",ld.getDataSetCount()+"");
//        Log.i("bd",bd.getDataSetCount()+"");
        if (ld.getDataSetCount()!=0){cd.setData(ld);}
        if (bd.getDataSetCount()!=0){cd.setData(bd);}
        return cd;
    }

    private LineDataSet getLineDataSet(Dataset dataset, int color){

//        LineData d = new LineData();

        ArrayList<Entry> entries = new ArrayList<Entry>();

        for (int index = 0; index < dataset.getDatas().size(); index++) {
            try {
//                entries.add(new Entry(Float.parseFloat(dataset.getDatas().get(index).toString()), index));
                entries.add(new Entry(FloatFormat.format(dataset.getDatas().get(index)),index));
            } catch (Exception e) {

            }
        }
        LineDataSet set = new LineDataSet(entries, dataset.getSubtitle());
        set.setColor(color);
        set.setLineWidth(2.5f);
        set.setCircleColor(color);
        set.setCircleSize(5f);
        set.setFillColor(color);
        set.setDrawCubic(true);
        set.setDrawValues(true);
        set.setValueTextSize(17f);
        set.setValueTextColor(color);
        set.setAxisDependency(YAxis.AxisDependency.LEFT);

//        d.addDataSet(set);

        return  set;
    }

    private BarDataSet getBarDataSet(Dataset dataset, int color){
//        BarData d = new BarData();

        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();

        for (int index = 0; index < dataset.getDatas().size(); index++)
            try{
                entries.add(new BarEntry(Float.parseFloat(dataset.getDatas().get(index).toString()), index));
            }catch (Exception e){

            }

        BarDataSet set = new BarDataSet(entries, dataset.getSubtitle());
        set.setColor(color);
        set.setValueTextColor(color);
        set.setValueTextSize(16f);
        set.setAxisDependency(YAxis.AxisDependency.LEFT);

//        d.addDataSet(set);
        return set;
    }


}
