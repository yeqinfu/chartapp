package com.ppandroid.app.bean.mine.energyanalysis;

import com.ppandroid.im.bean.BaseBody;

import java.util.List;

/**
 * Created by yeqinfu on 2017/9/14.
 */

public class BN_CateDetailAnalysisPage extends BaseBody {

    /**
     * message : {"averageKwh":"0.68kwh","stageKwh":[{"key":"00时","value":"0.02"},{"key":"01时","value":"0.02"},{"key":"02时","value":"0.02"},{"key":"03时","value":"0.02"},{"key":"04时","value":"0.02"},{"key":"05时","value":"0.02"},{"key":"06时","value":"0.02"},{"key":"07时","value":"0.02"},{"key":"08时","value":"0.50"},{"key":"09时","value":"0.50"},{"key":"10时","value":"0.50"},{"key":"11时","value":"0.98"},{"key":"12时","value":"1.50"},{"key":"13时","value":"1.52"},{"key":"14时","value":"1.51"},{"key":"15时","value":"1.47"},{"key":"16时","value":"1.50"},{"key":"17时","value":"1.51"},{"key":"18时","value":"1.50"},{"key":"19时","value":"1.51"},{"key":"20时","value":"1.51"},{"key":"21时","value":"0.03"},{"key":"22时","value":"0.03"},{"key":"23时","value":"0.03"}]}
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
         * averageKwh : 0.68kwh
         * stageKwh : [{"key":"00时","value":"0.02"},{"key":"01时","value":"0.02"},{"key":"02时","value":"0.02"},{"key":"03时","value":"0.02"},{"key":"04时","value":"0.02"},{"key":"05时","value":"0.02"},{"key":"06时","value":"0.02"},{"key":"07时","value":"0.02"},{"key":"08时","value":"0.50"},{"key":"09时","value":"0.50"},{"key":"10时","value":"0.50"},{"key":"11时","value":"0.98"},{"key":"12时","value":"1.50"},{"key":"13时","value":"1.52"},{"key":"14时","value":"1.51"},{"key":"15时","value":"1.47"},{"key":"16时","value":"1.50"},{"key":"17时","value":"1.51"},{"key":"18时","value":"1.50"},{"key":"19时","value":"1.51"},{"key":"20时","value":"1.51"},{"key":"21时","value":"0.03"},{"key":"22时","value":"0.03"},{"key":"23时","value":"0.03"}]
         */

        private String averageKwh;
        private String accurateAverageKwh;
        private String totalKwh;

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

        private List<StageKwhBean> stageKwh;

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
             * key : 00时
             * value : 0.02
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
