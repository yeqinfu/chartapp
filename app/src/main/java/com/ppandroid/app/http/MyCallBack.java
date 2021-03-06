/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.http;

import com.ppandroid.app.bean.ErrorBody;
import com.ppandroid.im.bean.BaseBody;

/**
 * Created by yeqinfu on 2017/7/31.
 */

public interface MyCallBack<T extends BaseBody> {
	void onResponse(T response);
	void  onError(ErrorBody error);

}
