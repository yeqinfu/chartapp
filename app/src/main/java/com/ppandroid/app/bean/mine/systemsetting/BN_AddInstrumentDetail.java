package com.ppandroid.app.bean.mine.systemsetting;

import com.ppandroid.im.bean.BaseBody;

/**
 * Created by yeqinfu on 2017/8/28.
 */

public class BN_AddInstrumentDetail extends BaseBody {

    /**
     * message : {"companyId":1,"level":1,"parentId":-1,"code":"87T04","energyClassificationId":1,"energyClassificationName":"电","name":"CH01-1约克离心式制冷机","position":"一楼空调室","charge":null,"rate":"1","codeAddress":"987654321","deviceId":1,"deviceName":"CH01-1离心式制冷机","status":0,"helfCount":1}
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
         * companyId : 1
         * level : 1
         * parentId : -1
         * code : 87T04
         * energyClassificationId : 1
         * energyClassificationName : 电
         * name : CH01-1约克离心式制冷机
         * position : 一楼空调室
         * charge : null
         * rate : 1
         * codeAddress : 987654321
         * deviceId : 1
         * deviceName : CH01-1离心式制冷机
         * status : 0
         * helfCount : 1
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
    }
}
