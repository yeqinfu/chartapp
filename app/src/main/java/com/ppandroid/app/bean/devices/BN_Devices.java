package com.ppandroid.app.bean.devices;

import com.ppandroid.im.bean.BaseBody;

import java.util.List;

/**
 * Created by yeqinfu on 2017/8/22.
 */

public class BN_Devices extends BaseBody {

    /**
     * message : {"deviceCateList":[{"name":"制冷机","deviceList":[{"id":1,"photo":"/device/photo/aa.jpg","name":"CH01-1离心式制冷机","model":"CH01-1","statusString":"运行"},{"id":2,"photo":"/device/photo/bb.jpg","name":"CH01-2离心式制冷机","model":"CH01-2","statusString":"运行"}]},{"name":"高压冷却泵","deviceList":[]},{"name":"冷冻泵","deviceList":[]},{"name":"冷却塔","deviceList":[]},{"name":"冷干机","deviceList":[]},{"name":"高压式离心式制冷机","deviceList":[]},{"name":"高压离心式制冷机","deviceList":[]},{"name":"高压冷却水泵","deviceList":[]},{"name":"高压寿力螺杆空压机","deviceList":[]},{"name":"空调系统冷却泵","deviceList":[]}]}
     */

    private MessageBean message;

    public MessageBean getMessage() {
        return message;
    }

    public void setMessage(MessageBean message) {
        this.message = message;
    }

    public static class MessageBean {
        private List<DeviceCateListBean> deviceCateList;

        public List<DeviceCateListBean> getDeviceCateList() {
            return deviceCateList;
        }

        public void setDeviceCateList(List<DeviceCateListBean> deviceCateList) {
            this.deviceCateList = deviceCateList;
        }

        public static class DeviceCateListBean {
            private boolean isOpen=false;
            /**
             * name : 制冷机
             * deviceList : [{"id":1,"photo":"/device/photo/aa.jpg","name":"CH01-1离心式制冷机","model":"CH01-1","statusString":"运行"},{"id":2,"photo":"/device/photo/bb.jpg","name":"CH01-2离心式制冷机","model":"CH01-2","statusString":"运行"}]
             */

            private String name;
            private List<DeviceListBean> deviceList;

            public boolean isOpen() {
                return isOpen;
            }

            public void setOpen(boolean open) {
                isOpen = open;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<DeviceListBean> getDeviceList() {
                return deviceList;
            }

            public void setDeviceList(List<DeviceListBean> deviceList) {
                this.deviceList = deviceList;
            }

            public static class DeviceListBean {
                /**
                 * id : 1
                 * photo : /device/photo/aa.jpg
                 * name : CH01-1离心式制冷机
                 * model : CH01-1
                 * statusString : 运行
                 */

                private int id;
                private String photo;
                private String name;
                private String model;
                private String statusString;
                private int status;

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getPhoto() {
                    return photo;
                }

                public void setPhoto(String photo) {
                    this.photo = photo;
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

                public String getStatusString() {
                    return statusString;
                }

                public void setStatusString(String statusString) {
                    this.statusString = statusString;
                }
            }
        }
    }
}
