/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.bean.mine.teammanagemet;

import com.ppandroid.im.bean.BaseBody;

import java.util.List;

/**
 * Created by yeqinfu on 2017/9/16.
 */

public class BN_Role extends BaseBody {

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
         * enName : administrator
         * name : 管理员
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
        private String enName;
        private String name;

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

        public String getEnName() {
            return enName;
        }

        public void setEnName(String enName) {
            this.enName = enName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
