package com.ppandroid.app.bean.mine.systemsetting;

import com.ppandroid.app.bean.ET_Base;

import java.util.UUID;

/**
 * Created by yeqinfu on 2017/4/6.
 */

public class ET_SyStemSetting extends ET_Base {

    /**刷新FG_InstrumentPage*/
    public static final int TASKID_REFRESH_INSTRUMENT_PAGE = UUID.randomUUID().hashCode();

    /**刷新FG_DeviceCatePage*/
    public static final int TASKID_REFRESH_DEVICE_CATE_PAGE = UUID.randomUUID().hashCode();

    public ET_SyStemSetting(int taskId) {
        super(taskId);
    }
}
