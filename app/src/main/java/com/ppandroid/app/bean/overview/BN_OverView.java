/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.bean.overview;

import com.ppandroid.im.bean.BaseBody;

import java.util.List;

/**
 * Created by yeqinfu on 2017/8/17.
 */

public class BN_OverView extends BaseBody {


    /**
     * message : {"overviewConsumptionInformation":{"totalKwh":"0kwh","totalMoney":"0元","carbonEmission":"0g","compareToYesterday":"-100.00%","compareToLastMonth":"-7.00%","energyUseThisMonth":"1.75万kwh","compareToLastMonthToday":"-100.00%","cmpareToLastYearToday":"0%","totalMoneyMonth":"1.19万元","classificationInformationList":[{"totalKwh":"0kwh","classificationKwhMapList":[{"key":"冷冻泵","value":"0kwh"},{"key":"冷却塔","value":"0kwh"},{"key":"冷干机","value":"0kwh"},{"key":"制冷机","value":"0kwh"},{"key":"空调系统冷却泵","value":"0kwh"}]},{"totalKwh":"1.75万kwh","classificationKwhMapList":[{"key":"制冷机","value":"1.75万kwh"},{"key":"冷冻泵","value":"0kwh"},{"key":"冷却塔","value":"0kwh"},{"key":"冷干机","value":"0kwh"},{"key":"空调系统冷却泵","value":"0kwh"}]},{"totalKwh":"24.48万kwh","classificationKwhMapList":[{"key":"制冷机","value":"24.48万kwh"},{"key":"冷冻泵","value":"0kwh"},{"key":"冷却塔","value":"0kwh"},{"key":"冷干机","value":"0kwh"},{"key":"空调系统冷却泵","value":"0kwh"}]}],"areaInformationList":[{"areaKwhMapList":[{"key":"高压动力站","value":"0kwh"}]},{"areaKwhMapList":[{"key":"高压动力站","value":"1.75万kwh"}]},{"areaKwhMapList":[{"key":"高压动力站","value":"24.48万kwh"}]}]},"instrumentInfomationList":[{"instrumentMapList":[{"key":"CH01-1约克离心式制冷机","value":"0kwh"},{"key":"CH01-2约克离心式制冷机","value":"0kwh"}]},{"instrumentMapList":[{"key":"CH01-2约克离心式制冷机","value":"1.76万kwh"},{"key":"CH01-1约克离心式制冷机","value":"1.75万kwh"}]},{"instrumentMapList":[{"key":"CH01-1约克离心式制冷机","value":"24.48万kwh"},{"key":"CH01-2约克离心式制冷机","value":"24.44万kwh"}]}],"deviceInformationList":[{"deviceKwhMapList":[{"key":"CH01-1离心式制冷机","value":"0kwh"},{"key":"CH01-2离心式制冷机","value":"0kwh"}]},{"deviceKwhMapList":[{"key":"CH01-2离心式制冷机","value":"1.76万kwh"},{"key":"CH01-1离心式制冷机","value":"1.75万kwh"}]},{"deviceKwhMapList":[{"key":"CH01-1离心式制冷机","value":"24.48万kwh"},{"key":"CH01-2离心式制冷机","value":"24.44万kwh"}]}]}
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
         * overviewConsumptionInformation : {"totalKwh":"0kwh","totalMoney":"0元","carbonEmission":"0g","compareToYesterday":"-100.00%","compareToLastMonth":"-7.00%","energyUseThisMonth":"1.75万kwh","compareToLastMonthToday":"-100.00%","cmpareToLastYearToday":"0%","totalMoneyMonth":"1.19万元","classificationInformationList":[{"totalKwh":"0kwh","classificationKwhMapList":[{"key":"冷冻泵","value":"0kwh"},{"key":"冷却塔","value":"0kwh"},{"key":"冷干机","value":"0kwh"},{"key":"制冷机","value":"0kwh"},{"key":"空调系统冷却泵","value":"0kwh"}]},{"totalKwh":"1.75万kwh","classificationKwhMapList":[{"key":"制冷机","value":"1.75万kwh"},{"key":"冷冻泵","value":"0kwh"},{"key":"冷却塔","value":"0kwh"},{"key":"冷干机","value":"0kwh"},{"key":"空调系统冷却泵","value":"0kwh"}]},{"totalKwh":"24.48万kwh","classificationKwhMapList":[{"key":"制冷机","value":"24.48万kwh"},{"key":"冷冻泵","value":"0kwh"},{"key":"冷却塔","value":"0kwh"},{"key":"冷干机","value":"0kwh"},{"key":"空调系统冷却泵","value":"0kwh"}]}],"areaInformationList":[{"areaKwhMapList":[{"key":"高压动力站","value":"0kwh"}]},{"areaKwhMapList":[{"key":"高压动力站","value":"1.75万kwh"}]},{"areaKwhMapList":[{"key":"高压动力站","value":"24.48万kwh"}]}]}
         * instrumentInfomationList : [{"instrumentMapList":[{"key":"CH01-1约克离心式制冷机","value":"0kwh"},{"key":"CH01-2约克离心式制冷机","value":"0kwh"}]},{"instrumentMapList":[{"key":"CH01-2约克离心式制冷机","value":"1.76万kwh"},{"key":"CH01-1约克离心式制冷机","value":"1.75万kwh"}]},{"instrumentMapList":[{"key":"CH01-1约克离心式制冷机","value":"24.48万kwh"},{"key":"CH01-2约克离心式制冷机","value":"24.44万kwh"}]}]
         * deviceInformationList : [{"deviceKwhMapList":[{"key":"CH01-1离心式制冷机","value":"0kwh"},{"key":"CH01-2离心式制冷机","value":"0kwh"}]},{"deviceKwhMapList":[{"key":"CH01-2离心式制冷机","value":"1.76万kwh"},{"key":"CH01-1离心式制冷机","value":"1.75万kwh"}]},{"deviceKwhMapList":[{"key":"CH01-1离心式制冷机","value":"24.48万kwh"},{"key":"CH01-2离心式制冷机","value":"24.44万kwh"}]}]
         */

