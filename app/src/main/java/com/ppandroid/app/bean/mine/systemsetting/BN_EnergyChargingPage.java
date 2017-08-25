package com.ppandroid.app.bean.mine.systemsetting;

import com.ppandroid.im.bean.BaseBody;

import java.util.List;

/**
 * Created by yeqinfu on 2017/8/25.
 */

public class BN_EnergyChargingPage extends BaseBody {

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
         * code : 001
         * classificationId : 1
         * chargingType : 1
         * chargingJson : 0.68
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
        private String code;
        private int classificationId;
        private int chargingType;
        private String chargingJson;

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

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public int getClassificationId() {
            return classificationId;
        }

        public void setClassificationId(int classificationId) {
            this.classificationId = classificationId;
        }

        public int getChargingType() {
            return chargingType;
        }

        public void setChargingType(int chargingType) {
            this.chargingType = chargingType;
        }

        public String getChargingJson() {
            return chargingJson;
        }

        public void setChargingJson(String chargingJson) {
            this.chargingJson = chargingJson;
        }
    }
}
