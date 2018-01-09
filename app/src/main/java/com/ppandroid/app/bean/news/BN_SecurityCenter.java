/*
 * Created by yeqinfu on 17-12-7 上午11:07
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.bean.news;

import com.ppandroid.im.bean.BaseBody;

import java.util.List;

/**
 * Created by yeqinfu on 2017/12/7.
 */

public class BN_SecurityCenter extends BaseBody {


    /**
     * message : {"deviceEntityList":[{"id":17,"deleteStatus":false,"version":10,"createTime":"2017-12-05 14:17:09","createBy":null,"createById":null,"lastModifyTime":"2017-12-26 16:57:00","lastModifyBy":null,"lastModifyById":null,"companyId":2,"name":"CH01-3约克离心式制冷机","model":"CH01-3","photo":"upload/device/1514277509180","deviceAreaId":1,"deviceCateId":2,"instrumentId":31,"propertiesJson":null,"templateName":null,"status":5,"energyClassificationId":1,"statusString":"通讯故障","template":false}],"failueNumber":1,"score":60,"waterDeviceEntityList":[{"id":39,"deleteStatus":false,"version":7,"createTime":"2018-01-05 10:49:42","createBy":null,"createById":null,"lastModifyTime":"2018-01-09 14:17:00","lastModifyBy":null,"lastModifyById":null,"companyId":2,"name":"CH01水表1","model":"CH01","photo":"upload/device/1515120582470","deviceAreaId":6,"deviceCateId":24,"instrumentId":44,"propertiesJson":"{\"list\":[]}","templateName":null,"status":5,"energyClassificationId":2,"statusString":"通讯故障","template":false}],"waterFailueNumber":1,"temperatureDeviceEntityList":[{"id":42,"deleteStatus":false,"version":10961,"createTime":"2018-01-05 15:38:33","createBy":null,"createById":null,"lastModifyTime":"2018-01-09 12:05:01","lastModifyBy":null,"lastModifyById":null,"companyId":2,"name":"温湿表1","model":"CH02-w01","photo":"upload/device/1515393447704","deviceAreaId":10,"deviceCateId":27,"instrumentId":50,"propertiesJson":"{\"list\":[]}","templateName":null,"status":5,"energyClassificationId":3,"statusString":"通讯故障","template":false}],"temperatureFailueNumber":1}
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
         * deviceEntityList : [{"id":17,"deleteStatus":false,"version":10,"createTime":"2017-12-05 14:17:09","createBy":null,"createById":null,"lastModifyTime":"2017-12-26 16:57:00","lastModifyBy":null,"lastModifyById":null,"companyId":2,"name":"CH01-3约克离心式制冷机","model":"CH01-3","photo":"upload/device/1514277509180","deviceAreaId":1,"deviceCateId":2,"instrumentId":31,"propertiesJson":null,"templateName":null,"status":5,"energyClassificationId":1,"statusString":"通讯故障","template":false}]
         * failueNumber : 1
         * score : 60
         * waterDeviceEntityList : [{"id":39,"deleteStatus":false,"version":7,"createTime":"2018-01-05 10:49:42","createBy":null,"createById":null,"lastModifyTime":"2018-01-09 14:17:00","lastModifyBy":null,"lastModifyById":null,"companyId":2,"name":"CH01水表1","model":"CH01","photo":"upload/device/1515120582470","deviceAreaId":6,"deviceCateId":24,"instrumentId":44,"propertiesJson":"{\"list\":[]}","templateName":null,"status":5,"energyClassificationId":2,"statusString":"通讯故障","template":false}]
         * waterFailueNumber : 1
         * temperatureDeviceEntityList : [{"id":42,"deleteStatus":false,"version":10961,"createTime":"2018-01-05 15:38:33","createBy":null,"createById":null,"lastModifyTime":"2018-01-09 12:05:01","lastModifyBy":null,"lastModifyById":null,"companyId":2,"name":"温湿表1","model":"CH02-w01","photo":"upload/device/1515393447704","deviceAreaId":10,"deviceCateId":27,"instrumentId":50,"propertiesJson":"{\"list\":[]}","templateName":null,"status":5,"energyClassificationId":3,"statusString":"通讯故障","template":false}]
         * temperatureFailueNumber : 1
         */

        private int failueNumber;
        private int score;
        private int waterFailueNumber;
        private int temperatureFailueNumber;
        private List<DeviceEntityListBean> deviceEntityList;
        private List<WaterDeviceEntityListBean> waterDeviceEntityList;
        private List<TemperatureDeviceEntityListBean> temperatureDeviceEntityList;

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

