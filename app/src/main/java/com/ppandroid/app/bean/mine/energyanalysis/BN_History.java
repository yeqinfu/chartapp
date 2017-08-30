package com.ppandroid.app.bean.mine.energyanalysis;

import com.ppandroid.im.bean.BaseBody;

import java.util.List;

/**
 * Created by yeqinfu on 2017/8/30.
 */

public class BN_History extends BaseBody {

    private List<String> message;

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }
}
