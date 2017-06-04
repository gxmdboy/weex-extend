package com.weex.chartcomponents.ChartView;


import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.DefaultValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;

import zjhcsoft.com.wx_statistics.R;
import zjhcsoft.com.wx_statistics.Util.ColorGet;
import zjhcsoft.com.wx_statistics.Util.MyValueFormatter;
import zjhcsoft.com.wx_statistics.Util.StringUtil;
import zjhcsoft.com.wx_statistics.bean.Data_collection;


public class ColumnView extends LinearLayout {

    private BarChart mChart;
    private Context context;
    private int[] colors;

    public ColumnView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public  ColumnView(Context context) {
        super(context);
        initView(context);
    }

    public void initView(Context context){
        this.context=context;
        LayoutInflater.from(context).inflate(R.layout.column_view,this,true);
        mChart = (BarChart) findViewById(R.id.Chart);
        mChart.setDescription("");
        mChart.setBackgroundColor(Color.WHITE);
        mChart.setDrawGridBackground(false);
        mChart.setDrawBarShadow(false);
        // draw bars behind lines
//        mChart.setDrawOrder(new CombinedChart.DrawOrder[] {
//                CombinedChart.DrawOrder.BAR
//        });

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setDrawLabels(false);
        rightAxis.setDrawGridLines(false);
        rightAxis.setDrawAxisLine(true);
        rightAxis.setDrawLimitLinesBehindData(true);


//        rightAxis.enableGridDashedLine(10f, 10f, 0f);
//        rightAxis.setStartAtZero(false);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setStartAtZero(false);
        leftAxis.setDrawGridLines(true);
        leftAxis.setTextSize(13f);
//        leftAxis.setLabelCount(5);

//        leftAxis.setValueFormatter(new MyValueFormatter(2));

        XAxis xAxis = mChart.getXAxis();
        xAxis.setTextSize(12f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
//        xAxis.setSpaceBetweenLabels(10);

        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);
        l.setForm(Legend.LegendForm.SQUARE);

//        最大最小
//        leftAxis.setAxisMaxValue(220f);
//        leftAxis.setAxisMinValue(-50f);
        colors =new ColorGet().get25Colors();
    }

    public void setChart(Data_collection datac,int[] ints) {
//        CombinedData data = new CombinedData(datac.getXString());
//        data.setData(generateBarData(datac,ints));

//        mChart.setData(testgenerateBarData());
        mChart.setData(generateBarData(datac,ints));
//        if(StringUtil.ContainNum(datac.getXString()[0])&&datac.getXString().length>10){
            mChart.getXAxis().setSpaceBetweenLabels(0);
//        }
//        mChart.getAxisLeft().setValueFormatter(new MyValueFormatter(datac.getDecimal()+1));
//        if (datac.getXString().length>9){mChart.zoom(1.4f,1f,0,0);}
//        mChart.zoom(1.1f*(datac.getXString().length/10),1f,0,0);
        mChart.invalidate();
        mChart.animateY(2000);
    }

    private BarData generateBarData(Data_collection datac,int[] ints) {

        BarData d = new BarData();

        for (int a = 0; a < datac.getXString().length; a++) {
            d.addXValue(datac.getXString()[a]);
        }

        for (int i = 0; i < datac.getDataset().size() ; i++) {

            ArrayList<BarEntry> entries = new ArrayList<BarEntry>();

            for (int index = 0; index < datac.getDataset().get(i).getDatas().size(); index++)
                try{
//                    entries.add(new BarEntry(Float.parseFloat(datac.getDataset().get(i).getDatas().get(index).toString()), index));

                    entries.add(new BarEntry(datac.getDataset().get(i).getDatas().get(index),index));
//                    entries.add(new BarEntry(FloatFormat.format(datac.getDataset().get(i).getDatas().get(index)),index));
                }catch (Exception e){
//                    BarEntry barEntry = new BarEntry(0,index);
//                    entries.add(new BarEntry(0,index));
//                    entries.add(new BarEntry(Float.NaN,index));
                }

        BarDataSet set = new BarDataSet(entries, datac.getDataset().get(i).getSubtitle());
        set.setColor(colors[ints[i]]);
        set.setValueTextColor(colors[ints[i]]);
        set.setValueTextSize(12f);
        set.setValueFormatter(new MyValueFormatter(datac.getDecimal()));
        d.addDataSet(set);
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        }

        return d;
    }
    private BarData testgenerateBarData(/*Data_collection datac,int[] ints*/) {

        BarData d = new BarData();

        for (int a = 0; a < 5; a++) {
            d.addXValue(a+"");
        }


            ArrayList<BarEntry> entries = new ArrayList<BarEntry>();
            entries.add(new BarEntry(4,0));
        entries.add(new BarEntry(1,1));
        entries.add(new BarEntry(2,2));
        entries.add(new BarEntry(3,3));
        entries.add(new BarEntry(4,4));
            BarDataSet set = new BarDataSet(entries, "");
            set.setColors(new int[]{Color.TRANSPARENT,colors[1],colors[1],colors[1],colors[1]});
//            set.setValueTextColor(new int[]{colors[0],colors[1]});
            set.setValueTextSize(12f);

//            set.setValueFormatter(new MyValueFormatter(datac.getDecimal()));
            d.addDataSet(set);


        ArrayList<BarEntry> entries1 = new ArrayList<BarEntry>();
            entries1.add(new BarEntry(1,0));
        entries1.add(new BarEntry(1,1));
        entries1.add(new BarEntry(2,2));
        entries1.add(new BarEntry(3,3));
        entries1.add(new BarEntry(4,4));
        BarDataSet set1 = new BarDataSet(entries1, "");
        set1.setColors(new int[]{colors[0],colors[0],colors[0],colors[0],colors[0]});
//            set.setValueTextColor(new int[]{colors[0],colors[1]});
        set1.setValueTextSize(12f);
//            set.setValueFormatter(new MyValueFormatter(datac.getDecimal()));
        d.addDataSet(set1);
            set.setAxisDependency(YAxis.AxisDependency.LEFT);
//        }

        return d;
    }
}
