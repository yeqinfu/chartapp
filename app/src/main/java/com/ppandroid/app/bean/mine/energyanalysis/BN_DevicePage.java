/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.bean.mine.energyanalysis;

import com.ppandroid.im.bean.BaseBody;

import java.util.List;

/**
 * Created by yeqinfu on 2017/8/31.
 */

public class BN_DevicePage extends BaseBody {

    /**
     * message : {"analysisDeviceParamDtoList":[{"deviceName":"CH01-1离心式制冷机","deviceRatio":"65%","deviceKwh":"11"},{"deviceName":"CH01-2离心式制冷机","deviceRatio":"35%","deviceKwh":"6"},{"deviceName":"信","deviceRatio":"0%","deviceKwh":"0"},{"deviceName":"哈哈","deviceRatio":"0%","deviceKwh":"0"},{"deviceName":"永动机","deviceRatio":"0%","deviceKwh":"0"}],"analysisDeviceSum":"17"}
     */

    private MessageBean message;

    public MessageBean getMessage() {
        return message;
    }

    public void setMessage(MessageBean message) {
        this.message = message;
    }

    public static class MessageBean {
        /**
         * analysisDeviceParamDtoList : [{"deviceName":"CH01-1离心式制冷机","deviceRatio":"65%","deviceKwh":"11"},{"deviceName":"CH01-2离心式制冷机","deviceRatio":"35%","deviceKwh":"6"},{"deviceName":"信","deviceRatio":"0%","deviceKwh":"0"},{"deviceName":"哈哈","deviceRatio":"0%","deviceKwh":"0"},{"deviceName":"永动机","deviceRatio":"0%","deviceKwh":"0"}]
         * analysisDeviceSum : 17
         */

        private String analysisDeviceSum;
        private String dateString;
        private List<AnalysisDeviceParamDtoListBean> analysisDeviceParamDtoList;

        public String getDateString() {
            return dateString;
        }

        public void setDateString(String dateString) {
            this.dateString = dateString;
        }

        public String getAnalysisDeviceSum() {
            return analysisDeviceSum;
        }

        public void setAnalysisDeviceSum(String analysisDeviceSum) {
            this.analysisDeviceSum = analysisDeviceSum;
        }

        public List<AnalysisDeviceParamDtoListBean> getAnalysisDeviceParamDtoList() {
            return analysisDeviceParamDtoList;
        }

        public void setAnalysisDeviceParamDtoList(List<AnalysisDeviceParamDtoListBean> analysisDeviceParamDtoList) {
            this.analysisDeviceParamDtoList = analysisDeviceParamDtoList;
        }

        public static class AnalysisDeviceParamDtoListBean {
            /**
             * deviceName : CH01-1离心式制冷机
             * deviceRatio : 65%
             * deviceKwh : 11
             */

            private String deviceName;
            private String deviceRatio;
            private String deviceKwh;
            private String deviceId;

            public String getDeviceId() {
                return deviceId;
            }

            public void setDeviceId(String deviceId) {
                this.deviceId = deviceId;
            }

            public String getDeviceName() {
                return deviceName;
            }

            public void setDeviceName(String deviceName) {
                this.deviceName = deviceName;
            }

            public String getDeviceRatio() {
                return deviceRatio;
            }

            public void setDeviceRatio(String deviceRatio) {
                this.deviceRatio = deviceRatio;
            }

            public String getDeviceKwh() {
                return deviceKwh;
            }

            public void setDeviceKwh(String deviceKwh) {
                this.deviceKwh = deviceKwh;
            }
        }
    }
}
