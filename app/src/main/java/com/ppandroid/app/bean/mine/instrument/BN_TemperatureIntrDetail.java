/*
 * Created by yeqinfu on 18-1-8 下午5:31
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.bean.mine.instrument;

import com.ppandroid.im.bean.BaseBody;

/**
 * Created by yeqinfu on 2018/1/8.
 */

public class BN_TemperatureIntrDetail extends BaseBody {

    /**
     * message : {"lastModifyTime":"2018-01-08 17:34:01","companyId":2,"deviceId":42,"recordTime":1515404041000,"temperature":23,"humidity":45,"statusString":"运行","status":3,"id":1}
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
         * lastModifyTime : 2018-01-08 17:34:01
         * companyId : 2
         * deviceId : 42
         * recordTime : 1515404041000
         * temperature : 23
         * humidity : 45
         * statusString : 运行
         * status : 3
         * id : 1
         */

        private String lastModifyTime;
        private int companyId;
        private int deviceId;
        private long recordTime;
        private double temperature;
        private double humidity;
        private String statusString;
        private int status;
        private int id;

        public String getLastModifyTime() {
            return lastModifyTime;
        }

        public void setLastModifyTime(String lastModifyTime) {
            this.lastModifyTime = lastModifyTime;
        }

        public int getCompanyId() {
            return companyId;
        }

        public void setCompanyId(int companyId) {
            this.companyId = companyId;
        }

        public int getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(int deviceId) {
            this.deviceId = deviceId;
        }

        public long getRecordTime() {
            return recordTime;
        }

        public void setRecordTime(long recordTime) {
            this.recordTime = recordTime;
        }

        public double getTemperature() {
            return temperature;
        }

        public void setTemperature(int temperature) {
            this.temperature = temperature;
        }

        public double getHumidity() {
            return humidity;
        }

        public void setHumidity(int humidity) {
            this.humidity = humidity;
        }

        public String getStatusString() {
            return statusString;
        }

        public void setStatusString(String statusString) {
            this.statusString = statusString;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
