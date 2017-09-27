/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.bean.mine.energyanalysis;

import com.ppandroid.im.bean.BaseBody;

import java.util.List;

/**
 * Created by yeqinfu on 2017/9/23.
 */

public class BN_TongPage extends BaseBody {

    /**
     * message : {"thisAnalysisDeviceDetailDto":{"averageKwh":"平均：0.49kwh","accurateAverageKwh":"0.49","totalKwh":"4.94","accurateTotalKwh":null,"stageKwh":[{"key":"00时","value":"0.00"},{"key":"01时","value":"0.00"},{"key":"02时","value":"0.00"},{"key":"03时","value":"0.00"},{"key":"04时","value":"0.00"},{"key":"05时","value":"0.00"},{"key":"06时","value":"0.00"},{"key":"07时","value":"0.04"},{"key":"08时","value":"4.90"},{"key":"09时","value":"0.00"}]},"tongbiAnalysisDeviceDetailDto":{"averageKwh":"平均：0.37kwh","accurateAverageKwh":"0.37","totalKwh":"8.91","accurateTotalKwh":null,"stageKwh":[{"key":"00时","value":"0.00"},{"key":"01时","value":"0.00"},{"key":"02时","value":"0.00"},{"key":"03时","value":"0.00"},{"key":"04时","value":"0.00"},{"key":"05时","value":"0.00"},{"key":"06时","value":"0.00"},{"key":"07时","value":"0.00"},{"key":"08时","value":"0.49"},{"key":"09时","value":"0.50"},{"key":"10时","value":"0.49"},{"key":"11时","value":"1.19"},{"key":"12时","value":"1.23"},{"key":"13时","value":"1.23"},{"key":"14时","value":"0.82"},{"key":"15时","value":"0.50"},{"key":"16时","value":"0.49"},{"key":"17时","value":"0.49"},{"key":"18时","value":"0.50"},{"key":"19时","value":"0.50"},{"key":"20时","value":"0.48"},{"key":"21时","value":"0.00"},{"key":"22时","value":"0.00"},{"key":"23时","value":"0.00"}]},"tongbiRatio":"32.43%"}
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
         * thisAnalysisDeviceDetailDto : {"averageKwh":"平均：0.49kwh","accurateAverageKwh":"0.49","totalKwh":"4.94","accurateTotalKwh":null,"stageKwh":[{"key":"00时","value":"0.00"},{"key":"01时","value":"0.00"},{"key":"02时","value":"0.00"},{"key":"03时","value":"0.00"},{"key":"04时","value":"0.00"},{"key":"05时","value":"0.00"},{"key":"06时","value":"0.00"},{"key":"07时","value":"0.04"},{"key":"08时","value":"4.90"},{"key":"09时","value":"0.00"}]}
         * tongbiAnalysisDeviceDetailDto : {"averageKwh":"平均：0.37kwh","accurateAverageKwh":"0.37","totalKwh":"8.91","accurateTotalKwh":null,"stageKwh":[{"key":"00时","value":"0.00"},{"key":"01时","value":"0.00"},{"key":"02时","value":"0.00"},{"key":"03时","value":"0.00"},{"key":"04时","value":"0.00"},{"key":"05时","value":"0.00"},{"key":"06时","value":"0.00"},{"key":"07时","value":"0.00"},{"key":"08时","value":"0.49"},{"key":"09时","value":"0.50"},{"key":"10时","value":"0.49"},{"key":"11时","value":"1.19"},{"key":"12时","value":"1.23"},{"key":"13时","value":"1.23"},{"key":"14时","value":"0.82"},{"key":"15时","value":"0.50"},{"key":"16时","value":"0.49"},{"key":"17时","value":"0.49"},{"key":"18时","value":"0.50"},{"key":"19时","value":"0.50"},{"key":"20时","value":"0.48"},{"key":"21时","value":"0.00"},{"key":"22时","value":"0.00"},{"key":"23时","value":"0.00"}]}
         * tongbiRatio : 32.43%
         */

        private AnalysisDeviceDetailDtoBean thisAnalysisDeviceDetailDto;
        private AnalysisDeviceDetailDtoBean tongbiAnalysisDeviceDetailDto;
        private String tongbiRatio;

        public AnalysisDeviceDetailDtoBean getThisAnalysisDeviceDetailDto() {
            return thisAnalysisDeviceDetailDto;
        }

        public void setThisAnalysisDeviceDetailDto(AnalysisDeviceDetailDtoBean thisAnalysisDeviceDetailDto) {
            this.thisAnalysisDeviceDetailDto = thisAnalysisDeviceDetailDto;
        }

        public AnalysisDeviceDetailDtoBean getTongbiAnalysisDeviceDetailDto() {
            return tongbiAnalysisDeviceDetailDto;
        }

        public void setTongbiAnalysisDeviceDetailDto(AnalysisDeviceDetailDtoBean tongbiAnalysisDeviceDetailDto) {
            this.tongbiAnalysisDeviceDetailDto = tongbiAnalysisDeviceDetailDto;
        }

        public String getTongbiRatio() {
            return tongbiRatio;
        }

        public void setTongbiRatio(String tongbiRatio) {
            this.tongbiRatio = tongbiRatio;
        }

        public static class AnalysisDeviceDetailDtoBean {
            /**
             * averageKwh : 平均：0.49kwh
             * accurateAverageKwh : 0.49
             * totalKwh : 4.94
             * accurateTotalKwh : null
             * stageKwh : [{"key":"00时","value":"0.00"},{"key":"01时","value":"0.00"},{"key":"02时","value":"0.00"},{"key":"03时","value":"0.00"},{"key":"04时","value":"0.00"},{"key":"05时","value":"0.00"},{"key":"06时","value":"0.00"},{"key":"07时","value":"0.04"},{"key":"08时","value":"4.90"},{"key":"09时","value":"0.00"}]
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
                 * key : 00时
                 * value : 0.00
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
