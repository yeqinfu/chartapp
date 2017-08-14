package com.ppandroid.app.home.overview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ppandroid.app.R;
import com.ppandroid.app.widget.draglist.AD_DragBase;

/**
 * Created by yeqinfu on 2017/8/14.
 */

public class DragAdapter extends AD_DragBase<String> {

    LayoutInflater inflater;
    public DragAdapter(Context context) {
        super(context);
        inflater=LayoutInflater.from(context);
    }

    @Override
    public View initItemView(int position, View convertView, ViewGroup parent) {
        View item=inflater.inflate(R.layout.item,null);
        TextView tv= (TextView) item.findViewById(android.R.id.text1);
        tv.setText(ts.get(position));
        return item;
    }
}