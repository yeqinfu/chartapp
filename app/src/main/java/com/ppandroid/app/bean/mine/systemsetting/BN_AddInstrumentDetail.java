/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.bean.mine.systemsetting;

import com.ppandroid.im.bean.BaseBody;

/**
 * Created by yeqinfu on 2017/8/28.
 */

public class BN_AddInstrumentDetail extends BaseBody {


    /**
     * message : {"companyId":2,"level":1,"parentId":-1,"code":"237","energyClassificationId":3,"energyClassificationName":"温湿度","name":"6664","position":"","charge":"","rate":"","codeAddress":"","deviceId":-1,"deviceName":null,"status":-1,"helfCount":0,"max":56,"min":8,"max1":8,"min1":9}
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
         * companyId : 2
         * level : 1
         * parentId : -1
         * code : 237
         * energyClassificationId : 3
         * energyClassificationName : 温湿度
         * name : 6664
         * position :
         * charge :
         * rate :
         * codeAddress :
         * deviceId : -1
         * deviceName : null
         * status : -1
         * helfCount : 0
         * max : 56
         * min : 8
         * max1 : 8
         * min1 : 9
         */

        private int companyId;
        private int level;
        private int parentId;
        private String code;
        private int energyClassificationId;
        private String energyClassificationName;
        private String name;
        private String position;
        private String charge;
        private String rate;
        private String codeAddress;
        private int deviceId;
        private String deviceName;
        private int status;
        private int helfCount;
        private double max;
        private double min;
        private double max1;
        private double min1;

        public int getCompanyId() {
            return companyId;
        }

        public void setCompanyId(int companyId) {
            this.companyId = companyId;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public int getEnergyClassificationId() {
            return energyClassificationId;
        }

        public void setEnergyClassificationId(int energyClassificationId) {
            this.energyClassificationId = energyClassificationId;
        }

        public String getEnergyClassificationName() {
            return energyClassificationName;
        }

        public void setEnergyClassificationName(String energyClassificationName) {
            this.energyClassificationName = energyClassificationName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getCharge() {
            return charge;
        }

        public void setCharge(String charge) {
            this.charge = charge;
        }

        public String getRate() {
            return rate;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }

        public String getCodeAddress() {
            return codeAddress;
        }

        public void setCodeAddress(String codeAddress) {
            this.codeAddress = codeAddress;
        }

        public int getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(int deviceId) {
            this.deviceId = deviceId;
        }

        public String getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getHelfCount() {
            return helfCount;
        }

        public void setHelfCount(int helfCount) {
            this.helfCount = helfCount;
        }

        public double getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public double getMin() {
            return min;
        }

        public void setMin(double min) {
            this.min = min;
        }

        public double getMax1() {
            return max1;
        }

        public void setMax1(double max1) {
            this.max1 = max1;
        }

        public double getMin1() {
            return min1;
        }

        public void setMin1(int min1) {
            this.min1 = min1;
        }
    }
}
