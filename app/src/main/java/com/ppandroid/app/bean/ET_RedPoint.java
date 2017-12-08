/*
 * Created by yeqinfu on 17-12-8 上午9:41
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.bean;

import java.util.UUID;

/**
 * Created by yeqinfu on 2017/12/8.
 */

public class ET_RedPoint extends ET_Base {
    /**小红点逻辑*/
    /**主tab小红点*/
    public static final int TASKID_RED_POINT_SHOW_MAIN = UUID.randomUUID().hashCode();
    /**主tab小红点*/
    public static final int TASKID_RED_POINT_HIDE_MAIN = UUID.randomUUID().hashCode();
    /**new tab列表小红点*/
    public static final int TASKID_RED_POINT_SHOW = UUID.randomUUID().hashCode();
    public static final int TASKID_RED_POINT_HIDE = UUID.randomUUID().hashCode();
    public String type="";
    public String msg="";
    public ET_RedPoint(int taskId) {
        super(taskId);
    }

    public ET_RedPoint(int taskId, String type) {
        super(taskId);
        this.type = type;
    }

    public ET_RedPoint(int taskId, String type, String msg) {
        super(taskId);
        this.type = type;
        this.msg = msg;
    }
}
