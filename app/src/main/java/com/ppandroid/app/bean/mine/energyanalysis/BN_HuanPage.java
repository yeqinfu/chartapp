/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.bean.mine.energyanalysis;

import com.ppandroid.im.bean.BaseBody;

import java.util.List;

/**
 * Created by yeqinfu on 2017/9/21.
 */

public class BN_HuanPage extends BaseBody {

    /**
     * message : {"thisAnalysisDeviceDetailDto":{"averageKwh":"平均：33.03kwh","accurateAverageKwh":"33.03","totalKwh":"693.57","accurateTotalKwh":null,"stageKwh":[{"key":"01日","value":"10.98"},{"key":"02日","value":"6.40"},{"key":"03日","value":"6.38"},{"key":"04日","value":"6.39"},{"key":"05日","value":"6.40"},{"key":"06日","value":"6.40"},{"key":"07日","value":"6.26"},{"key":"08日","value":"6.38"},{"key":"09日","value":"15.68"},{"key":"10日","value":"31.90"},{"key":"11日","value":"42.16"},{"key":"12日","value":"31.94"},{"key":"13日","value":"43.73"},{"key":"14日","value":"63.68"},{"key":"15日","value":"63.87"},{"key":"16日","value":"61.49"},{"key":"17日","value":"64.00"},{"key":"18日","value":"63.91"},{"key":"19日","value":"63.86"},{"key":"20日","value":"63.81"},{"key":"21日","value":"27.95"},{"key":"22日","value":"0.00"},{"key":"23日","value":"0.00"},{"key":"24日","value":"0.00"},{"key":"25日","value":"0.00"},{"key":"26日","value":"0.00"},{"key":"27日","value":"0.00"},{"key":"28日","value":"0.00"},{"key":"29日","value":"0.00"},{"key":"30日","value":"0.00"}]},"huanbiAnalysisDeviceDetailDto":{"averageKwh":"平均：1495.56kwh","accurateAverageKwh":"1495.56","totalKwh":"46362.35","accurateTotalKwh":null,"stageKwh":[{"key":"01日","value":"2102.00"},{"key":"02日","value":"2122.00"},{"key":"03日","value":"2145.00"},{"key":"04日","value":"2118.00"},{"key":"05日","value":"2131.00"},{"key":"06日","value":"2128.00"},{"key":"07日","value":"2133.00"},{"key":"08日","value":"2172.00"},{"key":"09日","value":"2162.00"},{"key":"10日","value":"2162.00"},{"key":"11日","value":"2142.00"},{"key":"12日","value":"2109.00"},{"key":"13日","value":"2114.00"},{"key":"14日","value":"2125.00"},{"key":"15日","value":"2120.00"},{"key":"16日","value":"2157.00"},{"key":"17日","value":"2126.00"},{"key":"18日","value":"2116.00"},{"key":"19日","value":"2131.00"},{"key":"20日","value":"2122.00"},{"key":"21日","value":"2104.00"},{"key":"22日","value":"1522.74"},{"key":"23日","value":"8.91"},{"key":"24日","value":"6.40"},{"key":"25日","value":"6.40"},{"key":"26日","value":"6.38"},{"key":"27日","value":"6.40"},{"key":"28日","value":"15.68"},{"key":"29日","value":"16.48"},{"key":"30日","value":"15.98"},{"key":"31日","value":"15.98"}]},"huanbiRatio":"-97.79%"}
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
         * thisAnalysisDeviceDetailDto : {"averageKwh":"平均：33.03kwh","accurateAverageKwh":"33.03","totalKwh":"693.57","accurateTotalKwh":null,"stageKwh":[{"key":"01日","value":"10.98"},{"key":"02日","value":"6.40"},{"key":"03日","value":"6.38"},{"key":"04日","value":"6.39"},{"key":"05日","value":"6.40"},{"key":"06日","value":"6.40"},{"key":"07日","value":"6.26"},{"key":"08日","value":"6.38"},{"key":"09日","value":"15.68"},{"key":"10日","value":"31.90"},{"key":"11日","value":"42.16"},{"key":"12日","value":"31.94"},{"key":"13日","value":"43.73"},{"key":"14日","value":"63.68"},{"key":"15日","value":"63.87"},{"key":"16日","value":"61.49"},{"key":"17日","value":"64.00"},{"key":"18日","value":"63.91"},{"key":"19日","value":"63.86"},{"key":"20日","value":"63.81"},{"key":"21日","value":"27.95"},{"key":"22日","value":"0.00"},{"key":"23日","value":"0.00"},{"key":"24日","value":"0.00"},{"key":"25日","value":"0.00"},{"key":"26日","value":"0.00"},{"key":"27日","value":"0.00"},{"key":"28日","value":"0.00"},{"key":"29日","value":"0.00"},{"key":"30日","value":"0.00"}]}
         * huanbiAnalysisDeviceDetailDto : {"averageKwh":"平均：1495.56kwh","accurateAverageKwh":"1495.56","totalKwh":"46362.35","accurateTotalKwh":null,"stageKwh":[{"key":"01日","value":"2102.00"},{"key":"02日","value":"2122.00"},{"key":"03日","value":"2145.00"},{"key":"04日","value":"2118.00"},{"key":"05日","value":"2131.00"},{"key":"06日","value":"2128.00"},{"key":"07日","value":"2133.00"},{"key":"08日","value":"2172.00"},{"key":"09日","value":"2162.00"},{"key":"10日","value":"2162.00"},{"key":"11日","value":"2142.00"},{"key":"12日","value":"2109.00"},{"key":"13日","value":"2114.00"},{"key":"14日","value":"2125.00"},{"key":"15日","value":"2120.00"},{"key":"16日","value":"2157.00"},{"key":"17日","value":"2126.00"},{"key":"18日","value":"2116.00"},{"key":"19日","value":"2131.00"},{"key":"20日","value":"2122.00"},{"key":"21日","value":"2104.00"},{"key":"22日","value":"1522.74"},{"key":"23日","value":"8.91"},{"key":"24日","value":"6.40"},{"key":"25日","value":"6.40"},{"key":"26日","value":"6.38"},{"key":"27日","value":"6.40"},{"key":"28日","value":"15.68"},{"key":"29日","value":"16.48"},{"key":"30日","value":"15.98"},{"key":"31日","value":"15.98"}]}
         * huanbiRatio : -97.79%
         */

