/*
 * Created by yeqinfu on 17-12-29 下午4:53
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.bean.overview;

import com.ppandroid.im.bean.BaseBody;

import java.util.List;

/**
 * Created by yeqinfu on 2017/12/29.
 */

public class BN_OverViewWater extends BaseBody {

    /**
     * message : {"overviewConsumptionInformation":{"totalM3":"43m3","totalMoney":"130元","compareToYesterday":"0%","compareToLastMonth":"4300.00%","energyUseThisMonth":"43m3","compareToLastMonthToday":"0.00%","cmpareToLastYearToday":"0%","totalMoneyMonth":"130元","classificationInformationList":[{"totalM3":"43.44m3","classificationM3MapList":[{"key":"高压冷却泵","value":"0.00m3","ratio":"0%","id":3},{"key":"冷冻泵","value":"0.00m3","ratio":"0%","id":4},{"key":"制冷机","value":"0.00m3","ratio":"0%","id":2},{"key":"高压式离心式制冷机","value":"0.00m3","ratio":"0%","id":7},{"key":"其它","value":"43.44m3","ratio":"100.0%","id":null}]},{"totalM3":"43.44m3","classificationM3MapList":[{"key":"高压冷却泵","value":"0.00m3","ratio":"0%","id":3},{"key":"冷冻泵","value":"0.00m3","ratio":"0%","id":4},{"key":"制冷机","value":"0.00m3","ratio":"0%","id":2},{"key":"高压式离心式制冷机","value":"0.00m3","ratio":"0%","id":7},{"key":"其它","value":"43.44m3","ratio":"100.0%","id":null}]},{"totalM3":"0.00kwh","classificationM3MapList":[{"key":"高压冷却泵","value":"0.00m3","ratio":"0%","id":3},{"key":"冷冻泵","value":"0.00m3","ratio":"0%","id":4},{"key":"制冷机","value":"0.00m3","ratio":"0%","id":2},{"key":"高压式离心式制冷机","value":"0.00m3","ratio":"0%","id":7},{"key":"其它","value":"0.00m3","ratio":"0.0%","id":null}]}],"areaInformationList":[{"areaM3MapList":[{"key":"顶级区域","value":"0m3","ratio":"�%","id":3},{"key":"高压动力站","value":"0m3","ratio":"�%","id":1},{"key":"区域","value":"0m3","ratio":"�%","id":7},{"key":"区域1","value":"0m3","ratio":"�%","id":8}]},{"areaM3MapList":[{"key":"顶级区域","value":"0m3","ratio":"�%","id":3},{"key":"高压动力站","value":"0m3","ratio":"�%","id":1},{"key":"区域","value":"0m3","ratio":"�%","id":7},{"key":"区域1","value":"0m3","ratio":"�%","id":8}]},{"areaM3MapList":[{"key":"顶级区域","value":"0m3","ratio":"�%","id":3},{"key":"高压动力站","value":"0m3","ratio":"�%","id":1},{"key":"区域","value":"0m3","ratio":"�%","id":7},{"key":"区域1","value":"0m3","ratio":"�%","id":8}]}]},"instrumentInfomationList":[{"instrumentMapList":[{"key":"CH01-1约克离心式制冷机","value":"0kwh","ratio":"�%","id":1},{"key":"CH01-2约克离心式制冷机","value":"0kwh","ratio":"�%","id":2},{"key":"钦富仪表别名","value":"0kwh","ratio":"�%","id":28},{"key":"宝剑能耗检测仪","value":"0kwh","ratio":"�%","id":38}]},{"instrumentMapList":[{"key":"CH01-1约克离心式制冷机","value":"0kwh","ratio":"�%","id":1},{"key":"CH01-2约克离心式制冷机","value":"0kwh","ratio":"�%","id":2},{"key":"钦富仪表别名","value":"0kwh","ratio":"�%","id":28},{"key":"宝剑能耗检测仪","value":"0kwh","ratio":"�%","id":38}]},{"instrumentMapList":[{"key":"CH01-1约克离心式制冷机","value":"0kwh","ratio":"�%","id":1},{"key":"CH01-2约克离心式制冷机","value":"0kwh","ratio":"�%","id":2},{"key":"钦富仪表别名","value":"0kwh","ratio":"�%","id":28},{"key":"宝剑能耗检测仪","value":"0kwh","ratio":"�%","id":38}]}],"deviceInformationList":[{"deviceM3MapList":[{"key":"42","value":"0kwh","ratio":"�%","id":4},{"key":"时光机","value":"0kwh","ratio":"�%","id":11},{"key":"飞机","value":"0kwh","ratio":"�%","id":13},{"key":"牛逼设备","value":"0kwh","ratio":"�%","id":14}]},{"deviceM3MapList":[{"key":"42","value":"0kwh","ratio":"�%","id":4},{"key":"时光机","value":"0kwh","ratio":"�%","id":11},{"key":"飞机","value":"0kwh","ratio":"�%","id":13},{"key":"牛逼设备","value":"0kwh","ratio":"�%","id":14}]},{"deviceM3MapList":[{"key":"42","value":"0kwh","ratio":"�%","id":4},{"key":"时光机","value":"0kwh","ratio":"�%","id":11},{"key":"飞机","value":"0kwh","ratio":"�%","id":13},{"key":"牛逼设备","value":"0kwh","ratio":"�%","id":14}]}]}
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
         * overviewConsumptionInformation : {"totalM3":"43m3","totalMoney":"130元","compareToYesterday":"0%","compareToLastMonth":"4300.00%","energyUseThisMonth":"43m3","compareToLastMonthToday":"0.00%","cmpareToLastYearToday":"0%","totalMoneyMonth":"130元","classificationInformationList":[{"totalM3":"43.44m3","classificationM3MapList":[{"key":"高压冷却泵","value":"0.00m3","ratio":"0%","id":3},{"key":"冷冻泵","value":"0.00m3","ratio":"0%","id":4},{"key":"制冷机","value":"0.00m3","ratio":"0%","id":2},{"key":"高压式离心式制冷机","value":"0.00m3","ratio":"0%","id":7},{"key":"其它","value":"43.44m3","ratio":"100.0%","id":null}]},{"totalM3":"43.44m3","classificationM3MapList":[{"key":"高压冷却泵","value":"0.00m3","ratio":"0%","id":3},{"key":"冷冻泵","value":"0.00m3","ratio":"0%","id":4},{"key":"制冷机","value":"0.00m3","ratio":"0%","id":2},{"key":"高压式离心式制冷机","value":"0.00m3","ratio":"0%","id":7},{"key":"其它","value":"43.44m3","ratio":"100.0%","id":null}]},{"totalM3":"0.00kwh","classificationM3MapList":[{"key":"高压冷却泵","value":"0.00m3","ratio":"0%","id":3},{"key":"冷冻泵","value":"0.00m3","ratio":"0%","id":4},{"key":"制冷机","value":"0.00m3","ratio":"0%","id":2},{"key":"高压式离心式制冷机","value":"0.00m3","ratio":"0%","id":7},{"key":"其它","value":"0.00m3","ratio":"0.0%","id":null}]}],"areaInformationList":[{"areaM3MapList":[{"key":"顶级区域","value":"0m3","ratio":"�%","id":3},{"key":"高压动力站","value":"0m3","ratio":"�%","id":1},{"key":"区域","value":"0m3","ratio":"�%","id":7},{"key":"区域1","value":"0m3","ratio":"�%","id":8}]},{"areaM3MapList":[{"key":"顶级区域","value":"0m3","ratio":"�%","id":3},{"key":"高压动力站","value":"0m3","ratio":"�%","id":1},{"key":"区域","value":"0m3","ratio":"�%","id":7},{"key":"区域1","value":"0m3","ratio":"�%","id":8}]},{"areaM3MapList":[{"key":"顶级区域","value":"0m3","ratio":"�%","id":3},{"key":"高压动力站","value":"0m3","ratio":"�%","id":1},{"key":"区域","value":"0m3","ratio":"�%","id":7},{"key":"区域1","value":"0m3","ratio":"�%","id":8}]}]}
         * instrumentInfomationList : [{"instrumentMapList":[{"key":"CH01-1约克离心式制冷机","value":"0kwh","ratio":"�%","id":1},{"key":"CH01-2约克离心式制冷机","value":"0kwh","ratio":"�%","id":2},{"key":"钦富仪表别名","value":"0kwh","ratio":"�%","id":28},{"key":"宝剑能耗检测仪","value":"0kwh","ratio":"�%","id":38}]},{"instrumentMapList":[{"key":"CH01-1约克离心式制冷机","value":"0kwh","ratio":"�%","id":1},{"key":"CH01-2约克离心式制冷机","value":"0kwh","ratio":"�%","id":2},{"key":"钦富仪表别名","value":"0kwh","ratio":"�%","id":28},{"key":"宝剑能耗检测仪","value":"0kwh","ratio":"�%","id":38}]},{"instrumentMapList":[{"key":"CH01-1约克离心式制冷机","value":"0kwh","ratio":"�%","id":1},{"key":"CH01-2约克离心式制冷机","value":"0kwh","ratio":"�%","id":2},{"key":"钦富仪表别名","value":"0kwh","ratio":"�%","id":28},{"key":"宝剑能耗检测仪","value":"0kwh","ratio":"�%","id":38}]}]
         * deviceInformationList : [{"deviceM3MapList":[{"key":"42","value":"0kwh","ratio":"�%","id":4},{"key":"时光机","value":"0kwh","ratio":"�%","id":11},{"key":"飞机","value":"0kwh","ratio":"�%","id":13},{"key":"牛逼设备","value":"0kwh","ratio":"�%","id":14}]},{"deviceM3MapList":[{"key":"42","value":"0kwh","ratio":"�%","id":4},{"key":"时光机","value":"0kwh","ratio":"�%","id":11},{"key":"飞机","value":"0kwh","ratio":"�%","id":13},{"key":"牛逼设备","value":"0kwh","ratio":"�%","id":14}]},{"deviceM3MapList":[{"key":"42","value":"0kwh","ratio":"�%","id":4},{"key":"时光机","value":"0kwh","ratio":"�%","id":11},{"key":"飞机","value":"0kwh","ratio":"�%","id":13},{"key":"牛逼设备","value":"0kwh","ratio":"�%","id":14}]}]
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
             * totalM3 : 43m3
             * totalMoney : 130元
             * compareToYesterday : 0%
             * compareToLastMonth : 4300.00%
             * energyUseThisMonth : 43m3
             * compareToLastMonthToday : 0.00%
             * cmpareToLastYearToday : 0%
             * totalMoneyMonth : 130元
             * classificationInformationList : [{"totalM3":"43.44m3","classificationM3MapList":[{"key":"高压冷却泵","value":"0.00m3","ratio":"0%","id":3},{"key":"冷冻泵","value":"0.00m3","ratio":"0%","id":4},{"key":"制冷机","value":"0.00m3","ratio":"0%","id":2},{"key":"高压式离心式制冷机","value":"0.00m3","ratio":"0%","id":7},{"key":"其它","value":"43.44m3","ratio":"100.0%","id":null}]},{"totalM3":"43.44m3","classificationM3MapList":[{"key":"高压冷却泵","value":"0.00m3","ratio":"0%","id":3},{"key":"冷冻泵","value":"0.00m3","ratio":"0%","id":4},{"key":"制冷机","value":"0.00m3","ratio":"0%","id":2},{"key":"高压式离心式制冷机","value":"0.00m3","ratio":"0%","id":7},{"key":"其它","value":"43.44m3","ratio":"100.0%","id":null}]},{"totalM3":"0.00kwh","classificationM3MapList":[{"key":"高压冷却泵","value":"0.00m3","ratio":"0%","id":3},{"key":"冷冻泵","value":"0.00m3","ratio":"0%","id":4},{"key":"制冷机","value":"0.00m3","ratio":"0%","id":2},{"key":"高压式离心式制冷机","value":"0.00m3","ratio":"0%","id":7},{"key":"其它","value":"0.00m3","ratio":"0.0%","id":null}]}]
             * areaInformationList : [{"areaM3MapList":[{"key":"顶级区域","value":"0m3","ratio":"�%","id":3},{"key":"高压动力站","value":"0m3","ratio":"�%","id":1},{"key":"区域","value":"0m3","ratio":"�%","id":7},{"key":"区域1","value":"0m3","ratio":"�%","id":8}]},{"areaM3MapList":[{"key":"顶级区域","value":"0m3","ratio":"�%","id":3},{"key":"高压动力站","value":"0m3","ratio":"�%","id":1},{"key":"区域","value":"0m3","ratio":"�%","id":7},{"key":"区域1","value":"0m3","ratio":"�%","id":8}]},{"areaM3MapList":[{"key":"顶级区域","value":"0m3","ratio":"�%","id":3},{"key":"高压动力站","value":"0m3","ratio":"�%","id":1},{"key":"区域","value":"0m3","ratio":"�%","id":7},{"key":"区域1","value":"0m3","ratio":"�%","id":8}]}]
             */

