package com.weex.chartcomponents.ChartView;


import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.inqbarna.tablefixheaders.TableFixHeaders;

import zjhcsoft.com.wx_statistics.Adapter.MatrixTableAdapter;
import zjhcsoft.com.wx_statistics.Adapter.MultiMatrixTableAdapter;
import zjhcsoft.com.wx_statistics.R;
import zjhcsoft.com.wx_statistics.bean.Data_collection;
import zjhcsoft.com.wx_statistics.bean.Data_table_result;

public class MultiTableView extends LinearLayout {

    private Context context;
    private TableFixHeaders table;
//    private String[][] show_ss;
    private MultiMatrixTableAdapter<String> matrixTableAdapter;
    public MultiTableView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    protected MultiTableView(Context context) {
        super(context);
        initView(context);
//        this.context=context;
//        LayoutInflater.from(context).inflate(R.layout.table_view, this, true);
//        table = (TableFixHeaders)findViewById(R.id.Table);
    }

    public void initView(Context context){
        this.context=context;
        LayoutInflater.from(context).inflate(R.layout.table_view, this, true);
        table = (TableFixHeaders)findViewById(R.id.Table);
    }



    public void make_matrixTableAdapter(Data_table_result datas){
//        show_ss = new String[datas.getXString().length+1][datas.getDataset().size()+1];
//
//        show_ss[0][0]="时间";
//        for(int i=0;i<datas.getXString().length;i++){
//            show_ss[i+1][0]=datas.getXString()[i];
//        }
//
//        for (int j = 0; j < datas.getDataset().size(); j++) {
//            for (int k = 0; k < datas.getXString().length+1; k++) {
//                if(k==0){
//                    show_ss[0][j+1]=datas.getDataset().get(j).getSubtitle();
//                }else{
//                    String s = datas.getDataset().get(j).getDatas().get(k-1)==null?"":datas.getDataset().get(j).getDatas().get(k-1)+"";
//                    show_ss[k][j+1] =s;
//                }
//            }
//        }
//        getShowSS(datas);
        matrixTableAdapter = new MultiMatrixTableAdapter<String>(context, datas);
        table.setAdapter(matrixTableAdapter);
    }

    public void resetData(Data_table_result datas){
//        show_ss = new String[datas.getXString().length+1][datas.getDataset().size()+1];
//
//        show_ss[0][0]="时间";
//        for(int i=0;i<datas.getXString().length;i++){
//            show_ss[i+1][0]=datas.getXString()[i];
//        }
//
//        for (int j = 0; j < datas.getDataset().size(); j++) {
//            for (int k = 0; k < datas.getXString().length+1; k++) {
//                if(k==0){
//                    show_ss[0][j+1]=datas.getDataset().get(j).getSubtitle();
//                }else{
//                    String s = datas.getDataset().get(j).getDatas().get(k-1)==null?"":datas.getDataset().get(j).getDatas().get(k-1)+"";
//                    show_ss[k][j+1] =s;
//                }
//            }
//        }
//        getShowSS(datas);
//        MatrixTableAdapter<String> matrixTableAdapter = new MatrixTableAdapter<String>(context, show_ss);
//        matrixTableAdapter.notifyDataSetChanged();
        matrixTableAdapter = new MultiMatrixTableAdapter<String>(context, datas);
        table.setAdapter(matrixTableAdapter);
    }

//    public void getShowSS(Data_collection datas){
//        show_ss = new String[datas.getXString().length+1][datas.getDataset().size()+1];
//
//        show_ss[0][0]="时间";
//        for(int i=0;i<datas.getXString().length;i++){
//            show_ss[i+1][0]=datas.getXString()[i];
//        }
//
//        for (int j = 0; j < datas.getDataset().size(); j++) {
//            for (int k = 0; k < datas.getXString().length+1; k++) {
//                if(k==0){
//                    show_ss[0][j+1]=datas.getDataset().get(j).getSubtitle();
//                }else{
//                    String s = datas.getDataset().get(j).getDatas().get(k-1)==null?"":datas.getDataset().get(j).getDatas().get(k-1)+"";
//                    show_ss[k][j+1] =s;
//                }
//            }
//        }
//    }

    }
