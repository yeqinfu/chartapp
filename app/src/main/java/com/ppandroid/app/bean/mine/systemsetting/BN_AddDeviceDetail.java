package com.ppandroid.app.bean.mine.systemsetting;

import com.ppandroid.im.bean.BaseBody;

/**
 * Created by yeqinfu on 2017/8/29.
 */

public class BN_AddDeviceDetail extends BaseBody {


    /**
     * message : {"deviceAreaName":"顶级区域","deviceCateName":"冷干机","instrumentName":"哈哈","id":7,"companyId":1,"name":"永动机","model":"jkk","photo":"device/1503978816520","deviceAreaId":3,"deviceCateId":6,"instrumentId":18,"propertiesJson":"[]","status":3}
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
         * deviceAreaName : 顶级区域
         * deviceCateName : 冷干机
         * instrumentName : 哈哈
         * id : 7
         * companyId : 1
         * name : 永动机
         * model : jkk
         * photo : device/1503978816520
         * deviceAreaId : 3
         * deviceCateId : 6
         * instrumentId : 18
         * propertiesJson : []
         * status : 3
         */

        private String deviceAreaName;
        private String deviceCateName;
        private String instrumentName;
        private int id;
        private int companyId;
        private String name;
        private String model;
        private String photo;
        private int deviceAreaId;
        private int deviceCateId;
        private int instrumentId;
        private String propertiesJson;
        private int status;

        public String getDeviceAreaName() {
            return deviceAreaName;
        }

        public void setDeviceAreaName(String deviceAreaName) {
            this.deviceAreaName = deviceAreaName;
        }

        public String getDeviceCateName() {
            return deviceCateName;
        }

        public void setDeviceCateName(String deviceCateName) {
            this.deviceCateName = deviceCateName;
        }

        public String getInstrumentName() {
            return instrumentName;
        }

        public void setInstrumentName(String instrumentName) {
            this.instrumentName = instrumentName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCompanyId() {
            return companyId;
        }

        public void setCompanyId(int companyId) {
            this.companyId = companyId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public int getDeviceAreaId() {
            return deviceAreaId;
        }

        public void setDeviceAreaId(int deviceAreaId) {
            this.deviceAreaId = deviceAreaId;
        }

        public int getDeviceCateId() {
            return deviceCateId;
        }

        public void setDeviceCateId(int deviceCateId) {
            this.deviceCateId = deviceCateId;
        }

        public int getInstrumentId() {
            return instrumentId;
        }

        public void setInstrumentId(int instrumentId) {
            this.instrumentId = instrumentId;
        }

        public String getPropertiesJson() {
            return propertiesJson;
        }

        public void setPropertiesJson(String propertiesJson) {
            this.propertiesJson = propertiesJson;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
