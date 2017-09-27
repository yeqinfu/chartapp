/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.bean.mine.teammanagemet;

import com.ppandroid.im.bean.BaseBody;

import java.util.List;

/**
 * Created by yeqinfu on 2017/8/24.
 */

public class BN_TeamManagement extends BaseBody {


    private List<BN_Team> message;

    public List<BN_Team> getMessage() {
        return message;
    }

    public void setMessage(List<BN_Team> message) {
        this.message = message;
    }

}
