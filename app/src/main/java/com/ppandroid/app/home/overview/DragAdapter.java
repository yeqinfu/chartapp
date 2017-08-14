package com.ppandroid.app.home.overview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ppandroid.app.R;
import com.ppandroid.app.bean.overview.BN_OverViewConfig;
import com.ppandroid.app.widget.draglist.AD_DragBase;

/**
 * Created by yeqinfu on 2017/8/14.
 */

public class DragAdapter extends AD_DragBase<BN_OverViewConfig.MessageBean.ChoosedBean> {

    LayoutInflater inflater;
    public DragAdapter(Context context) {
        super(context);
        inflater=LayoutInflater.from(context);
    }

    @Override
    public View initItemView(final int position, View convertView, ViewGroup parent) {
        View item=inflater.inflate(R.layout.item,null);
        TextView tv= (TextView) item.findViewById(R.id.tv_content);
        tv.setText(ts.get(position).getName());
        ImageView iv_del= (ImageView) item.findViewById(R.id.iv_del);
        iv_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener!=null){
                    listener.onItemModified(position);
                }
            }
        });

        return item;
    }
    ItemModifiedListenr listener;

    public ItemModifiedListenr getListener() {
        return listener;
    }

    public void setListener(ItemModifiedListenr listener) {
        this.listener = listener;
    }

    interface  ItemModifiedListenr{
        void onItemModified(int pos);
    }
}