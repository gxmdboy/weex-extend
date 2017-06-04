package com.weex.chartcomponents.View;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import java.util.ArrayList;

import zjhcsoft.com.wx_statistics.ChartView.MultiTableView;
import zjhcsoft.com.wx_statistics.ChartView.TableView;
import zjhcsoft.com.wx_statistics.R;
import zjhcsoft.com.wx_statistics.bean.Data_collection;
import zjhcsoft.com.wx_statistics.bean.Data_result;
import zjhcsoft.com.wx_statistics.bean.Data_table_result;


public class MultiTableValueView extends LinearLayout {

    private MultiTableView TV;
    private Context context;
//    private GridView GV;
    private Data_table_result All_datas;//所有的数据
    private Data_table_result show_datac;//展现的数据

    public MultiTableValueView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MultiTableValueView(Context context) {
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
        LayoutInflater.from(context).inflate(R.layout.table_value_view,this,true);
        TV = (MultiTableView) findViewById(R.id.TChart);
//        GV = (GridView)findViewById(R.id.Button_List);
//        GV.setNumColumns(8);
    }

    public void setData(Data_table_result datac) {
        All_datas = datac;
        initShowdata(0);
        TV.make_matrixTableAdapter(show_datac);
    }

    public void setDataById(Data_table_result datac,int defaultId) {
        All_datas = datac;
        initShowdata(defaultId);
        TV.make_matrixTableAdapter(show_datac);
    }

    public void refreshShow(boolean[] booleanss){
        show_datac.getData_collection().clear();
        for (int i = 0; i < booleanss.length; i++) {
            if (booleanss[i])
                show_datac.getData_collection().add(All_datas.getData_collection().get(i));
        }
        TV.resetData(show_datac);
    }

//    public int[] getColors(boolean[] b){
//        int[] i = new int[show_datac.getDataset().size()];
//        int count=0;
//        for (int j = 0; j < b.length; j++) {
//            if(b[j]){
//                i[count]=j;count++;
//            }
//        }
//        return i;
//    }

//    public ArrayList<String> getAreas(data_collection datac){
//        ArrayList<String> strings = new ArrayList<String>();
//        for (int i = 0; i < datac.getDataset().size(); i++) {
//            strings.add(datac.getDataset().get(i).getSubtitle());
//        }
//        return  strings;
//    }

    //初始化展现数据
    public void initShowdata(int defaultId){
        show_datac=new Data_table_result();
        show_datac.setTable_header(All_datas.getTable_header());
        show_datac.setDataList(All_datas.getDataList());
        show_datac.getData_collection().add(All_datas.getData_collection().get(defaultId));
    }
}
