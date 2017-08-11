package com.ppandroid.app.bean;

import java.util.UUID;

/**
 * Created by yeqinfu on 2017/4/6.
 */

public class ET_Refresh extends ET_Base {

    /**小红点逻辑*/
    public static final int TASKID_REFRESH_RED_POINT = UUID.randomUUID().hashCode();
    /**刷新我的页面*/
    public static final int TASKID_REFRESH_MINE = UUID.randomUUID().hashCode();
    /**登出*/
    public static final int TASKID_REFRESH_LOGINOUT = UUID.randomUUID().hashCode();
    /**刷新我的新家*/
    public static final int TASKID_REFRESH_MY_HOME = UUID.randomUUID().hashCode();

    public ET_Refresh(int taskId) {
        super(taskId);
    }
}
