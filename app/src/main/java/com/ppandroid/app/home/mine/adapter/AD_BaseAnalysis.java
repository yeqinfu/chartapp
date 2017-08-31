package com.ppandroid.app.home.mine.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ppandroid.app.R;
import com.ppandroid.app.home.mine.energyanalysis.FG_BaseAnlysisPage;

/**
 * Created by yeqinfu on 2015/12/31.
 */
public abstract class AD_BaseAnalysis extends FragmentStatePagerAdapter {

	private String[]				arrays_title;
	private FG_BaseAnlysisPage[]	fragments;
	private Context					mContext;

	public AD_BaseAnalysis(Activity ac, FragmentManager fm) {
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
		this.arrays_title = mContext.getResources().getStringArray(R.array.fg_base_analysis);
		fragments = new FG_BaseAnlysisPage[arrays_title.length];
		fragments[0] = getContentFragment();//日
		fragments[0].setArguments(FG_BaseAnlysisPage.Companion.createBundle(0));
		fragments[1] = getContentFragment();//年
		fragments[1].setArguments(FG_BaseAnlysisPage.Companion.createBundle(1));
		fragments[2] = getContentFragment();//月
		fragments[2].setArguments(FG_BaseAnlysisPage.Companion.createBundle(2));
		fragments[3] = getContentFragment();//总
		fragments[3].setArguments(FG_BaseAnlysisPage.Companion.createBundle(3));
	}

    protected abstract FG_BaseAnlysisPage getContentFragment();
}
