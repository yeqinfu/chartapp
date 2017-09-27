/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.home.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ppandroid.app.R;
import com.ppandroid.app.bean.devices.BN_Devices;
import com.ppandroid.app.utils.glide.GlideUtils;

import java.util.List;

public class AD_ExList extends BaseExpandableListAdapter {
	List<BN_Devices.MessageBean.DeviceCateListBean>	deviceCateList;

    private int[] images={
            R.drawable.icon_gaoya,
            R.drawable.icon_gaoya02,
            R.drawable.icon_gaoya03,
            R.drawable.icon_gaoya04,
            R.drawable.icon_gaoya05
    };

	private Context									mContext;

	public AD_ExList(List<BN_Devices.MessageBean.DeviceCateListBean> deviceCateList, Context context) {
		this.deviceCateList = deviceCateList;
		mContext = context;
	}

	//  获得某个父项的某个子项
	@Override
	public Object getChild(int parentPos, int childPos) {
		return deviceCateList.get(parentPos).getDeviceList().get(childPos);
	}

	//  获得父项的数量
	@Override
	public int getGroupCount() {
		return deviceCateList.size();
	}

	//  获得某个父项的子项数目
	@Override
	public int getChildrenCount(int parentPos) {
		return deviceCateList.get(parentPos).getDeviceList().size();
	}

	//  获得某个父项
	@Override
	public Object getGroup(int parentPos) {
		return deviceCateList.get(parentPos);
	}

	//  获得某个父项的id
	@Override
	public long getGroupId(int parentPos) {
		return parentPos;
	}

	//  获得某个父项的某个子项的id
	@Override
	public long getChildId(int parentPos, int childPos) {
		return childPos;
	}

	//  按函数的名字来理解应该是是否具有稳定的id，这个方法目前一直都是返回false，没有去改动过
	@Override
	public boolean hasStableIds() {
		return false;
	}

	//  获得父项显示的view
	@Override
	public View getGroupView(int parentPos, boolean b, View view, ViewGroup viewGroup) {
		if (view == null) {
			LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.parent_item, null);
		}
		ImageView iv_arrow = (ImageView) view.findViewById(R.id.iv_arrow);
		if (deviceCateList.get(parentPos).isOpen()) {
			iv_arrow.setImageResource(R.drawable.icon_arrar);
		}
		else {
			Resources res = mContext.getResources();
			Bitmap img = BitmapFactory.decodeResource(res, R.drawable.icon_arrar);
			Matrix matrix = new Matrix();
			matrix.postRotate(180); //翻转180度
			int width = img.getWidth();
			int height = img.getHeight();
			Bitmap img_a = Bitmap.createBitmap(img, 0, 0, width, height, matrix, true);
			iv_arrow.setImageBitmap(img_a);
		}
		view.setTag(R.layout.parent_item, parentPos);
		view.setTag(R.layout.child_item, -1);
		TextView text = (TextView) view.findViewById(R.id.parent_title);
		text.setText(deviceCateList.get(parentPos).getName());
		ImageView icon = (ImageView) view.findViewById(R.id.group_icon);
		icon.setImageResource(images[parentPos%images.length]);
		return view;
	}

	//  获得子项显示的view
	@Override
	public View getChildView(int parentPos, int childPos, boolean b, View view, ViewGroup viewGroup) {
		if (view == null) {
			LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(R.layout.child_item, null);
		}
		view.setTag(R.layout.parent_item, parentPos);
		view.setTag(R.layout.child_item, childPos);
		ImageView iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
		TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
		TextView tv_num = (TextView) view.findViewById(R.id.tv_num);
		TextView btn_action = (TextView) view.findViewById(R.id.btn_action);
		BN_Devices.MessageBean.DeviceCateListBean.DeviceListBean item = deviceCateList.get(parentPos).getDeviceList().get(childPos);
        String url=GlideUtils.addImageBaseUrl(item.getPhoto());
        Glide.with(mContext).load(url).asBitmap().centerCrop().placeholder(R.drawable.pic_device_default).into(iv_icon);

        tv_name.setText(item.getName());
        tv_num.setText(item.getModel());
        if (item.getStatus()==2){
            btn_action.setBackgroundResource(R.drawable.shape_oval_solid_color_blue);
        }else if (item.getStatus()==3){
            btn_action.setBackgroundResource(R.drawable.shape_oval_solid_color_green);
        }else{
            btn_action.setBackgroundResource(R.drawable.shape_oval_solid_color_grey);
        }
        btn_action.setText(item.getStatusString());
		return view;
	}

	//  子项是否可选中，如果需要设置子项的点击事件，需要返回true
	@Override
	public boolean isChildSelectable(int i, int i1) {
		return true;
	}
}