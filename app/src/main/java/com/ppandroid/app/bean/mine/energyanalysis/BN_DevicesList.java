package com.ppandroid.app.bean.mine.energyanalysis;

import com.ppandroid.im.bean.BaseBody;

import java.util.List;

/**
 * Created by yeqinfu on 2017/9/16.
 */

public class BN_DevicesList extends BaseBody {

    private List<MessageBean> message;

    public List<MessageBean> getMessage() {
        return message;
    }

    public void setMessage(List<MessageBean> message) {
        this.message = message;
    }

    public static class MessageBean {
        /**
         * id : 3
         * deleteStatus : false
         * version : 1
         * createTime : null
         * createBy : null
         * createById : null
         * lastModifyTime : 2017-09-14 14:24:47
         * lastModifyBy : null
         * lastModifyById : null
         * companyId : 1
         * name : CH01-1离心式制冷机
         * model : CH01-1
         * photo : upload/device/1505370286580
         * deviceAreaId : 5
         * deviceCateId : 2
         * instrumentId : 1
         * propertiesJson : {"list":[]}
         * templateName : null
         * status : 3
         * template : false
         */

        private int id;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