            private String totalM3;
            private String totalMoney;
            private String compareToYesterday;
            private String compareToLastMonth;
            private String energyUseThisMonth;
            private String compareToLastMonthToday;
            private String cmpareToLastYearToday;
            private String totalMoneyMonth;
            private List<ClassificationInformationListBean> classificationInformationList;
            private List<AreaInformationListBean> areaInformationList;

            public String getTotalM3() {
                return totalM3;
            }

            public void setTotalM3(String totalM3) {
                this.totalM3 = totalM3;
            }

            public String getTotalMoney() {
                return totalMoney;
            }

            public void setTotalMoney(String totalMoney) {
                this.totalMoney = totalMoney;
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
                 * totalM3 : 43.44m3
                 * classificationM3MapList : [{"key":"高压冷却泵","value":"0.00m3","ratio":"0%","id":3},{"key":"冷冻泵","value":"0.00m3","ratio":"0%","id":4},{"key":"制冷机","value":"0.00m3","ratio":"0%","id":2},{"key":"高压式离心式制冷机","value":"0.00m3","ratio":"0%","id":7},{"key":"其它","value":"43.44m3","ratio":"100.0%","id":null}]
                 */

                private String totalM3;
                private List<ClassificationM3MapListBean> classificationM3MapList;

                public String getTotalM3() {
                    return totalM3;
                }

                public void setTotalM3(String totalM3) {
                    this.totalM3 = totalM3;
                }

                public List<ClassificationM3MapListBean> getClassificationM3MapList() {
                    return classificationM3MapList;
                }

                public void setClassificationM3MapList(List<ClassificationM3MapListBean> classificationM3MapList) {
                    this.classificationM3MapList = classificationM3MapList;
                }

                public static class ClassificationM3MapListBean {
                    /**
                     * key : 高压冷却泵
                     * value : 0.00m3
                     * ratio : 0%
                     * id : 3
                     */

                    private String key;
                    private String value;
                    private String ratio;
                    private int id;

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

                    public String getRatio() {
                        return ratio;
                    }

                    public void setRatio(String ratio) {
                        this.ratio = ratio;
                    }

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }
                }
            }

            public static class AreaInformationListBean {
                private List<AreaM3MapListBean> areaM3MapList;

                public List<AreaM3MapListBean> getAreaM3MapList() {
                    return areaM3MapList;
                }

                public void setAreaM3MapList(List<AreaM3MapListBean> areaM3MapList) {
                    this.areaM3MapList = areaM3MapList;
                }

                public static class AreaM3MapListBean {
                    /**
                     * key : 顶级区域
                     * value : 0m3
                     * ratio : �%
                     * id : 3
                     */

                    private String key;
                    private String value;
                    private String ratio;
                    private int id;

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

                    public String getRatio() {
                        return ratio;
                    }

                    public void setRatio(String ratio) {
                        this.ratio = ratio;
                    }

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
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
                 * ratio : �%
                 * id : 1
                 */

                private String key;
                private String value;
                private String ratio;
                private int id;

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

                public String getRatio() {
                    return ratio;
                }

                public void setRatio(String ratio) {
                    this.ratio = ratio;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }
            }
        }

        public static class DeviceInformationListBean {
            private List<DeviceM3MapListBean> deviceM3MapList;

            public List<DeviceM3MapListBean> getDeviceM3MapList() {
                return deviceM3MapList;
            }

            public void setDeviceM3MapList(List<DeviceM3MapListBean> deviceM3MapList) {
                this.deviceM3MapList = deviceM3MapList;
            }

            public static class DeviceM3MapListBean {
                /**
                 * key : 42
                 * value : 0kwh
                 * ratio : �%
                 * id : 4
                 */

                private String key;
                private String value;
                private String ratio;
                private int id;

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

                public String getRatio() {
                    return ratio;
                }

                public void setRatio(String ratio) {
                    this.ratio = ratio;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }
            }
        }
    }
}