        public int getWaterFailueNumber() {
            return waterFailueNumber;
        }

        public void setWaterFailueNumber(int waterFailueNumber) {
            this.waterFailueNumber = waterFailueNumber;
        }

        public int getTemperatureFailueNumber() {
            return temperatureFailueNumber;
        }

        public void setTemperatureFailueNumber(int temperatureFailueNumber) {
            this.temperatureFailueNumber = temperatureFailueNumber;
        }

        public List<DeviceEntityListBean> getDeviceEntityList() {
            return deviceEntityList;
        }

        public void setDeviceEntityList(List<DeviceEntityListBean> deviceEntityList) {
            this.deviceEntityList = deviceEntityList;
        }

        public List<WaterDeviceEntityListBean> getWaterDeviceEntityList() {
            return waterDeviceEntityList;
        }

        public void setWaterDeviceEntityList(List<WaterDeviceEntityListBean> waterDeviceEntityList) {
            this.waterDeviceEntityList = waterDeviceEntityList;
        }

        public List<TemperatureDeviceEntityListBean> getTemperatureDeviceEntityList() {
            return temperatureDeviceEntityList;
        }

        public void setTemperatureDeviceEntityList(List<TemperatureDeviceEntityListBean> temperatureDeviceEntityList) {
            this.temperatureDeviceEntityList = temperatureDeviceEntityList;
        }

        public static class DeviceEntityListBean {
            /**
             * id : 17
             * deleteStatus : false
             * version : 10
             * createTime : 2017-12-05 14:17:09
             * createBy : null
             * createById : null
             * lastModifyTime : 2017-12-26 16:57:00
             * lastModifyBy : null
             * lastModifyById : null
             * companyId : 2
             * name : CH01-3约克离心式制冷机
             * model : CH01-3
             * photo : upload/device/1514277509180
             * deviceAreaId : 1
             * deviceCateId : 2
             * instrumentId : 31
             * propertiesJson : null
             * templateName : null
             * status : 5
             * energyClassificationId : 1
             * statusString : 通讯故障
             * template : false
             */

            private int id;
            private boolean deleteStatus;
            private int version;
            private String createTime;
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
            private int energyClassificationId;
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

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
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

            public int getEnergyClassificationId() {
                return energyClassificationId;
            }

            public void setEnergyClassificationId(int energyClassificationId) {
                this.energyClassificationId = energyClassificationId;
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

        public static class WaterDeviceEntityListBean {
            /**
             * id : 39
             * deleteStatus : false
             * version : 7
             * createTime : 2018-01-05 10:49:42
             * createBy : null
             * createById : null
             * lastModifyTime : 2018-01-09 14:17:00
             * lastModifyBy : null
             * lastModifyById : null
             * companyId : 2
             * name : CH01水表1
             * model : CH01
             * photo : upload/device/1515120582470
             * deviceAreaId : 6
             * deviceCateId : 24
             * instrumentId : 44
             * propertiesJson : {"list":[]}
             * templateName : null
             * status : 5
             * energyClassificationId : 2
             * statusString : 通讯故障
             * template : false
             */

            private int id;
            private boolean deleteStatus;
            private int version;
            private String createTime;
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
            private String propertiesJson;
            private Object templateName;
            private int status;
            private int energyClassificationId;
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

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
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

            public int getEnergyClassificationId() {
                return energyClassificationId;
            }

            public void setEnergyClassificationId(int energyClassificationId) {
                this.energyClassificationId = energyClassificationId;
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

        public static class TemperatureDeviceEntityListBean {
            /**
             * id : 42
             * deleteStatus : false
             * version : 10961
             * createTime : 2018-01-05 15:38:33
             * createBy : null
             * createById : null
             * lastModifyTime : 2018-01-09 12:05:01
             * lastModifyBy : null
             * lastModifyById : null
             * companyId : 2
             * name : 温湿表1
             * model : CH02-w01
             * photo : upload/device/1515393447704
             * deviceAreaId : 10
             * deviceCateId : 27
             * instrumentId : 50
             * propertiesJson : {"list":[]}
             * templateName : null
             * status : 5
             * energyClassificationId : 3
             * statusString : 通讯故障
             * template : false
             */

            private int id;
            private boolean deleteStatus;
            private int version;
            private String createTime;
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
            private String propertiesJson;
            private Object templateName;
            private int status;
            private int energyClassificationId;
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

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
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

            public int getEnergyClassificationId() {
                return energyClassificationId;
            }

            public void setEnergyClassificationId(int energyClassificationId) {
                this.energyClassificationId = energyClassificationId;
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
