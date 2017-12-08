/*
 * Created by yeqinfu on 17-12-7 上午11:07
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.bean.news;

import com.ppandroid.im.bean.BaseBody;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yeqinfu on 2017/12/7.
 */

public class BN_SecurityCenter extends BaseBody {

    /**
     * message : {"deviceEntityList":[{"id":4,"deleteStatus":false,"version":949,"createTime":null,"createBy":null,"createById":null,"lastModifyTime":"2017-12-05 14:24:35","lastModifyBy":null,"lastModifyById":null,"companyId":1,"name":"42","model":"CH01-22","photo":"upload/device/1509531463201","deviceAreaId":5,"deviceCateId":2,"instrumentId":1,"propertiesJson":null,"templateName":null,"status":5,"statusString":"通讯故障","template":false},{"id":11,"deleteStatus":false,"version":6028,"createTime":"2017-09-07 09:34:13","createBy":null,"createById":null,"lastModifyTime":"2017-12-05 14:25:40","lastModifyBy":null,"lastModifyById":null,"companyId":1,"name":"时光机","model":"编号89757","photo":"upload/device/1505447770164","deviceAreaId":3,"deviceCateId":26,"instrumentId":40,"propertiesJson":"{\"list\":[{\"key\":\"数学\",\"value\":\"98\"},{\"key\":\"载人\",\"value\":\"2\"}]}","templateName":null,"status":5,"statusString":"通讯故障","template":false}],"failueNumber":2,"score":60}
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
         * deviceEntityList : [{"id":4,"deleteStatus":false,"version":949,"createTime":null,"createBy":null,"createById":null,"lastModifyTime":"2017-12-05 14:24:35","lastModifyBy":null,"lastModifyById":null,"companyId":1,"name":"42","model":"CH01-22","photo":"upload/device/1509531463201","deviceAreaId":5,"deviceCateId":2,"instrumentId":1,"propertiesJson":null,"templateName":null,"status":5,"statusString":"通讯故障","template":false},{"id":11,"deleteStatus":false,"version":6028,"createTime":"2017-09-07 09:34:13","createBy":null,"createById":null,"lastModifyTime":"2017-12-05 14:25:40","lastModifyBy":null,"lastModifyById":null,"companyId":1,"name":"时光机","model":"编号89757","photo":"upload/device/1505447770164","deviceAreaId":3,"deviceCateId":26,"instrumentId":40,"propertiesJson":"{\"list\":[{\"key\":\"数学\",\"value\":\"98\"},{\"key\":\"载人\",\"value\":\"2\"}]}","templateName":null,"status":5,"statusString":"通讯故障","template":false}]
         * failueNumber : 2
         * score : 60
         */

        private int failueNumber;
        private int score;
        private List<DeviceEntityListBean> deviceEntityList;

        public int getFailueNumber() {
            return failueNumber;
        }

        public void setFailueNumber(int failueNumber) {
            this.failueNumber = failueNumber;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public List<DeviceEntityListBean> getDeviceEntityList() {
            return deviceEntityList;
        }

        public void setDeviceEntityList(List<DeviceEntityListBean> deviceEntityList) {
            this.deviceEntityList = deviceEntityList;
        }

        public static class DeviceEntityListBean implements Serializable{
            /**
             * id : 4
             * deleteStatus : false
             * version : 949
             * createTime : null
             * createBy : null
             * createById : null
             * lastModifyTime : 2017-12-05 14:24:35
             * lastModifyBy : null
             * lastModifyById : null
             * companyId : 1
             * name : 42
             * model : CH01-22
             * photo : upload/device/1509531463201
             * deviceAreaId : 5
             * deviceCateId : 2
             * instrumentId : 1
             * propertiesJson : null
             * templateName : null
             * status : 5
             * statusString : 通讯故障
             * template : false
             */

            private int id;
            private boolean deleteStatus;
            private int version;
            private Object createTime;
            private Object createBy;
            private Object createById;
            private String lastModifyTime;
            private Object lastModifyBy;
            private Object lastModifyById;
            private int companyId;
            private String name;
            private String model;
            private String photo;
            private int deviceAreaId;
            private int deviceCateId;
            private int instrumentId;
            private Object propertiesJson;
            private Object templateName;
            private int status;
            private String statusString;
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

            public String getLastModifyTime() {
                return lastModifyTime;
            }

            public void setLastModifyTime(String lastModifyTime) {
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

            public int getInstrumentId() {
                return instrumentId;
            }

            public void setInstrumentId(int instrumentId) {
                this.instrumentId = instrumentId;
            }

            public Object getPropertiesJson() {
                return propertiesJson;
            }

            public void setPropertiesJson(Object propertiesJson) {
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

            public String getStatusString() {
                return statusString;
            }

            public void setStatusString(String statusString) {
                this.statusString = statusString;
            }

            public boolean isTemplate() {
                return template;
            }

            public void setTemplate(boolean template) {
                this.template = template;
            }
        }
    }
}
