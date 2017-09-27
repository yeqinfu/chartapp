/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.bean.mine.energyanalysis;

import com.ppandroid.im.bean.BaseBody;

import java.util.List;

/**
 * Created by yeqinfu on 2017/9/13.
 */

public class BN_InstrumentDetailAnalysisPage extends BaseBody {


    /**
     * message : {"averageKwh":"27828.29万kwh","stageKwh":[{"key":"01月","value":"3.32万"},{"key":"02月","value":"3.00万"},{"key":"03月","value":"3.32万"},{"key":"04月","value":"3.21万"},{"key":"05月","value":"3.31万"},{"key":"06月","value":"3.23万"},{"key":"07月","value":"3.31万"},{"key":"08月","value":"2.32万"},{"key":"09月","value":"176.00"},{"key":"10月","value":"0.00"},{"key":"11月","value":"0.00"},{"key":"12月","value":"0.00"}]}
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
         * averageKwh : 27828.29万kwh
         * stageKwh : [{"key":"01月","value":"3.32万"},{"key":"02月","value":"3.00万"},{"key":"03月","value":"3.32万"},{"key":"04月","value":"3.21万"},{"key":"05月","value":"3.31万"},{"key":"06月","value":"3.23万"},{"key":"07月","value":"3.31万"},{"key":"08月","value":"2.32万"},{"key":"09月","value":"176.00"},{"key":"10月","value":"0.00"},{"key":"11月","value":"0.00"},{"key":"12月","value":"0.00"}]
         */

        private String averageKwh;
        private List<StageKwhBean> stageKwh;
        private String totalKwh;
        private String accurateAverageKwh;

        public String getTotalKwh() {
            return totalKwh;
        }

        public void setTotalKwh(String totalKwh) {
            this.totalKwh = totalKwh;
        }

        public String getAccurateAverageKwh() {
            return accurateAverageKwh;
        }

        public void setAccurateAverageKwh(String accurateAverageKwh) {
            this.accurateAverageKwh = accurateAverageKwh;
        }

        public String getAverageKwh() {
            return averageKwh;
        }

        public void setAverageKwh(String averageKwh) {
            this.averageKwh = averageKwh;
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
             * value : 3.32万
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