        private OverviewConsumptionInformationBean overviewConsumptionInformation;
        private List<InstrumentInfomationListBean> instrumentInfomationList;
        private List<DeviceInformationListBean> deviceInformationList;

        public OverviewConsumptionInformationBean getOverviewConsumptionInformation() {
            return overviewConsumptionInformation;
        }

        public void setOverviewConsumptionInformation(OverviewConsumptionInformationBean overviewConsumptionInformation) {
            this.overviewConsumptionInformation = overviewConsumptionInformation;
        }

        public List<InstrumentInfomationListBean> getInstrumentInfomationList() {
            return instrumentInfomationList;
        }

        public void setInstrumentInfomationList(List<InstrumentInfomationListBean> instrumentInfomationList) {
            this.instrumentInfomationList = instrumentInfomationList;
        }

        public List<DeviceInformationListBean> getDeviceInformationList() {
            return deviceInformationList;
        }

        public void setDeviceInformationList(List<DeviceInformationListBean> deviceInformationList) {
            this.deviceInformationList = deviceInformationList;
        }

        public static class OverviewConsumptionInformationBean {
            /**
             * totalKwh : 0kwh
             * totalMoney : 0元
             * carbonEmission : 0g
             * compareToYesterday : -100.00%
             * compareToLastMonth : -7.00%
             * energyUseThisMonth : 1.75万kwh
             * compareToLastMonthToday : -100.00%
             * cmpareToLastYearToday : 0%
             * totalMoneyMonth : 1.19万元
             * classificationInformationList : [{"totalKwh":"0kwh","classificationKwhMapList":[{"key":"冷冻泵","value":"0kwh"},{"key":"冷却塔","value":"0kwh"},{"key":"冷干机","value":"0kwh"},{"key":"制冷机","value":"0kwh"},{"key":"空调系统冷却泵","value":"0kwh"}]},{"totalKwh":"1.75万kwh","classificationKwhMapList":[{"key":"制冷机","value":"1.75万kwh"},{"key":"冷冻泵","value":"0kwh"},{"key":"冷却塔","value":"0kwh"},{"key":"冷干机","value":"0kwh"},{"key":"空调系统冷却泵","value":"0kwh"}]},{"totalKwh":"24.48万kwh","classificationKwhMapList":[{"key":"制冷机","value":"24.48万kwh"},{"key":"冷冻泵","value":"0kwh"},{"key":"冷却塔","value":"0kwh"},{"key":"冷干机","value":"0kwh"},{"key":"空调系统冷却泵","value":"0kwh"}]}]
             * areaInformationList : [{"areaKwhMapList":[{"key":"高压动力站","value":"0kwh"}]},{"areaKwhMapList":[{"key":"高压动力站","value":"1.75万kwh"}]},{"areaKwhMapList":[{"key":"高压动力站","value":"24.48万kwh"}]}]
             */

