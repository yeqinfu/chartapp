/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.bean.mine.energyanalysis;

import com.ppandroid.im.bean.BaseBody;

import java.util.List;

/**
 * Created by yeqinfu on 2017/9/18.
 */

public class BN_TotalAnalysis extends BaseBody {

    /**
     * message : {"averageKwh":"平均：5.57万kwh","accurateAverageKwh":"55655.66","totalKwh":"500900.98","accurateTotalKwh":null,"stageKwh":[{"key":"01月","value":"6.63万"},{"key":"02月","value":"6.01万"},{"key":"03月","value":"6.65万"},{"key":"04月","value":"6.41万"},{"key":"05月","value":"6.64万"},{"key":"06月","value":"6.44万"},{"key":"07月","value":"6.63万"},{"key":"08月","value":"4.65万"},{"key":"09月","value":"363.66"},{"key":"10月","value":"0.00"},{"key":"11月","value":"0.00"},{"key":"12月","value":"0.00"}]}
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
         * averageKwh : 平均：5.57万kwh
         * accurateAverageKwh : 55655.66
         * totalKwh : 500900.98
         * accurateTotalKwh : null
         * stageKwh : [{"key":"01月","value":"6.63万"},{"key":"02月","value":"6.01万"},{"key":"03月","value":"6.65万"},{"key":"04月","value":"6.41万"},{"key":"05月","value":"6.64万"},{"key":"06月","value":"6.44万"},{"key":"07月","value":"6.63万"},{"key":"08月","value":"4.65万"},{"key":"09月","value":"363.66"},{"key":"10月","value":"0.00"},{"key":"11月","value":"0.00"},{"key":"12月","value":"0.00"}]
         */

        private String averageKwh;
        private String dateString;
        private String accurateAverageKwh;
        private String totalKwh;
        private Object accurateTotalKwh;
        private List<StageKwhBean> stageKwh;

        public String getDateString() {
            return dateString;
        }

        public void setDateString(String dateString) {
            this.dateString = dateString;
        }

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
             * key : 01月
             * value : 6.63万
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
