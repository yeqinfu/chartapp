package com.ppandroid.app.http;

import com.ppandroid.im.bean.BaseBody;

/**
 * Created by yeqinfu on 2017/7/31.
 */

public interface MyCallBack<T extends BaseBody> {
	void onResponse(T response);
	void  onError(BaseBody error);
}
