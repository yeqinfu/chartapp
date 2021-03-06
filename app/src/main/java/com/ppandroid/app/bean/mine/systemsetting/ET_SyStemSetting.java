/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.bean.mine.systemsetting;

import com.ppandroid.app.bean.ET_Base;

import java.util.UUID;

/**
 * Created by yeqinfu on 2017/4/6.
 */

public class ET_SyStemSetting extends ET_Base {

    /**刷新FG_InstrumentPage*/
    public static final int TASKID_REFRESH_INSTRUMENT_PAGE = UUID.randomUUID().hashCode();

    /**FG_EnergyChargingPage*/
    public static final int TASKID_REFRESH_ENERGY_CHARGING= UUID.randomUUID().hashCode();
    /**FG_ImportantDevicePage*/
    public static final int TASKID_REFRESH_IMPORTANT_DEVICE = UUID.randomUUID().hashCode();
    /**刷新FG_DeviceCatePage*/
    public static final int TASKID_REFRESH_DEVICE_CATE_PAGE = UUID.randomUUID().hashCode();
    /**刷新FG_DeviceAreaPage*/
    public static final int TASKID_REFRESH_DEVICE_AREA_PAGE = UUID.randomUUID().hashCode();
    /**FG_AboutArea 关闭*/
    public static final int TASKID_CLOSE_FG_ABOUT_AREA = UUID.randomUUID().hashCode();
    /**FG_AboutCate 关闭*/
    public static final int TASKID_CLOSE_FG_ABOUT_CATE = UUID.randomUUID().hashCode();
    /**FG_AboutInstrument 关闭*/
    public static final int TASKID_CLOSE_FG_ABOUT_INSTRUMENT = UUID.randomUUID().hashCode();

    public ET_SyStemSetting(int taskId) {
        super(taskId);
    }
}
