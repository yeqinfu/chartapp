package com.ppandroid.app.bean.overview;

import com.google.gson.annotations.SerializedName;
import com.ppandroid.im.bean.BaseBody;

import java.util.List;

/**
 * Created by yeqinfu on 2017/8/17.
 */

public class BN_OverView extends BaseBody {

    /**
     * message : {"overviewConsumptionInformation":{"totalKwh":"915kwh","totalMoney":"622元","carbonEmission":"878kg","compareToYesterday":"-32.00%","compareToLastMonth":"-1.50%","energyUseThisMonth":"1.75万kwh","compareToLastMonthToday":"-32.00%","cmpareToLastYearToday":"0%","totalMoneyMonth":"1.19万元","classificationInformationList":[{"totalKwh":"915kwh","classificationKwhMap":{"制冷机":"915kwh","冷冻泵":"0kwh","冷却塔":"0kwh","冷干机":"0kwh","空调系统冷却泵":"0kwh","高压冷却水泵":"0kwh","高压冷却泵":"0kwh","高压寿力螺杆空压机":"0kwh","高压式离心式制冷机":"0kwh","高压离心式制冷机":"0kwh"}},{"totalKwh":"1.75万kwh","classificationKwhMap":{"制冷机":"1.75万kwh","冷冻泵":"0kwh","冷却塔":"0kwh","冷干机":"0kwh","空调系统冷却泵":"0kwh","高压冷却水泵":"0kwh","高压冷却泵":"0kwh","高压寿力螺杆空压机":"0kwh","高压式离心式制冷机":"0kwh","高压离心式制冷机":"0kwh"}},{"totalKwh":"24.48万kwh","classificationKwhMap":{"制冷机":"24.48万kwh","冷冻泵":"0kwh","冷却塔":"0kwh","冷干机":"0kwh","空调系统冷却泵":"0kwh","高压冷却水泵":"0kwh","高压冷却泵":"0kwh","高压寿力螺杆空压机":"0kwh","高压式离心式制冷机":"0kwh","高压离心式制冷机":"0kwh"}}],"areaInformationList":[{"areaKwhMap":{"高压动力站":"915kwh"}},{"areaKwhMap":{"高压动力站":"1.75万kwh"}},{"areaKwhMap":{"高压动力站":"24.48万kwh"}}]},"instrumentInfomationList":[{"instrumentMap":{"CH01-1约克离心式制冷机":"452kwh","CH01-2约克离心式制冷机":"463kwh"}},{"instrumentMap":{"CH01-1约克离心式制冷机":"1.75万kwh","CH01-2约克离心式制冷机":"1.76万kwh"}},{"instrumentMap":{"CH01-1约克离心式制冷机":"24.48万kwh","CH01-2约克离心式制冷机":"24.44万kwh"}}],"deviceInformationList":[{"deviceKwhMap":{"CH01-1离心式制冷机":"452kwh","CH01-2离心式制冷机":"463kwh"}},{"deviceKwhMap":{"CH01-1离心式制冷机":"1.75万kwh","CH01-2离心式制冷机":"1.76万kwh"}},{"deviceKwhMap":{"CH01-1离心式制冷机":"24.48万kwh","CH01-2离心式制冷机":"24.44万kwh"}}]}
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
         * overviewConsumptionInformation : {"totalKwh":"915kwh","totalMoney":"622元","carbonEmission":"878kg","compareToYesterday":"-32.00%","compareToLastMonth":"-1.50%","energyUseThisMonth":"1.75万kwh","compareToLastMonthToday":"-32.00%","cmpareToLastYearToday":"0%","totalMoneyMonth":"1.19万元","classificationInformationList":[{"totalKwh":"915kwh","classificationKwhMap":{"制冷机":"915kwh","冷冻泵":"0kwh","冷却塔":"0kwh","冷干机":"0kwh","空调系统冷却泵":"0kwh","高压冷却水泵":"0kwh","高压冷却泵":"0kwh","高压寿力螺杆空压机":"0kwh","高压式离心式制冷机":"0kwh","高压离心式制冷机":"0kwh"}},{"totalKwh":"1.75万kwh","classificationKwhMap":{"制冷机":"1.75万kwh","冷冻泵":"0kwh","冷却塔":"0kwh","冷干机":"0kwh","空调系统冷却泵":"0kwh","高压冷却水泵":"0kwh","高压冷却泵":"0kwh","高压寿力螺杆空压机":"0kwh","高压式离心式制冷机":"0kwh","高压离心式制冷机":"0kwh"}},{"totalKwh":"24.48万kwh","classificationKwhMap":{"制冷机":"24.48万kwh","冷冻泵":"0kwh","冷却塔":"0kwh","冷干机":"0kwh","空调系统冷却泵":"0kwh","高压冷却水泵":"0kwh","高压冷却泵":"0kwh","高压寿力螺杆空压机":"0kwh","高压式离心式制冷机":"0kwh","高压离心式制冷机":"0kwh"}}],"areaInformationList":[{"areaKwhMap":{"高压动力站":"915kwh"}},{"areaKwhMap":{"高压动力站":"1.75万kwh"}},{"areaKwhMap":{"高压动力站":"24.48万kwh"}}]}
         * instrumentInfomationList : [{"instrumentMap":{"CH01-1约克离心式制冷机":"452kwh","CH01-2约克离心式制冷机":"463kwh"}},{"instrumentMap":{"CH01-1约克离心式制冷机":"1.75万kwh","CH01-2约克离心式制冷机":"1.76万kwh"}},{"instrumentMap":{"CH01-1约克离心式制冷机":"24.48万kwh","CH01-2约克离心式制冷机":"24.44万kwh"}}]
         * deviceInformationList : [{"deviceKwhMap":{"CH01-1离心式制冷机":"452kwh","CH01-2离心式制冷机":"463kwh"}},{"deviceKwhMap":{"CH01-1离心式制冷机":"1.75万kwh","CH01-2离心式制冷机":"1.76万kwh"}},{"deviceKwhMap":{"CH01-1离心式制冷机":"24.48万kwh","CH01-2离心式制冷机":"24.44万kwh"}}]
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
             * totalKwh : 915kwh
             * totalMoney : 622元
             * carbonEmission : 878kg
             * compareToYesterday : -32.00%
             * compareToLastMonth : -1.50%
             * energyUseThisMonth : 1.75万kwh
             * compareToLastMonthToday : -32.00%
             * cmpareToLastYearToday : 0%
             * totalMoneyMonth : 1.19万元
             * classificationInformationList : [{"totalKwh":"915kwh","classificationKwhMap":{"制冷机":"915kwh","冷冻泵":"0kwh","冷却塔":"0kwh","冷干机":"0kwh","空调系统冷却泵":"0kwh","高压冷却水泵":"0kwh","高压冷却泵":"0kwh","高压寿力螺杆空压机":"0kwh","高压式离心式制冷机":"0kwh","高压离心式制冷机":"0kwh"}},{"totalKwh":"1.75万kwh","classificationKwhMap":{"制冷机":"1.75万kwh","冷冻泵":"0kwh","冷却塔":"0kwh","冷干机":"0kwh","空调系统冷却泵":"0kwh","高压冷却水泵":"0kwh","高压冷却泵":"0kwh","高压寿力螺杆空压机":"0kwh","高压式离心式制冷机":"0kwh","高压离心式制冷机":"0kwh"}},{"totalKwh":"24.48万kwh","classificationKwhMap":{"制冷机":"24.48万kwh","冷冻泵":"0kwh","冷却塔":"0kwh","冷干机":"0kwh","空调系统冷却泵":"0kwh","高压冷却水泵":"0kwh","高压冷却泵":"0kwh","高压寿力螺杆空压机":"0kwh","高压式离心式制冷机":"0kwh","高压离心式制冷机":"0kwh"}}]
             * areaInformationList : [{"areaKwhMap":{"高压动力站":"915kwh"}},{"areaKwhMap":{"高压动力站":"1.75万kwh"}},{"areaKwhMap":{"高压动力站":"24.48万kwh"}}]
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
                 * totalKwh : 915kwh
                 * classificationKwhMap : {"制冷机":"915kwh","冷冻泵":"0kwh","冷却塔":"0kwh","冷干机":"0kwh","空调系统冷却泵":"0kwh","高压冷却水泵":"0kwh","高压冷却泵":"0kwh","高压寿力螺杆空压机":"0kwh","高压式离心式制冷机":"0kwh","高压离心式制冷机":"0kwh"}
                 */

