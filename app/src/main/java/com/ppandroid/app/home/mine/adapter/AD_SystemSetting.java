package com.ppandroid.app.home.mine.adapter;


import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ppandroid.app.R;
import com.ppandroid.app.home.mine.FG_SystemSettingPage;

/**
 * Created by yeqinfu on 2015/12/31.
 */
public class AD_SystemSetting extends FragmentStatePagerAdapter {

	private String[]	arrays_title;
	private Fragment[]	fragments;
	private Context		mContext;

	public AD_SystemSetting(Activity ac, FragmentManager fm) {
		super(fm);
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
		this.arrays_title = mContext.getResources().getStringArray(R.array.fg_setting);
		fragments = new Fragment[arrays_title.length];
		fragments[0] = new FG_SystemSettingPage();//我的收藏图库
		fragments[1] = new FG_SystemSettingPage();//攻略
		fragments[2] = new FG_SystemSettingPage();//装修案例
		fragments[3] = new FG_SystemSettingPage();//日记
		fragments[4] = new FG_SystemSettingPage();//小区

	}
}
