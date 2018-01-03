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
     * message : {"deviceEntityList":[{"id":4,"deleteStatus":false,"version":1621,"createTime":null,"createBy":null,"createById":null,"lastModifyTime":"2017-12-26 10:10:00","lastModifyBy":null,"lastModifyById":null,"companyId":1,"name":"42","model":"CH01-22","photo":"upload/device/1509531463201","deviceAreaId":5,"deviceCateId":2,"instrumentId":1,"propertiesJson":null,"templateName":null,"status":5,"energyClassificationId":1,"template":false,"statusString":"通讯故障"},{"id":11,"deleteStatus":false,"version":6048,"createTime":"2017-09-07 09:34:13","createBy":null,"createById":null,"lastModifyTime":"2018-01-02 11:42:00","lastModifyBy":null,"lastModifyById":null,"companyId":1,"name":"时光机","model":"编号89757","photo":"upload/device/1505447770164","deviceAreaId":3,"deviceCateId":26,"instrumentId":47,"propertiesJson":"{\"list\":[{\"key\":\"数学\",\"value\":\"98\"},{\"key\":\"载人\",\"value\":\"2\"}]}","templateName":null,"status":5,"energyClassificationId":1,"template":false,"statusString":"通讯故障"},{"id":13,"deleteStatus":false,"version":5560,"createTime":"2017-09-11 15:41:31","createBy":null,"createById":null,"lastModifyTime":"2017-12-08 12:04:03","lastModifyBy":null,"lastModifyById":null,"companyId":1,"name":"飞机","model":"1369","photo":"upload/device/1505447762007","deviceAreaId":8,"deviceCateId":26,"instrumentId":41,"propertiesJson":"{\"list\":[]}","templateName":null,"status":5,"energyClassificationId":1,"template":false,"statusString":"通讯故障"},{"id":16,"deleteStatus":false,"version":1904,"createTime":"2017-09-21 10:41:11","createBy":null,"createById":null,"lastModifyTime":"2017-12-26 10:10:01","lastModifyBy":null,"lastModifyById":null,"companyId":1,"name":"屠龙刀","model":"谢逊一号","photo":"upload/device/1505961671063","deviceAreaId":8,"deviceCateId":26,"instrumentId":2,"propertiesJson":"{\"list\":[{\"key\":\"屠龙数量\",\"value\":\"777\"}]}","templateName":null,"status":5,"energyClassificationId":1,"template":false,"statusString":"通讯故障"}],"failueNumber":4,"score":60,"waterDeviceEntityList":[{"id":20,"deleteStatus":false,"version":15114,"createTime":null,"createBy":null,"createById":null,"lastModifyTime":"2018-01-03 10:53:00","lastModifyBy":null,"lastModifyById":null,"companyId":1,"name":"水表CH01-4","model":"CH01","photo":null,"deviceAreaId":12,"deviceCateId":27,"instrumentId":46,"propertiesJson":null,"templateName":null,"status":5,"energyClassificationId":2,"template":false,"statusString":"通讯故障"}],"waterFailueNumber":1}
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
         * deviceEntityList : [{"id":4,"deleteStatus":false,"version":1621,"createTime":null,"createBy":null,"createById":null,"lastModifyTime":"2017-12-26 10:10:00","lastModifyBy":null,"lastModifyById":null,"companyId":1,"name":"42","model":"CH01-22","photo":"upload/device/1509531463201","deviceAreaId":5,"deviceCateId":2,"instrumentId":1,"propertiesJson":null,"templateName":null,"status":5,"energyClassificationId":1,"template":false,"statusString":"通讯故障"},{"id":11,"deleteStatus":false,"version":6048,"createTime":"2017-09-07 09:34:13","createBy":null,"createById":null,"lastModifyTime":"2018-01-02 11:42:00","lastModifyBy":null,"lastModifyById":null,"companyId":1,"name":"时光机","model":"编号89757","photo":"upload/device/1505447770164","deviceAreaId":3,"deviceCateId":26,"instrumentId":47,"propertiesJson":"{\"list\":[{\"key\":\"数学\",\"value\":\"98\"},{\"key\":\"载人\",\"value\":\"2\"}]}","templateName":null,"status":5,"energyClassificationId":1,"template":false,"statusString":"通讯故障"},{"id":13,"deleteStatus":false,"version":5560,"createTime":"2017-09-11 15:41:31","createBy":null,"createById":null,"lastModifyTime":"2017-12-08 12:04:03","lastModifyBy":null,"lastModifyById":null,"companyId":1,"name":"飞机","model":"1369","photo":"upload/device/1505447762007","deviceAreaId":8,"deviceCateId":26,"instrumentId":41,"propertiesJson":"{\"list\":[]}","templateName":null,"status":5,"energyClassificationId":1,"template":false,"statusString":"通讯故障"},{"id":16,"deleteStatus":false,"version":1904,"createTime":"2017-09-21 10:41:11","createBy":null,"createById":null,"lastModifyTime":"2017-12-26 10:10:01","lastModifyBy":null,"lastModifyById":null,"companyId":1,"name":"屠龙刀","model":"谢逊一号","photo":"upload/device/1505961671063","deviceAreaId":8,"deviceCateId":26,"instrumentId":2,"propertiesJson":"{\"list\":[{\"key\":\"屠龙数量\",\"value\":\"777\"}]}","templateName":null,"status":5,"energyClassificationId":1,"template":false,"statusString":"通讯故障"}]
         * failueNumber : 4
         * score : 60
         * waterDeviceEntityList : [{"id":20,"deleteStatus":false,"version":15114,"createTime":null,"createBy":null,"createById":null,"lastModifyTime":"2018-01-03 10:53:00","lastModifyBy":null,"lastModifyById":null,"companyId":1,"name":"水表CH01-4","model":"CH01","photo":null,"deviceAreaId":12,"deviceCateId":27,"instrumentId":46,"propertiesJson":null,"templateName":null,"status":5,"energyClassificationId":2,"template":false,"statusString":"通讯故障"}]
         * waterFailueNumber : 1
         */

        private int failueNumber;
        private int score;
        private int waterFailueNumber;
        private List<DeviceEntityListBean> deviceEntityList;
        private List<WaterDeviceEntityListBean> waterDeviceEntityList;

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

        public static class DeviceEntityListBean {
            /**
             * id : 4
             * deleteStatus : false
             * version : 1621
             * createTime : null
             * createBy : null
             * createById : null
             * lastModifyTime : 2017-12-26 10:10:00
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
             * energyClassificationId : 1
             * template : false
             * statusString : 通讯故障
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
            private int energyClassificationId;
            private boolean template;
            private String statusString;

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

            public int getEnergyClassificationId() {
                return energyClassificationId;
            }

            public void setEnergyClassificationId(int energyClassificationId) {
                this.energyClassificationId = energyClassificationId;
            }

            public boolean isTemplate() {
                return template;
            }

            public void setTemplate(boolean template) {
                this.template = template;
            }

            public String getStatusString() {
                return statusString;
            }

            public void setStatusString(String statusString) {
                this.statusString = statusString;
            }
        }

        public static class WaterDeviceEntityListBean {
            /**
             * id : 20
             * deleteStatus : false
             * version : 15114
             * createTime : null
             * createBy : null
             * createById : null
             * lastModifyTime : 2018-01-03 10:53:00
             * lastModifyBy : null
             * lastModifyById : null
             * companyId : 1
             * name : 水表CH01-4
             * model : CH01
             * photo : null
             * deviceAreaId : 12
             * deviceCateId : 27
             * instrumentId : 46
             * propertiesJson : null
             * templateName : null
             * status : 5
             * energyClassificationId : 2
             * template : false
             * statusString : 通讯故障
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
            private Object photo;
            private int deviceAreaId;
            private int deviceCateId;
            private int instrumentId;
            private Object propertiesJson;
            private Object templateName;
            private int status;
            private int energyClassificationId;
            private boolean template;
            private String statusString;

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

            public Object getPhoto() {
                return photo;
            }

            public void setPhoto(Object photo) {
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

            public boolean isTemplate() {
                return template;
            }

            public void setTemplate(boolean template) {
                this.template = template;
            }

            public String getStatusString() {
                return statusString;
            }

            public void setStatusString(String statusString) {
                this.statusString = statusString;
            }
        }
    }
}