                private String totalKwh;
                private ClassificationKwhMapBean classificationKwhMap;

                public String getTotalKwh() {
                    return totalKwh;
                }

                public void setTotalKwh(String totalKwh) {
                    this.totalKwh = totalKwh;
                }

                public ClassificationKwhMapBean getClassificationKwhMap() {
                    return classificationKwhMap;
                }

                public void setClassificationKwhMap(ClassificationKwhMapBean classificationKwhMap) {
                    this.classificationKwhMap = classificationKwhMap;
                }

                public static class ClassificationKwhMapBean {
                    /**
                     * 制冷机 : 915kwh
                     * 冷冻泵 : 0kwh
                     * 冷却塔 : 0kwh
                     * 冷干机 : 0kwh
                     * 空调系统冷却泵 : 0kwh
                     * 高压冷却水泵 : 0kwh
                     * 高压冷却泵 : 0kwh
                     * 高压寿力螺杆空压机 : 0kwh
                     * 高压式离心式制冷机 : 0kwh
                     * 高压离心式制冷机 : 0kwh
                     */

                    private String 制冷机;
                    private String 冷冻泵;
                    private String 冷却塔;
                    private String 冷干机;
                    private String 空调系统冷却泵;
                    private String 高压冷却水泵;
                    private String 高压冷却泵;
                    private String 高压寿力螺杆空压机;
                    private String 高压式离心式制冷机;
                    private String 高压离心式制冷机;

