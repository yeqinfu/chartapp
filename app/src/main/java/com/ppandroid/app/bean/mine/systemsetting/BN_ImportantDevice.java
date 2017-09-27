/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.bean.mine.systemsetting;

import com.ppandroid.im.bean.BaseBody;

import java.util.List;

/**
 * Created by yeqinfu on 2017/8/25.
 */

public class BN_ImportantDevice extends BaseBody {


    private List<MessageBean> message;

    public List<MessageBean> getMessage() {
        return message;
    }

    public void setMessage(List<MessageBean> message) {
        this.message = message;
    }

    public static class MessageBean {
        /**
         * id : 1
         * companyId : 0
         * name : CH01-1离心式制冷机
         * model : CH01-1
         * photo : /device/photo/aa.jpg
         * deviceAreaId : 1
         * deviceCateId : 2
         * instrumentId : null
         * propertiesJson : {"安装位置":"动力一楼","装机容量":"1275kw","规格型号":"YKR2R2K 45DGG/EE22","设备位号":"CH01-1离心式制冷机","设备名称":"约克离心式制冷机","馈线名称":"621馈线"}
         * templateName : null
         * status : 3
         * deviceCateName : 制冷机
         * template : false
         */

        private int id;
        private int companyId;
        private String name;
        private String model;
        private String photo;
        private int deviceAreaId;
        private int deviceCateId;
        private Object instrumentId;
        private String propertiesJson;
        private Object templateName;
        private int status;
        private String deviceCateName;
        private boolean template;

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

        public Object getInstrumentId() {
            return instrumentId;
        }

        public void setInstrumentId(Object instrumentId) {
            this.instrumentId = instrumentId;
        }

        public String getPropertiesJson() {
            return propertiesJson;
        }

        public void setPropertiesJson(String propertiesJson) {
            this.propertiesJson = propertiesJson;
        }

        public Object getTemplateName() {
            return templateName;
        }

        public void setTemplateName(Object templateName) {
            this.templateName = templateName;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getDeviceCateName() {
            return deviceCateName;
        }

        public void setDeviceCateName(String deviceCateName) {
            this.deviceCateName = deviceCateName;
        }

        public boolean isTemplate() {
            return template;
        }

        public void setTemplate(boolean template) {
            this.template = template;
        }
    }
}
