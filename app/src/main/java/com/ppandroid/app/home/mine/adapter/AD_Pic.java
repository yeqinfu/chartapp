/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.home.mine.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.ppandroid.app.R;
import com.ppandroid.app.utils.toast.ToastUtil;

import java.util.ArrayList;

/**
 * Created by yeqinfu on 2017/8/28.
 */

public class AD_Pic extends BaseAdapter {
    /**
     * 每次从本地传一张照片就添加一张
     */
    ArrayList<Bitmap> Bitmaps	= new ArrayList<Bitmap>();
    private Activity mContext;

    public AD_Pic(ArrayList<Bitmap> bitmaps, Activity context) {
        Bitmaps = bitmaps;
        mContext = context;
    }

    @Override
    public int getCount() {
        return Bitmaps.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View layout = mContext.getLayoutInflater().inflate(R.layout.want_to_sell_imgview, null);
        ImageView userImg = (ImageView) layout.findViewById(R.id.userImg);
        ImageView deleteImg = (ImageView) layout.findViewById(R.id.deleteImg);
        userImg.setImageBitmap(Bitmaps.get(position));
        deleteImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmaps.remove(position);
                notifyDataSetChanged();
                ToastUtil.toast(mContext, "删除成功");
            }
        });

        return layout;
    }

}
