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
import android.widget.Toast;

import com.ppandroid.app.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AD_ExList extends BaseExpandableListAdapter {
	private Map<BN_Ex, List<String>>	dataset		= new HashMap<>();
	private ArrayList<BN_Ex>			parentList	= new ArrayList<>();

	private Context						mContext;

	public AD_ExList(Map<BN_Ex, List<String>> dataset, ArrayList<BN_Ex> parentList, Context context) {
		this.dataset = dataset;
		this.parentList = parentList;
		mContext = context;
	}

	//  获得某个父项的某个子项
	@Override
	public Object getChild(int parentPos, int childPos) {
		return dataset.get(parentList.get(parentPos)).get(childPos);
	}

	//  获得父项的数量
	@Override
	public int getGroupCount() {
		return dataset.size();
	}

	//  获得某个父项的子项数目
	@Override
	public int getChildrenCount(int parentPos) {
		return dataset.get(parentList.get(parentPos)).size();
	}

	//  获得某个父项
	@Override
	public Object getGroup(int parentPos) {
		return dataset.get(parentList.get(parentPos));
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
        if (parentList.get(parentPos).isOpen()) {
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
		text.setText(parentList.get(parentPos).getTitle());
        ImageView icon= (ImageView) view.findViewById(R.id.group_icon);
        icon.setImageResource(parentList.get(parentPos).getIconId());
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
		TextView text = (TextView) view.findViewById(R.id.child_title);
		text.setText(dataset.get(parentList.get(parentPos)).get(childPos));
		text.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Toast.makeText(mContext, "点到了内置的textview", Toast.LENGTH_SHORT).show();
			}
		});
		return view;
	}

	//  子项是否可选中，如果需要设置子项的点击事件，需要返回true
	@Override
	public boolean isChildSelectable(int i, int i1) {
		return false;
	}
}