package com.ppandroid.app.home.mine.adapter;

import android.widget.BaseAdapter;

/**
 * Created by yeqinfu on 2017/9/8.
 */

public abstract class AD_SlideDeleteBase extends BaseAdapter {

    public void setOnClickListenerEditOrDelete(OnClickListenerDetailOrDelete onClickListenerEditOrDelete1){
        this.onClickListenerDetailOrDelete=onClickListenerEditOrDelete1;
    }
    protected OnClickListenerDetailOrDelete onClickListenerDetailOrDelete;

}
