/*
 * Created by yeqinfu on 18-1-8 下午5:30
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.bean.mine.instrument;

import com.ppandroid.im.bean.BaseBody;

/**
 * Created by yeqinfu on 2018/1/8.
 */

public class BN_WaterIntrumentDetail extends BaseBody {


    /**
     * message : {"lastModifyTime":"2018-01-08 17:33:35","companyId":2,"deviceId":39,"recordTime":1515404015000,"m3":"207.78","statusString":"运行","status":3,"id":1}
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
         * lastModifyTime : 2018-01-08 17:33:35
         * companyId : 2
         * deviceId : 39
         * recordTime : 1515404015000
         * m3 : 207.78
         * statusString : 运行
         * status : 3
         * id : 1
         */

        private String lastModifyTime;
        private int companyId;
        private int deviceId;
        private long recordTime;
        private String m3;
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

        public String getM3() {
            return m3;
        }

        public void setM3(String m3) {
            this.m3 = m3;
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
