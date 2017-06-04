package com.weex.chartcomponents.View;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.RadioGroup;

import zjhcsoft.com.wx_statistics.R;

/**
 * Created by Administrator on 2015/7/17.
 */
public class SearchWayDialog extends Dialog implements RadioGroup.OnCheckedChangeListener{

    private RadioGroup RG;

    protected SearchWayDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);

    }

    public SearchWayDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int id) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.searchway_dialog_layout);
        setCancelable(true);
        RG=(RadioGroup)findViewById(R.id.RG);
        RG.setOnCheckedChangeListener(this);
        RG.check(R.id.time_Rbtn);
    }

    public void ShowNO1(){
        RG.check(R.id.time_Rbtn);
    }
}
