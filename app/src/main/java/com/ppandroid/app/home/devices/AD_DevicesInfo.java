/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.home.devices;


import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ppandroid.app.R;

/**
 * Created by yeqinfu on 2015/12/31.
 */
public class AD_DevicesInfo extends FragmentStatePagerAdapter {

	private String[]	arrays_title;
	private Fragment[]	fragments;
	private Context		mContext;


    private String id="-1";
	public AD_DevicesInfo(Activity ac, FragmentManager fm,String id) {
		super(fm);
        this.id=id;
		mContext = ac;
		initFragment();
	}

	@Override
	public Fragment getItem(int position) {
		return fragments[position];
	}

	@Override
	public int getItemPosition(Object object) {
		return POSITION_NONE;
	}

	@Override
	public int getCount() {
		return arrays_title.length;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return arrays_title[position];
	}

	public void initFragment() {
		this.arrays_title = mContext.getResources().getStringArray(R.array.fg_devices_info);
		fragments = new Fragment[arrays_title.length];
		fragments[0] = new FG_DevicesMonitor();//数据监测
        fragments[0].setArguments(FG_DevicesMonitor.Companion.createBundle(id));
		fragments[1] = new FG_DevicesDetail();//账台信息
        fragments[1].setArguments(FG_DevicesDetail.Companion.createBundle(id));

    }
}
