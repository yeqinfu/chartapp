package com.ppandroid.app.bean.mine.energyanalysis;

import com.ppandroid.im.bean.BaseBody;

import java.util.List;

/**
 * Created by yeqinfu on 2017/8/29.
 */

public class BN_EnergyAnalysis extends BaseBody {

    /**
     * message : {"weekAverage":"912","weekSum":"3648","deviceSumString":["2108","1527","9","4","0","0","0"]}
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
         * weekAverage : 912
         * weekSum : 3648
         * deviceSumString : ["2108","1527","9","4","0","0","0"]
         */

        private String weekAverage;
        private String weekSum;
        private List<String> deviceSumString;
        private String accurateAverageKwh;
        private String averageKwh;

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

        public String getWeekAverage() {
            return weekAverage;
        }

        public void setWeekAverage(String weekAverage) {
            this.weekAverage = weekAverage;
        }

        public String getWeekSum() {
            return weekSum;
        }

        public void setWeekSum(String weekSum) {
            this.weekSum = weekSum;
        }

        public List<String> getDeviceSumString() {
            return deviceSumString;
        }

        public void setDeviceSumString(List<String> deviceSumString) {
            this.deviceSumString = deviceSumString;
        }
    }
}