            private String totalKwh;
            private String totalMoney;
            private String carbonEmission;
            private String compareToYesterday;
            private String compareToLastMonth;
            private String energyUseThisMonth;
            private String compareToLastMonthToday;
            private String cmpareToLastYearToday;
            private String totalMoneyMonth;
            private List<ClassificationInformationListBean> classificationInformationList;
            private List<AreaInformationListBean> areaInformationList;

            public String getTotalKwh() {
                return totalKwh;
            }

            public void setTotalKwh(String totalKwh) {
                this.totalKwh = totalKwh;
            }

            public String getTotalMoney() {
                return totalMoney;
            }

            public void setTotalMoney(String totalMoney) {
                this.totalMoney = totalMoney;
            }

            public String getCarbonEmission() {
                return carbonEmission;
            }

            public void setCarbonEmission(String carbonEmission) {
                this.carbonEmission = carbonEmission;
            }

            public String getCompareToYesterday() {
                return compareToYesterday;
            }

            public void setCompareToYesterday(String compareToYesterday) {
                this.compareToYesterday = compareToYesterday;
            }

            public String getCompareToLastMonth() {
                return compareToLastMonth;
            }

            public void setCompareToLastMonth(String compareToLastMonth) {
                this.compareToLastMonth = compareToLastMonth;
            }

            public String getEnergyUseThisMonth() {
                return energyUseThisMonth;
            }

            public void setEnergyUseThisMonth(String energyUseThisMonth) {
                this.energyUseThisMonth = energyUseThisMonth;
            }

            public String getCompareToLastMonthToday() {
                return compareToLastMonthToday;
            }

            public void setCompareToLastMonthToday(String compareToLastMonthToday) {
                this.compareToLastMonthToday = compareToLastMonthToday;
            }

            public String getCmpareToLastYearToday() {
                return cmpareToLastYearToday;
            }

            public void setCmpareToLastYearToday(String cmpareToLastYearToday) {
                this.cmpareToLastYearToday = cmpareToLastYearToday;
            }

            public String getTotalMoneyMonth() {
                return totalMoneyMonth;
            }

            public void setTotalMoneyMonth(String totalMoneyMonth) {
                this.totalMoneyMonth = totalMoneyMonth;
            }

            public List<ClassificationInformationListBean> getClassificationInformationList() {
                return classificationInformationList;
            }

            public void setClassificationInformationList(List<ClassificationInformationListBean> classificationInformationList) {
                this.classificationInformationList = classificationInformationList;
            }

            public List<AreaInformationListBean> getAreaInformationList() {
                return areaInformationList;
            }

            public void setAreaInformationList(List<AreaInformationListBean> areaInformationList) {
                this.areaInformationList = areaInformationList;
            }

            public static class ClassificationInformationListBean {
                /**
                 * totalKwh : 0kwh
                 * classificationKwhMapList : [{"key":"冷冻泵","value":"0kwh"},{"key":"冷却塔","value":"0kwh"},{"key":"冷干机","value":"0kwh"},{"key":"制冷机","value":"0kwh"},{"key":"空调系统冷却泵","value":"0kwh"}]
                 */

