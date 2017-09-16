package com.ppandroid.app.home.mine.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ppandroid.app.R;
import com.ppandroid.app.home.mine.energyanalysis.horizontalanalysis.FG_BaseHistoramAnalysisPage;

/**
 * Created by yeqinfu on 2015/12/31.
 */
public abstract class AD_BaseHorizontalAnalysis extends FragmentStatePagerAdapter {

	private String[]				arrays_title;
	private FG_BaseHistoramAnalysisPage[]	fragments;
	private Context					mContext;

	public AD_BaseHorizontalAnalysis(Activity ac, FragmentManager fm) {
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

	public void initFragment() {
		this.arrays_title = mContext.getResources().getStringArray(R.array.fg_base_h_analysis);
		fragments = new FG_BaseHistoramAnalysisPage[arrays_title.length];
		fragments[0] = getContentFragment();//日
		fragments[0].setArguments(getBundle(0));
		fragments[1] = getContentFragment();//年
		fragments[1].setArguments(getBundle(1));
		fragments[2] = getContentFragment();//月
		fragments[2].setArguments(getBundle(2));
		fragments[3] = getContentFragment();//总
		fragments[3].setArguments(getBundle(3));
	}

    protected abstract Bundle getBundle(int index);

    protected abstract FG_BaseHistoramAnalysisPage getContentFragment();
}