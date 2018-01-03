/*
 * Created by yeqinfu on 18-1-3 下午4:57
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.bean.news;

import com.ppandroid.im.bean.BaseBody;

import java.util.List;

/**
 * Created by yeqinfu on 2018/1/3.
 */

public class BN_RecentComparison  extends BaseBody{


    private List<MessageBean> message;

    public List<MessageBean> getMessage() {
        return message;
    }

    public void setMessage(List<MessageBean> message) {
        this.message = message;
    }

    public static class MessageBean {
        /**
         * averageKwh : 平均：0.00kwh
         * accurateAverageKwh : 0.00
         * totalKwh : 0.00
         * accurateTotalKwh : null
         * stageKwh : [{"key":"00时","value":"0.00"},{"key":"01时","value":"0.00"},{"key":"02时","value":"0.00"},{"key":"03时","value":"0.00"},{"key":"04时","value":"0.00"},{"key":"05时","value":"0.00"},{"key":"06时","value":"0.00"},{"key":"07时","value":"0.00"},{"key":"08时","value":"0.00"},{"key":"09时","value":"0.00"},{"key":"10时","value":"0.00"},{"key":"11时","value":"0.00"},{"key":"12时","value":"0.00"},{"key":"13时","value":"0.00"},{"key":"14时","value":"0.00"},{"key":"15时","value":"0.00"},{"key":"16时","value":"0.00"},{"key":"17时","value":"0.00"},{"key":"18时","value":"0.00"},{"key":"19时","value":"0.00"},{"key":"20时","value":"0.00"},{"key":"21时","value":"0.00"},{"key":"22时","value":"0.00"},{"key":"23时","value":"0.00"}]
         * dateString : null
         * deviceTableData : null
         */

        private String averageKwh;
        private String accurateAverageKwh;
        private String totalKwh;
        private Object accurateTotalKwh;
        private Object dateString;
        private Object deviceTableData;
        private List<StageKwhBean> stageKwh;

        public String getAverageKwh() {
            return averageKwh;
        }

        public void setAverageKwh(String averageKwh) {
            this.averageKwh = averageKwh;
        }

        public String getAccurateAverageKwh() {
            return accurateAverageKwh;
        }

        public void setAccurateAverageKwh(String accurateAverageKwh) {
            this.accurateAverageKwh = accurateAverageKwh;
        }

        public String getTotalKwh() {
            return totalKwh;
        }

        public void setTotalKwh(String totalKwh) {
            this.totalKwh = totalKwh;
        }

        public Object getAccurateTotalKwh() {
            return accurateTotalKwh;
        }

        public void setAccurateTotalKwh(Object accurateTotalKwh) {
            this.accurateTotalKwh = accurateTotalKwh;
        }

        public Object getDateString() {
            return dateString;
        }

        public void setDateString(Object dateString) {
            this.dateString = dateString;
        }

        public Object getDeviceTableData() {
            return deviceTableData;
        }

        public void setDeviceTableData(Object deviceTableData) {
            this.deviceTableData = deviceTableData;
        }

        public List<StageKwhBean> getStageKwh() {
            return stageKwh;
        }

        public void setStageKwh(List<StageKwhBean> stageKwh) {
            this.stageKwh = stageKwh;
        }

        public static class StageKwhBean {
            /**
             * key : 00时
             * value : 0.00
             */

            private String key;
            private String value;

            public String getKey() {
                return key;
            }

            public void setKey(String key) {
                this.key = key;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }
    }
}
