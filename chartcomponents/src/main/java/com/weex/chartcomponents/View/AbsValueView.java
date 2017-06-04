package com.weex.chartcomponents.View;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import zjhcsoft.com.wx_statistics.ChartView.ColumnView;
import zjhcsoft.com.wx_statistics.R;
import zjhcsoft.com.wx_statistics.bean.Data_collection;


public class AbsValueView extends LinearLayout {

    private ColumnView CV;
    private Context context;
//    private GridView GV;
    private Data_collection All_datac;//所有的数据
    private Data_collection show_datac;//展现的数据

    public AbsValueView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public AbsValueView(Context context) {
        super(context);
//        this.context=context;
//        LayoutInflater.from(context).inflate(R.layout.column_list_view,this,true);
//        CV = (ColumnView) findViewById(R.id.CVChart);
//        GV = (GridView)findViewById(R.id.Button_List);
//        GV.setNumColumns(8);
        initView(context);
    }

    public void initView(Context context){
        this.context=context;
        LayoutInflater.from(context).inflate(R.layout.column_list_view,this,true);
        CV = (ColumnView) findViewById(R.id.CVChart);
//        GV = (GridView)findViewById(R.id.Button_List);
//        GV.setNumColumns(8);
    }

    public void setData(Data_collection datac) {
        All_datac = datac;
        initShowdata(0);
        CV.setChart(show_datac,new int[]{0});
//        GV.setAdapter(new MyGridAdapter(context,getAreas(datac)){
//            @Override
//            public void setText(int i, boolean b, boolean[] booleanss) {
////                if(b){
////                    show_datac.getDataset().add(All_datac.getDataset().get(i));
////                }else{
////                    show_datac.getDataset().remove(All_datac.getDataset().get(i));
////                }
////                CV.setChart(show_datac);
//                refreshShow(booleanss);
//                CV.setChart(show_datac, getColors(booleanss));
//            }
//        });
    }

    public void refreshShow(boolean[] booleanss){
        show_datac.getDataset().clear();
        for (int i = 0; i < booleanss.length; i++) {
            if (booleanss[i])
                show_datac.getDataset().add(All_datac.getDataset().get(i));
        }
        CV.setChart(show_datac, getColors(booleanss));
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

    public void setDataAndDefaultId(Data_collection datac, int defaultAreaId) {
        All_datac = datac;
        initShowdata(defaultAreaId);
        CV.setChart(show_datac,new int[]{defaultAreaId});
    }
}
