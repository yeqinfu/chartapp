package com.ppandroid.app.bean;

import java.util.UUID;

/**
 * Created by yeqinfu on 2017/4/6.
 */

public class ET_ReSend extends ET_Base {


    /**刷新*/
    public static final int TASKID_RESEND = UUID.randomUUID().hashCode();


    public ET_ReSend(int taskId) {
        super(taskId);
    }
}
