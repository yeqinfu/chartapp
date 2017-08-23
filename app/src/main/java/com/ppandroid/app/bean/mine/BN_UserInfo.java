package com.ppandroid.app.bean.mine;

import com.ppandroid.im.bean.BaseBody;

/**
 * Created by yeqinfu on 2017/8/23.
 */

public class BN_UserInfo extends BaseBody {

    /**
     * message : {"id":2,"deleteStatus":false,"version":46,"createTime":null,"createBy":null,"createById":null,"lastModifyTime":"2017-08-23 16:27:00","lastModifyBy":null,"lastModifyById":null,"companyId":1,"departmentId":1,"roleId":1,"roleEnName":"administrator","roleName":"管理员","realName":"叶钦富","mobile":"18960045254","job":"主管","headPhoto":null,"sex":1,"provinceId":null,"cityId":null,"areaId":null,"areaName":null,"password":"fd58e211b187e2eca1374da6aef7a4e43c66056365caf306bf9f5c0b696cfefd","lastLoginTime":1503476820000,"lastLoginIp":"192.168.25.136"}
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
         * id : 2
         * deleteStatus : false
         * version : 46
         * createTime : null
         * createBy : null
         * createById : null
         * lastModifyTime : 2017-08-23 16:27:00
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
         * lastLoginTime : 1503476820000
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
        private Object areaName;
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

        public Object getAreaName() {
            return areaName;
        }

        public void setAreaName(Object areaName) {
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
}
