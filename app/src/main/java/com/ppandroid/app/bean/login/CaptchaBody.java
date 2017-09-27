/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.bean.login;

import com.ppandroid.im.bean.BaseBody;

/**
 * Created by yeqinfu on 2017/9/25.
 */

public class CaptchaBody extends BaseBody {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
