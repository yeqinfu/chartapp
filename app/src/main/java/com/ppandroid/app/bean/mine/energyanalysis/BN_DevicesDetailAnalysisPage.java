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

public class BN_DevicesDetailAnalysisPage extends BaseBody {


    /**
     * message : {"averageKwh":"25.04万kwh","stageKwh":[{"key":"2017年","value":"25.04万"}]}
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
         * averageKwh : 25.04万kwh
         * stageKwh : [{"key":"2017年","value":"25.04万"}]
         */

        private String totalKwh;
        private String accurateAverageKwh;
        private String averageKwh;
        private List<StageKwhBean> stageKwh;


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
             * key : 2017年
             * value : 25.04万
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
