/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.bean.mine;

import com.ppandroid.im.bean.BaseBody;

/**
 * Created by yeqinfu on 2017/8/23.
 */

public class BN_UserInfo extends BaseBody {


    /**
     * message : {"employeeEntity":{"id":2,"deleteStatus":false,"version":49,"createTime":null,"createBy":null,"createById":null,"lastModifyTime":"2017-08-24 14:16:59","lastModifyBy":null,"lastModifyById":null,"companyId":1,"departmentId":1,"roleId":1,"roleEnName":"administrator","roleName":"管理员","realName":"叶钦富","mobile":"18960045254","job":"主管","headPhoto":null,"sex":1,"provinceId":null,"cityId":null,"areaId":null,"areaName":null,"password":"fd58e211b187e2eca1374da6aef7a4e43c66056365caf306bf9f5c0b696cfefd","lastLoginTime":1503555419000,"lastLoginIp":"192.168.25.136"},"companyEntity":{"id":1,"deleteStatus":false,"version":0,"createTime":null,"createBy":null,"createById":null,"lastModifyTime":null,"lastModifyBy":null,"lastModifyById":null,"name":"厦门市XXXXX责任有限公司","industryType":"制造业","provinceId":4523253,"cityId":4523269,"areaId":4523271,"areaName":"思明区"}}
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
         * employeeEntity : {"id":2,"deleteStatus":false,"version":49,"createTime":null,"createBy":null,"createById":null,"lastModifyTime":"2017-08-24 14:16:59","lastModifyBy":null,"lastModifyById":null,"companyId":1,"departmentId":1,"roleId":1,"roleEnName":"administrator","roleName":"管理员","realName":"叶钦富","mobile":"18960045254","job":"主管","headPhoto":null,"sex":1,"provinceId":null,"cityId":null,"areaId":null,"areaName":null,"password":"fd58e211b187e2eca1374da6aef7a4e43c66056365caf306bf9f5c0b696cfefd","lastLoginTime":1503555419000,"lastLoginIp":"192.168.25.136"}
         * companyEntity : {"id":1,"deleteStatus":false,"version":0,"createTime":null,"createBy":null,"createById":null,"lastModifyTime":null,"lastModifyBy":null,"lastModifyById":null,"name":"厦门市XXXXX责任有限公司","industryType":"制造业","provinceId":4523253,"cityId":4523269,"areaId":4523271,"areaName":"思明区"}
         */

        private EmployeeEntityBean employeeEntity;
        private CompanyEntityBean companyEntity;

        public EmployeeEntityBean getEmployeeEntity() {
            return employeeEntity;
        }

        public void setEmployeeEntity(EmployeeEntityBean employeeEntity) {
            this.employeeEntity = employeeEntity;
        }

        public CompanyEntityBean getCompanyEntity() {
            return companyEntity;
        }

        public void setCompanyEntity(CompanyEntityBean companyEntity) {
            this.companyEntity = companyEntity;
        }

        public static class EmployeeEntityBean {
            /**
             * id : 2
             * deleteStatus : false
             * version : 49
             * createTime : null
             * createBy : null
             * createById : null
             * lastModifyTime : 2017-08-24 14:16:59
             * lastModifyBy : null
             * lastModifyById : null
             * companyId : 1
             * departmentId : 1
             * roleId : 1
             * roleEnName : administrator
             * roleName : 管理员
             * realName : 叶钦富
             * mobile : 18960045254
             * job : 主管
             * headPhoto : null
             * sex : 1
             * provinceId : null
             * cityId : null
             * areaId : null
             * areaName : null
             * password : fd58e211b187e2eca1374da6aef7a4e43c66056365caf306bf9f5c0b696cfefd
             * lastLoginTime : 1503555419000
             * lastLoginIp : 192.168.25.136
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
            private int departmentId;
            private int roleId;
            private String roleEnName;
            private String roleName;
            private String realName;
            private String mobile;
            private String job;
            private String headPhoto;
            private int sex;
            private Object provinceId;
            private Object cityId;
            private Object areaId;
            private String areaName;
            private String password;
            private long lastLoginTime;
            private String lastLoginIp;

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

            public int getDepartmentId() {
                return departmentId;
            }

            public void setDepartmentId(int departmentId) {
                this.departmentId = departmentId;
            }

            public int getRoleId() {
                return roleId;
            }

            public void setRoleId(int roleId) {
                this.roleId = roleId;
            }

            public String getRoleEnName() {
                return roleEnName;
            }

            public void setRoleEnName(String roleEnName) {
                this.roleEnName = roleEnName;
            }

            public String getRoleName() {
                return roleName;
            }

            public void setRoleName(String roleName) {
                this.roleName = roleName;
            }

            public String getRealName() {
                return realName;
            }

            public void setRealName(String realName) {
                this.realName = realName;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public String getJob() {
                return job;
            }

            public void setJob(String job) {
                this.job = job;
            }

            public String getHeadPhoto() {
                return headPhoto;
            }

            public void setHeadPhoto(String headPhoto) {
                this.headPhoto = headPhoto;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public Object getProvinceId() {
                return provinceId;
            }

            public void setProvinceId(Object provinceId) {
                this.provinceId = provinceId;
            }

            public Object getCityId() {
                return cityId;
            }

            public void setCityId(Object cityId) {
                this.cityId = cityId;
            }

            public Object getAreaId() {
                return areaId;
            }

            public void setAreaId(Object areaId) {
                this.areaId = areaId;
            }

            public String getAreaName() {
                return areaName;
            }

            public void setAreaName(String areaName) {
                this.areaName = areaName;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public long getLastLoginTime() {
                return lastLoginTime;
            }

            public void setLastLoginTime(long lastLoginTime) {
                this.lastLoginTime = lastLoginTime;
            }

            public String getLastLoginIp() {
                return lastLoginIp;
            }

            public void setLastLoginIp(String lastLoginIp) {
                this.lastLoginIp = lastLoginIp;
            }
        }

        public static class CompanyEntityBean {
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
             * name : 厦门市XXXXX责任有限公司
             * industryType : 制造业
             * provinceId : 4523253
             * cityId : 4523269
             * areaId : 4523271
             * areaName : 思明区
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
            private String name;
            private String industryType;
            private int provinceId;
            private int cityId;
            private int areaId;
            private String areaName;
            private String serviceProvider;

            public String getServiceProvider() {
                return serviceProvider;
            }

            public void setServiceProvider(String serviceProvider) {
                this.serviceProvider = serviceProvider;
            }

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

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getIndustryType() {
                return industryType;
            }

            public void setIndustryType(String industryType) {
                this.industryType = industryType;
            }

            public int getProvinceId() {
                return provinceId;
            }

            public void setProvinceId(int provinceId) {
                this.provinceId = provinceId;
            }

            public int getCityId() {
                return cityId;
            }

            public void setCityId(int cityId) {
                this.cityId = cityId;
            }

            public int getAreaId() {
                return areaId;
            }

            public void setAreaId(int areaId) {
                this.areaId = areaId;
            }

            public String getAreaName() {
                return areaName;
            }

            public void setAreaName(String areaName) {
                this.areaName = areaName;
            }
        }
    }
}
