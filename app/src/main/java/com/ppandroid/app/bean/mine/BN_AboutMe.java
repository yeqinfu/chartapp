package com.ppandroid.app.bean.mine;

import com.ppandroid.im.bean.BaseBody;

/**
 * Created by yeqinfu on 2017/9/8.
 */

public class BN_AboutMe extends BaseBody {


    /**
     * message : {"id":1,"deleteStatus":false,"version":0,"createTime":null,"createBy":null,"createById":null,"lastModifyTime":null,"lastModifyBy":null,"lastModifyById":null,"text":"关于XXXXXXXXXXXXXXX","companyName":"厦门节效通网络科技有限公司","companyAddress":"厦门市思明区软件园二期观日路36号之一101单元","companyPhone":"0592-5795929","companyWebsite":"www.jiexiaotong.com"}
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
         * id : 1
         * deleteStatus : false
         * version : 0
         * createTime : null
         * createBy : null
         * createById : null
         * lastModifyTime : null
         * lastModifyBy : null
         * lastModifyById : null
         * text : 关于XXXXXXXXXXXXXXX
         * companyName : 厦门节效通网络科技有限公司
         * companyAddress : 厦门市思明区软件园二期观日路36号之一101单元
         * companyPhone : 0592-5795929
         * companyWebsite : www.jiexiaotong.com
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
        private String text;
        private String companyName;
        private String companyAddress;
        private String companyPhone;
        private String companyWebsite;

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

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getCompanyAddress() {
            return companyAddress;
        }

        public void setCompanyAddress(String companyAddress) {
            this.companyAddress = companyAddress;
        }

        public String getCompanyPhone() {
            return companyPhone;
        }

        public void setCompanyPhone(String companyPhone) {
            this.companyPhone = companyPhone;
        }

        public String getCompanyWebsite() {
            return companyWebsite;
        }

        public void setCompanyWebsite(String companyWebsite) {
            this.companyWebsite = companyWebsite;
        }
    }
}
