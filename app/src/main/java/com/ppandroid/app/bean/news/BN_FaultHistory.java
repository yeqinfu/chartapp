/*
 * Created by yeqinfu on 17-12-7 上午10:32
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.bean.news;

import com.ppandroid.im.bean.BaseBody;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yeqinfu on 2017/12/7.
 */

public class BN_FaultHistory extends BaseBody {

    /**
     * message : {"failueWarningList":[{"deviceId":11,"status":"通讯故障","deviceName":"时光机","devicePhoto":"upload/device/1505447770164","warningTime":"2017-12-05 142240","failueTime":"2017-12-05"},{"deviceId":13,"status":"通讯故障","deviceName":"飞机","devicePhoto":"upload/device/1505447762007","warningTime":"2017-12-05 142240","failueTime":"2017-12-05"},{"deviceId":4,"status":"通讯故障","deviceName":"42","devicePhoto":"upload/device/1509531463201","warningTime":"2017-12-05 142200","failueTime":"2017-12-05"},{"deviceId":16,"status":"通讯故障","deviceName":"屠龙刀","devicePhoto":"upload/device/1505961671063","warningTime":"2017-12-05 142200","failueTime":"2017-12-05"},{"deviceId":11,"status":"通讯故障","deviceName":"时光机","devicePhoto":"upload/device/1505447770164","warningTime":"2017-12-05 142140","failueTime":"2017-12-05"},{"deviceId":13,"status":"通讯故障","deviceName":"飞机","devicePhoto":"upload/device/1505447762007","warningTime":"2017-12-05 142140","failueTime":"2017-12-05"},{"deviceId":11,"status":"通讯故障","deviceName":"时光机","devicePhoto":"upload/device/1505447770164","warningTime":"2017-12-05 142040","failueTime":"2017-12-05"},{"deviceId":13,"status":"通讯故障","deviceName":"飞机","devicePhoto":"upload/device/1505447762007","warningTime":"2017-12-05 142040","failueTime":"2017-12-05"},{"deviceId":4,"status":"通讯故障","deviceName":"42","devicePhoto":"upload/device/1509531463201","warningTime":"2017-12-05 142000","failueTime":"2017-12-05"},{"deviceId":16,"status":"通讯故障","deviceName":"屠龙刀","devicePhoto":"upload/device/1505961671063","warningTime":"2017-12-05 142000","failueTime":"2017-12-05"}],"pageNumber":0}
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
         * failueWarningList : [{"deviceId":11,"status":"通讯故障","deviceName":"时光机","devicePhoto":"upload/device/1505447770164","warningTime":"2017-12-05 142240","failueTime":"2017-12-05"},{"deviceId":13,"status":"通讯故障","deviceName":"飞机","devicePhoto":"upload/device/1505447762007","warningTime":"2017-12-05 142240","failueTime":"2017-12-05"},{"deviceId":4,"status":"通讯故障","deviceName":"42","devicePhoto":"upload/device/1509531463201","warningTime":"2017-12-05 142200","failueTime":"2017-12-05"},{"deviceId":16,"status":"通讯故障","deviceName":"屠龙刀","devicePhoto":"upload/device/1505961671063","warningTime":"2017-12-05 142200","failueTime":"2017-12-05"},{"deviceId":11,"status":"通讯故障","deviceName":"时光机","devicePhoto":"upload/device/1505447770164","warningTime":"2017-12-05 142140","failueTime":"2017-12-05"},{"deviceId":13,"status":"通讯故障","deviceName":"飞机","devicePhoto":"upload/device/1505447762007","warningTime":"2017-12-05 142140","failueTime":"2017-12-05"},{"deviceId":11,"status":"通讯故障","deviceName":"时光机","devicePhoto":"upload/device/1505447770164","warningTime":"2017-12-05 142040","failueTime":"2017-12-05"},{"deviceId":13,"status":"通讯故障","deviceName":"飞机","devicePhoto":"upload/device/1505447762007","warningTime":"2017-12-05 142040","failueTime":"2017-12-05"},{"deviceId":4,"status":"通讯故障","deviceName":"42","devicePhoto":"upload/device/1509531463201","warningTime":"2017-12-05 142000","failueTime":"2017-12-05"},{"deviceId":16,"status":"通讯故障","deviceName":"屠龙刀","devicePhoto":"upload/device/1505961671063","warningTime":"2017-12-05 142000","failueTime":"2017-12-05"}]
         * pageNumber : 0
         */

        private int pageNumber;
        private int totalPage;

        public int getTotalPage() {
            return totalPage;
        }

        public void setTotalPage(int totalPage) {
            this.totalPage = totalPage;
        }

        private List<FailueWarningListBean> failueWarningList;

        public int getPageNumber() {
            return pageNumber;
        }

        public void setPageNumber(int pageNumber) {
            this.pageNumber = pageNumber;
        }

        public List<FailueWarningListBean> getFailueWarningList() {
            return failueWarningList;
        }

        public void setFailueWarningList(List<FailueWarningListBean> failueWarningList) {
            this.failueWarningList = failueWarningList;
        }

        public static class FailueWarningListBean implements Serializable{
            /**
             * deviceId : 11
             * status : 通讯故障
             * deviceName : 时光机
             * devicePhoto : upload/device/1505447770164
             * warningTime : 2017-12-05 142240
             * failueTime : 2017-12-05
             */

            private int deviceId;
            private String status;
            private String deviceName;
            private String devicePhoto;
            private String warningTime;
            private String failueTime;
            private String mark;

            public String getMark() {
                return mark;
            }

            public void setMark(String mark) {
                this.mark = mark;
            }

            public int getDeviceId() {
                return deviceId;
            }

            public void setDeviceId(int deviceId) {
                this.deviceId = deviceId;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getDeviceName() {
                return deviceName;
            }

            public void setDeviceName(String deviceName) {
                this.deviceName = deviceName;
            }

            public String getDevicePhoto() {
                return devicePhoto;
            }

            public void setDevicePhoto(String devicePhoto) {
                this.devicePhoto = devicePhoto;
            }

            public String getWarningTime() {
                return warningTime;
            }

            public void setWarningTime(String warningTime) {
                this.warningTime = warningTime;
            }

            public String getFailueTime() {
                return failueTime;
            }

            public void setFailueTime(String failueTime) {
                this.failueTime = failueTime;
            }
        }
    }
}
