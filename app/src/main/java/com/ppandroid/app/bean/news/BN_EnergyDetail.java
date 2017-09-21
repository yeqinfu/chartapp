package com.ppandroid.app.bean.news;

import com.ppandroid.im.bean.BaseBody;

import java.util.List;

/**
 * Created by yeqinfu on 2017/9/21.
 */

public class BN_EnergyDetail extends BaseBody {


    /**
     * message : {"deviceNumber":6,"consumptionSum":"98796","energyConsumptionDeviceList":[{"deviceName":"CH01-1离心式制冷机","lastDevice":"11","lastDeviceRatio":"64.71%","thisDevice":"1066","thisDeviceRatio":"50.35%"},{"deviceName":"CH01-2离心式制冷机","lastDevice":"6","lastDeviceRatio":"35.29%","thisDevice":"1051","thisDeviceRatio":"49.65%"},{"deviceName":"永动机","lastDevice":"0","lastDeviceRatio":"0.00%","thisDevice":"0","thisDeviceRatio":"0.00%"},{"deviceName":"测试","lastDevice":"0","lastDeviceRatio":"0.00%","thisDevice":"0","thisDeviceRatio":"0.00%"}]}
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
         * deviceNumber : 6
         * consumptionSum : 98796
         * energyConsumptionDeviceList : [{"deviceName":"CH01-1离心式制冷机","lastDevice":"11","lastDeviceRatio":"64.71%","thisDevice":"1066","thisDeviceRatio":"50.35%"},{"deviceName":"CH01-2离心式制冷机","lastDevice":"6","lastDeviceRatio":"35.29%","thisDevice":"1051","thisDeviceRatio":"49.65%"},{"deviceName":"永动机","lastDevice":"0","lastDeviceRatio":"0.00%","thisDevice":"0","thisDeviceRatio":"0.00%"},{"deviceName":"测试","lastDevice":"0","lastDeviceRatio":"0.00%","thisDevice":"0","thisDeviceRatio":"0.00%"}]
         */

        private int deviceNumber;
        private String consumptionSum;
        private List<EnergyConsumptionDeviceListBean> energyConsumptionDeviceList;

        public int getDeviceNumber() {
            return deviceNumber;
        }

        public void setDeviceNumber(int deviceNumber) {
            this.deviceNumber = deviceNumber;
        }

        public String getConsumptionSum() {
            return consumptionSum;
        }

        public void setConsumptionSum(String consumptionSum) {
            this.consumptionSum = consumptionSum;
        }

        public List<EnergyConsumptionDeviceListBean> getEnergyConsumptionDeviceList() {
            return energyConsumptionDeviceList;
        }

        public void setEnergyConsumptionDeviceList(List<EnergyConsumptionDeviceListBean> energyConsumptionDeviceList) {
            this.energyConsumptionDeviceList = energyConsumptionDeviceList;
        }

        public static class EnergyConsumptionDeviceListBean {
            /**
             * deviceName : CH01-1离心式制冷机
             * lastDevice : 11
             * lastDeviceRatio : 64.71%
             * thisDevice : 1066
             * thisDeviceRatio : 50.35%
             */

            private String deviceName;
            private String lastDevice;
            private String lastDeviceRatio;
            private String thisDevice;
            private String thisDeviceRatio;

            public String getDeviceName() {
                return deviceName;
            }

            public void setDeviceName(String deviceName) {
                this.deviceName = deviceName;
            }

            public String getLastDevice() {
                return lastDevice;
            }

            public void setLastDevice(String lastDevice) {
                this.lastDevice = lastDevice;
            }

            public String getLastDeviceRatio() {
                return lastDeviceRatio;
            }

            public void setLastDeviceRatio(String lastDeviceRatio) {
                this.lastDeviceRatio = lastDeviceRatio;
            }

            public String getThisDevice() {
                return thisDevice;
            }

            public void setThisDevice(String thisDevice) {
                this.thisDevice = thisDevice;
            }

            public String getThisDeviceRatio() {
                return thisDeviceRatio;
            }

            public void setThisDeviceRatio(String thisDeviceRatio) {
                this.thisDeviceRatio = thisDeviceRatio;
            }
        }
    }
}
