/*
 * Created by yeqinfu on 18-1-4 下午4:43
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.bean.overview;

import com.ppandroid.im.bean.BaseBody;

import java.util.List;

/**
 * Created by yeqinfu on 2018/1/4.
 */

public class BN_TemperatureItem extends BaseBody {

    /**
     * message : {"temperatureList":[{"hour":5,"temperature":23,"humidity":45},{"hour":6,"temperature":23,"humidity":45},{"hour":7,"temperature":23,"humidity":45},{"hour":8,"temperature":23,"humidity":45},{"hour":9,"temperature":23,"humidity":45},{"hour":10,"temperature":23,"humidity":45},{"hour":11,"temperature":23,"humidity":45}]}
     */

    private MessageBean message;

    public MessageBean getMessage() {
        return message;
    }

    public void setMessage(MessageBean message) {
        this.message = message;
    }

    public static class MessageBean {
        private List<BN_Temperature.MessageBean.DevicePageBean.ResultBean.TemperatureListBean> temperatureList;

        public List<BN_Temperature.MessageBean.DevicePageBean.ResultBean.TemperatureListBean> getTemperatureList() {
            return temperatureList;
        }

        public void setTemperatureList(List<BN_Temperature.MessageBean.DevicePageBean.ResultBean.TemperatureListBean> temperatureList) {
            this.temperatureList = temperatureList;
        }


    }
}
