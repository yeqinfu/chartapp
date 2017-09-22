package com.ppandroid.app.home.mine.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ppandroid.app.home.mine.energyanalysis.totalhorzizontalanalysis.FG_BaseLineChart;

/**
 * Created by yeqinfu on 2015/12/31.
 */
public abstract class AD_BaseHuanTong extends FragmentStatePagerAdapter {

	protected String[]				arrays_title;
    protected FG_BaseLineChart[]	fragments;
    protected Context					mContext;
    protected abstract void initFragment();
	public AD_BaseHuanTong(Activity ac, FragmentManager fm) {
		super(fm);
		mContext = ac;
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



    protected abstract Bundle getBundle(int index);

    protected abstract FG_BaseLineChart getContentFragment();

    @Override
    public void restoreState(Parcelable arg0, ClassLoader arg1) {
        //do nothing here! no call to super.restoreState(arg0, arg1);
        //super方法中会抛出异常，这边重写解决，至于为啥，没明白
       /*
       这样也行
       try{
            super.restoreState(state, loader);
        }catch (NullPointerException e){
            // null caught
        }

        */
    }
}
