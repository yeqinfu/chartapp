/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ppandroid.app.R;

/**
 * Created by yeqinfu on 2017/8/8.
 */

public class HeadViewLayout extends RelativeLayout {
    public HeadViewLayout(Context context) {
        super(context);
    }

    public HeadViewLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        readAttributes(attrs);
    }

    public HeadViewLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        readAttributes(attrs);
    }
    // Content View Elements

    private TextView tv_back;
    private TextView tv_center;
    private TextView tv_right;
    private ImageView iv_right;
    private ImageView iv_right2;
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_center = (TextView) findViewById(R.id.tv_center);
        tv_right = (TextView) findViewById(R.id.tv_right);
        iv_right = (ImageView) findViewById(R.id.iv_right);
        iv_right2 = (ImageView) findViewById(R.id.iv_right2);
        initTheme();
    }

    private void initTheme() {
        if (theme==THEME_NORMAL){
            Drawable drawable = getResources().getDrawable(R.drawable.back_grey);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            tv_back.setCompoundDrawables(drawable, null, null, null);
            tv_back.setTextColor(getResources().getColor(R.color.color_02));
            tv_center.setTextColor(getResources().getColor(R.color.color_02));
            tv_right.setTextColor(getResources().getColor(R.color.color_02));
        }else if (theme==THEME_WHITE){
            Drawable drawable = getResources().getDrawable(R.drawable.back_white);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            tv_back.setCompoundDrawables(drawable, null, null, null);
            tv_back.setTextColor(getResources().getColor(R.color.white));
            tv_center.setTextColor(getResources().getColor(R.color.white));
            tv_right.setTextColor(getResources().getColor(R.color.white));
        }else if (theme==THEME_ORANGE){
            Drawable drawable = getResources().getDrawable(R.drawable.back_white);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            tv_back.setCompoundDrawables(drawable, null, null, null);
            tv_back.setTextColor(getResources().getColor(R.color.white));
            tv_center.setTextColor(getResources().getColor(R.color.white));
            tv_right.setTextColor(getResources().getColor(R.color.white));
            setBackgroundResource(R.color.orange);
        }
    }

    public void init(final Activity activity){
        if (activity==null){
            return;
        }
        tv_back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.finish();
            }
        });
    }
    public void setBackText(String msg){
        if (tv_back!=null&& !TextUtils.isEmpty(msg)){
            tv_back.setText(msg);
        }
    }
    public void setIvRight(int rsid,OnClickListener listener){
        if (iv_right!=null){
            iv_right.setVisibility(VISIBLE);
            iv_right.setImageResource(rsid);
            iv_right.setOnClickListener(listener);
        }
    }
    public void setIvRight2(int rsid,OnClickListener listener){
        if (iv_right2!=null){
            iv_right2.setVisibility(VISIBLE);
            iv_right2.setImageResource(rsid);
            iv_right2.setOnClickListener(listener);
        }
    }
    public void setCenterTitle(String msg){
        if (tv_center!=null&& !TextUtils.isEmpty(msg)){
            tv_center.setText(msg);
        }
    }


    public void setRightText(String msg,OnClickListener onClickListener){
        if (tv_right!=null&& !TextUtils.isEmpty(msg)){
            tv_right.setVisibility(VISIBLE);
            tv_right.setText(msg);
            tv_right.setOnClickListener(onClickListener);
        }
    }
    private void readAttributes(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray attributes = getContext()
                    .getTheme().obtainStyledAttributes(attrs, R.styleable.headview, 0, 0);
            theme=attributes.getInt(R.styleable.headview_titleTheme,0);
            attributes.recycle();
        }
    }


    private int theme=0;
    public static final int THEME_WHITE=1;
    public static final int THEME_NORMAL=0;
    /**安全中心桔色*/
    public static final int THEME_ORANGE=2;

    public int getTheme() {
        return theme;
    }

    public void setTheme(int theme) {
        this.theme = theme;
        initTheme();
    }
}
