package com.ppandroid.app.http;

import com.ppandroid.im.bean.BN_Base;

/**
 * Created by yeqinfu on 2017/7/31.
 */

public interface MyCallBack<T extends BN_Base> {
	void onResponse(T response);
	void  onError(BN_Base error);
}