                private String totalKwh;
                private List<ClassificationKwhMapListBean> classificationKwhMapList;

                public String getTotalKwh() {
                    return totalKwh;
                }

                public void setTotalKwh(String totalKwh) {
                    this.totalKwh = totalKwh;
                }

                public List<ClassificationKwhMapListBean> getClassificationKwhMapList() {
                    return classificationKwhMapList;
                }

                public void setClassificationKwhMapList(List<ClassificationKwhMapListBean> classificationKwhMapList) {
                    this.classificationKwhMapList = classificationKwhMapList;
                }

                public static class ClassificationKwhMapListBean {
                    /**
                     * key : 冷冻泵
                     * value : 0kwh
                     */

                    private String key;
                    private String value;
                    private String ratio;
                    private String id;

                    public String getId() {
                        return id;
                    }

                    public void setId(String id) {
                        this.id = id;
                    }

                    public String getRatio() {
                        return ratio;
                    }

                    public void setRatio(String ratio) {
                        this.ratio = ratio;
                    }

                    public String getKey() {
                        return key;
                    }

                    public void setKey(String key) {
                        this.key = key;
                    }

                    public String getValue() {
                        return value;
                    }

                    public void setValue(String value) {
                        this.value = value;
                    }
                }
            }

            public static class AreaInformationListBean {
                private List<AreaKwhMapListBean> areaKwhMapList;

                public List<AreaKwhMapListBean> getAreaKwhMapList() {
                    return areaKwhMapList;
                }

                public void setAreaKwhMapList(List<AreaKwhMapListBean> areaKwhMapList) {
                    this.areaKwhMapList = areaKwhMapList;
                }

                public static class AreaKwhMapListBean {
                    /**
                     * key : 高压动力站
                     * value : 0kwh
                     */

                    private String key;
                    private String value;
                    private String ratio;
                    private String id;

                    public String getId() {
                        return id;
                    }

                    public void setId(String id) {
                        this.id = id;
                    }

                    public String getRatio() {
                        return ratio;
                    }

                    public void setRatio(String ratio) {
                        this.ratio = ratio;
                    }

                    public String getKey() {
                        return key;
                    }

                    public void setKey(String key) {
                        this.key = key;
                    }

                    public String getValue() {
                        return value;
                    }

                    public void setValue(String value) {
                        this.value = value;
                    }
                }
            }
        }

        public static class InstrumentInfomationListBean {
            private List<InstrumentMapListBean> instrumentMapList;

            public List<InstrumentMapListBean> getInstrumentMapList() {
                return instrumentMapList;
            }

            public void setInstrumentMapList(List<InstrumentMapListBean> instrumentMapList) {
                this.instrumentMapList = instrumentMapList;
            }

            public static class InstrumentMapListBean {
                /**
                 * key : CH01-1约克离心式制冷机
                 * value : 0kwh
                 */

                private String key;
                private String value;
                private String ratio;
                private String id;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getRatio() {
                    return ratio;
                }

                public void setRatio(String ratio) {
                    this.ratio = ratio;
                }

                public String getKey() {
                    return key;
                }

                public void setKey(String key) {
                    this.key = key;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }
        }

        public static class DeviceInformationListBean {
            private List<DeviceKwhMapListBean> deviceKwhMapList;

            public List<DeviceKwhMapListBean> getDeviceKwhMapList() {
                return deviceKwhMapList;
            }

            public void setDeviceKwhMapList(List<DeviceKwhMapListBean> deviceKwhMapList) {
                this.deviceKwhMapList = deviceKwhMapList;
            }

            public static class DeviceKwhMapListBean {
                /**
                 * key : CH01-1离心式制冷机
                 * value : 0kwh
                 */

                private String key;
                private String value;
                private String ratio;
                private String id;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getRatio() {
                    return ratio;
                }

                public void setRatio(String ratio) {
                    this.ratio = ratio;
                }

                public String getKey() {
                    return key;
                }

                public void setKey(String key) {
                    this.key = key;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }
        }
    }
}