        private AnalysisDeviceDetailDtoBean thisAnalysisDeviceDetailDto;
        private AnalysisDeviceDetailDtoBean huanbiAnalysisDeviceDetailDto;
        private String huanbiRatio;

        public AnalysisDeviceDetailDtoBean getThisAnalysisDeviceDetailDto() {
            return thisAnalysisDeviceDetailDto;
        }

        public void setThisAnalysisDeviceDetailDto(AnalysisDeviceDetailDtoBean thisAnalysisDeviceDetailDto) {
            this.thisAnalysisDeviceDetailDto = thisAnalysisDeviceDetailDto;
        }

        public AnalysisDeviceDetailDtoBean getHuanbiAnalysisDeviceDetailDto() {
            return huanbiAnalysisDeviceDetailDto;
        }

        public void setHuanbiAnalysisDeviceDetailDto(AnalysisDeviceDetailDtoBean huanbiAnalysisDeviceDetailDto) {
            this.huanbiAnalysisDeviceDetailDto = huanbiAnalysisDeviceDetailDto;
        }

        public String getHuanbiRatio() {
            return huanbiRatio;
        }

        public void setHuanbiRatio(String huanbiRatio) {
            this.huanbiRatio = huanbiRatio;
        }

        public static class AnalysisDeviceDetailDtoBean {
            /**
             * averageKwh : 平均：33.03kwh
             * accurateAverageKwh : 33.03
             * totalKwh : 693.57
             * accurateTotalKwh : null
             * stageKwh : [{"key":"01日","value":"10.98"},{"key":"02日","value":"6.40"},{"key":"03日","value":"6.38"},{"key":"04日","value":"6.39"},{"key":"05日","value":"6.40"},{"key":"06日","value":"6.40"},{"key":"07日","value":"6.26"},{"key":"08日","value":"6.38"},{"key":"09日","value":"15.68"},{"key":"10日","value":"31.90"},{"key":"11日","value":"42.16"},{"key":"12日","value":"31.94"},{"key":"13日","value":"43.73"},{"key":"14日","value":"63.68"},{"key":"15日","value":"63.87"},{"key":"16日","value":"61.49"},{"key":"17日","value":"64.00"},{"key":"18日","value":"63.91"},{"key":"19日","value":"63.86"},{"key":"20日","value":"63.81"},{"key":"21日","value":"27.95"},{"key":"22日","value":"0.00"},{"key":"23日","value":"0.00"},{"key":"24日","value":"0.00"},{"key":"25日","value":"0.00"},{"key":"26日","value":"0.00"},{"key":"27日","value":"0.00"},{"key":"28日","value":"0.00"},{"key":"29日","value":"0.00"},{"key":"30日","value":"0.00"}]
             */

            private String averageKwh;
            private String accurateAverageKwh;
            private String totalKwh;
            private Object accurateTotalKwh;
            private List<StageKwhBean> stageKwh;

            public String getAverageKwh() {
                return averageKwh;
            }

            public void setAverageKwh(String averageKwh) {
                this.averageKwh = averageKwh;
            }

            public String getAccurateAverageKwh() {
                return accurateAverageKwh;
            }

            public void setAccurateAverageKwh(String accurateAverageKwh) {
                this.accurateAverageKwh = accurateAverageKwh;
            }

            public String getTotalKwh() {
                return totalKwh;
            }

            public void setTotalKwh(String totalKwh) {
                this.totalKwh = totalKwh;
            }

            public Object getAccurateTotalKwh() {
                return accurateTotalKwh;
            }

            public void setAccurateTotalKwh(Object accurateTotalKwh) {
                this.accurateTotalKwh = accurateTotalKwh;
            }

            public List<StageKwhBean> getStageKwh() {
                return stageKwh;
            }

            public void setStageKwh(List<StageKwhBean> stageKwh) {
                this.stageKwh = stageKwh;
            }

            public static class StageKwhBean {
                /**
                 * key : 01日
                 * value : 10.98
                 */

                private String key;
                private String value;

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
