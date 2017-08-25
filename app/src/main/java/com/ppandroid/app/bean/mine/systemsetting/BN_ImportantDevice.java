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
         * deleteStatus : false
         * version : 0
         * createTime : null
         * createBy : null
         * createById : null
         * lastModifyTime : null
         * lastModifyBy : null
         * lastModifyById : null
         * companyId : 1
         * name : CH01-1离心式制冷机
         * model : CH01-1
         * photo : /device/photo/aa.jpg
         * deviceAreaId : 1
         * deviceCateId : 2
         * instrumentId : null
         * propertiesJson : {"安装位置":"动力一楼","装机容量":"1275kw","规格型号":"YKR2R2K 45DGG/EE22","设备位号":"CH01-1离心式制冷机","设备名称":"约克离心式制冷机","馈线名称":"621馈线"}
         * templateName : null
         * status : 3
         * template : false
         */

        private int id;
        private boolean deleteStatus;
        private int version;
        private Object createTime;
        private Object createBy;
        private Object createById;
        private Object lastModifyTime;
        private Object lastModifyBy;
        private Object lastModifyById;
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
        private boolean template;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean isDeleteStatus() {
            return deleteStatus;
        }

        public void setDeleteStatus(boolean deleteStatus) {
            this.deleteStatus = deleteStatus;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        public Object getCreateBy() {
            return createBy;
        }

        public void setCreateBy(Object createBy) {
            this.createBy = createBy;
        }

        public Object getCreateById() {
            return createById;
        }

        public void setCreateById(Object createById) {
            this.createById = createById;
        }

        public Object getLastModifyTime() {
            return lastModifyTime;
        }

        public void setLastModifyTime(Object lastModifyTime) {
            this.lastModifyTime = lastModifyTime;
        }

        public Object getLastModifyBy() {
            return lastModifyBy;
        }

        public void setLastModifyBy(Object lastModifyBy) {
            this.lastModifyBy = lastModifyBy;
        }

        public Object getLastModifyById() {
            return lastModifyById;
        }

        public void setLastModifyById(Object lastModifyById) {
            this.lastModifyById = lastModifyById;
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

        public boolean isTemplate() {
            return template;
        }

        public void setTemplate(boolean template) {
            this.template = template;
        }
    }
}