                    public String get制冷机() {
                        return 制冷机;
                    }

                    public void set制冷机(String 制冷机) {
                        this.制冷机 = 制冷机;
                    }

                    public String get冷冻泵() {
                        return 冷冻泵;
                    }

                    public void set冷冻泵(String 冷冻泵) {
                        this.冷冻泵 = 冷冻泵;
                    }

                    public String get冷却塔() {
                        return 冷却塔;
                    }

                    public void set冷却塔(String 冷却塔) {
                        this.冷却塔 = 冷却塔;
                    }

                    public String get冷干机() {
                        return 冷干机;
                    }

                    public void set冷干机(String 冷干机) {
                        this.冷干机 = 冷干机;
                    }

                    public String get空调系统冷却泵() {
                        return 空调系统冷却泵;
                    }

                    public void set空调系统冷却泵(String 空调系统冷却泵) {
                        this.空调系统冷却泵 = 空调系统冷却泵;
                    }

                    public String get高压冷却水泵() {
                        return 高压冷却水泵;
                    }

                    public void set高压冷却水泵(String 高压冷却水泵) {
                        this.高压冷却水泵 = 高压冷却水泵;
                    }

                    public String get高压冷却泵() {
                        return 高压冷却泵;
                    }

                    public void set高压冷却泵(String 高压冷却泵) {
                        this.高压冷却泵 = 高压冷却泵;
                    }

                    public String get高压寿力螺杆空压机() {
                        return 高压寿力螺杆空压机;
                    }

                    public void set高压寿力螺杆空压机(String 高压寿力螺杆空压机) {
                        this.高压寿力螺杆空压机 = 高压寿力螺杆空压机;
                    }

                    public String get高压式离心式制冷机() {
                        return 高压式离心式制冷机;
                    }

