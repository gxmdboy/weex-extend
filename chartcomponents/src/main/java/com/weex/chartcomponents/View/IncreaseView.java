package com.weex.chartcomponents.View;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import zjhcsoft.com.wx_statistics.ChartView.LineView;
import zjhcsoft.com.wx_statistics.R;
import zjhcsoft.com.wx_statistics.bean.Data_collection;


public class IncreaseView extends LinearLayout {

    private LineView LV;
    private Context context;
//    private GridView GV;
    private Data_collection All_datac;//所有的数据
    private Data_collection show_datac;//展现的数据

    public IncreaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public IncreaseView(Context context) {
        super(context);
        initView(context);
//        this.context=context;
//        LayoutInflater.from(context).inflate(R.layout.line_list_view,this,true);
//        LV = (LineView) findViewById(R.id.LVChart);
//        GV = (GridView)findViewById(R.id.Button_List);
//        GV.setNumColumns(8);
    }

    public void initView(Context context){
        this.context=context;
        LayoutInflater.from(context).inflate(R.layout.line_list_view,this,true);
        LV = (LineView) findViewById(R.id.LVChart);
//        GV = (GridView)findViewById(R.id.Button_List);
//        GV.setNumColumns(8);
    }

    public void setData(Data_collection datac) {
        All_datac = datac;
        initShowdata(0);
        LV.setChart(show_datac,new int[]{0});
//        GV.setAdapter(new MyGridAdapter(context,getAreas(datac)){
//            @Override
//            public void setText(int i, boolean b, boolean[] booleanss) {
////                if(b){
////                    show_datac.getDataset().add(All_datac.getDataset().get(i));
////                }else{
////                    show_datac.getDataset().remove(All_datac.getDataset().get(i));
////                }
//                refreshShow(booleanss);
//                LV.setChart(show_datac, getColors(booleanss));
//            }
//        });
    }

    public void setDataAndDefaultId(Data_collection datac,int defaultId) {
        All_datac = datac;
        initShowdata(defaultId);
        LV.setChart(show_datac,new int[]{defaultId});
    }

    public void refreshShow(boolean[] booleanss){
        show_datac.getDataset().clear();
        for (int i = 0; i < booleanss.length; i++) {
            if (booleanss[i])
                show_datac.getDataset().add(All_datac.getDataset().get(i));
        }
        LV.setChart(show_datac, getColors(booleanss));
    }

    public int[] getColors(boolean[] b){
        int[] i = new int[show_datac.getDataset().size()];
        int count=0;
        for (int j = 0; j < b.length; j++) {
            if(b[j]){
                i[count]=j;count++;
            }
        }
        return i;
    }

//    public ArrayList<String> getAreas(data_collection datac){
//        ArrayList<String> strings = new ArrayList<String>();
//        for (int i = 0; i < datac.getDataset().size(); i++) {
//            strings.add(datac.getDataset().get(i).getSubtitle());
//        }
//        return  strings;
//    }

    //初始化展现数据
    public void initShowdata(int defId){
        show_datac=new Data_collection();
        show_datac.setXString(All_datac.getXString());
        show_datac.getDataset().add(All_datac.getDataset().get(defId));
        show_datac.setDecimal(All_datac.getDecimal());
        show_datac.setUnit(All_datac.getUnit());
        show_datac.setTitle(All_datac.getTitle());
    }


}
