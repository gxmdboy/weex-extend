package com.weex.chartcomponents.View;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.RadioGroup;

import java.util.ArrayList;

import zjhcsoft.com.wx_statistics.Adapter.MyGridAdapter;
import zjhcsoft.com.wx_statistics.R;

/**
 * Created by Administrator on 2015/7/17.
 */
public class ButtonListDialog extends Dialog {

//    private RadioGroup RG;
    private GridView Button_List;
    private ArrayList<String> Areas;
    private Context context;
    private int defaultId = 0;

    protected ButtonListDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);

    }

    public ButtonListDialog(Context context, int theme,ArrayList<String> Areas ,int defaultId) {
        super(context, theme);
        this.context=context;
        this.Areas=Areas;
        this.defaultId=defaultId;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.buttonlist_dialog_layout);
        setCancelable(true);
        Button_List = (GridView)findViewById(R.id.Tab_Button_List);
        setAdapter();
//        RG=(RadioGroup)findViewById(R.id.RG);
//        RG.setOnCheckedChangeListener(this);
//        RG.check(R.id.time_Rbtn);
    }

    public void ShowNO1(){
//        RG.check(R.id.time_Rbtn);
    }

    public void setAdapter(){
        Button_List.setAdapter(new MyGridAdapter(context,Areas,defaultId){
            @Override
            public void setText(int i, boolean b, boolean[] booleanss) {
                super.setText(i, b, booleanss);
                setTextDiaglog(i, b, booleanss);
            }
        });
    }

    public void setTextDiaglog(int i, boolean b,boolean[] booleanss){

    }
}