                    public void set高压式离心式制冷机(String 高压式离心式制冷机) {
                        this.高压式离心式制冷机 = 高压式离心式制冷机;
                    }

                    public String get高压离心式制冷机() {
                        return 高压离心式制冷机;
                    }

                    public void set高压离心式制冷机(String 高压离心式制冷机) {
                        this.高压离心式制冷机 = 高压离心式制冷机;
                    }
                }
            }

            public static class AreaInformationListBean {
                /**
                 * areaKwhMap : {"高压动力站":"915kwh"}
                 */

                private AreaKwhMapBean areaKwhMap;

                public AreaKwhMapBean getAreaKwhMap() {
                    return areaKwhMap;
                }

                public void setAreaKwhMap(AreaKwhMapBean areaKwhMap) {
                    this.areaKwhMap = areaKwhMap;
                }

                public static class AreaKwhMapBean {
                    /**
                     * 高压动力站 : 915kwh
                     */

                    private String 高压动力站;

                    public String get高压动力站() {
                        return 高压动力站;
                    }

                    public void set高压动力站(String 高压动力站) {
                        this.高压动力站 = 高压动力站;
                    }
                }
            }
        }

        public static class InstrumentInfomationListBean {
            /**
             * instrumentMap : {"CH01-1约克离心式制冷机":"452kwh","CH01-2约克离心式制冷机":"463kwh"}
             */

            private InstrumentMapBean instrumentMap;

            public InstrumentMapBean getInstrumentMap() {
                return instrumentMap;
            }

            public void setInstrumentMap(InstrumentMapBean instrumentMap) {
                this.instrumentMap = instrumentMap;
            }

            public static class InstrumentMapBean {
                /**
                 * CH01-1约克离心式制冷机 : 452kwh
                 * CH01-2约克离心式制冷机 : 463kwh
                 */

                @SerializedName("CH01-1约克离心式制冷机")
                private String CH011约克离心式制冷机;
                @SerializedName("CH01-2约克离心式制冷机")
                private String CH012约克离心式制冷机;

                public String getCH011约克离心式制冷机() {
                    return CH011约克离心式制冷机;
                }

                public void setCH011约克离心式制冷机(String CH011约克离心式制冷机) {
                    this.CH011约克离心式制冷机 = CH011约克离心式制冷机;
                }

                public String getCH012约克离心式制冷机() {
                    return CH012约克离心式制冷机;
                }

                public void setCH012约克离心式制冷机(String CH012约克离心式制冷机) {
                    this.CH012约克离心式制冷机 = CH012约克离心式制冷机;
                }
            }
        }

        public static class DeviceInformationListBean {
            /**
             * deviceKwhMap : {"CH01-1离心式制冷机":"452kwh","CH01-2离心式制冷机":"463kwh"}
             */

            private DeviceKwhMapBean deviceKwhMap;

            public DeviceKwhMapBean getDeviceKwhMap() {
                return deviceKwhMap;
            }

            public void setDeviceKwhMap(DeviceKwhMapBean deviceKwhMap) {
                this.deviceKwhMap = deviceKwhMap;
            }

            public static class DeviceKwhMapBean {
                /**
                 * CH01-1离心式制冷机 : 452kwh
                 * CH01-2离心式制冷机 : 463kwh
                 */

                @SerializedName("CH01-1离心式制冷机")
                private String CH011离心式制冷机;
                @SerializedName("CH01-2离心式制冷机")
                private String CH012离心式制冷机;

                public String getCH011离心式制冷机() {
                    return CH011离心式制冷机;
                }

                public void setCH011离心式制冷机(String CH011离心式制冷机) {
                    this.CH011离心式制冷机 = CH011离心式制冷机;
                }

                public String getCH012离心式制冷机() {
                    return CH012离心式制冷机;
                }

                public void setCH012离心式制冷机(String CH012离心式制冷机) {
                    this.CH012离心式制冷机 = CH012离心式制冷机;
                }
            }
        }
    }
}
