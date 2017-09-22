package com.ppandroid.app.bean.mine.energyanalysis;

import com.ppandroid.im.bean.BaseBody;

import java.util.List;

/**
 * Created by yeqinfu on 2017/9/22.
 */

public class BN_HuanPageWeek extends BaseBody {

    /**
     * message : {"thisAnalysisWeekParamDto":{"weekAverage":"59.63","weekSum":"298.17","deviceSumString":["63.91","63.86","63.81","62.27","44.32"]},"huanbiAnalysisWeekParamDto":{"weekAverage":"52.98","weekSum":"370.87","deviceSumString":["42.16","31.94","43.73","63.68","63.87","61.49","64.00"]},"huanbiRatio":"12.55%"}
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
         * thisAnalysisWeekParamDto : {"weekAverage":"59.63","weekSum":"298.17","deviceSumString":["63.91","63.86","63.81","62.27","44.32"]}
         * huanbiAnalysisWeekParamDto : {"weekAverage":"52.98","weekSum":"370.87","deviceSumString":["42.16","31.94","43.73","63.68","63.87","61.49","64.00"]}
         * huanbiRatio : 12.55%
         */

        private AnalysisWeekParamDtoBean thisAnalysisWeekParamDto;
        private AnalysisWeekParamDtoBean huanbiAnalysisWeekParamDto;
        private String huanbiRatio;

        public AnalysisWeekParamDtoBean getThisAnalysisWeekParamDto() {
            return thisAnalysisWeekParamDto;
        }

        public void setThisAnalysisWeekParamDto(AnalysisWeekParamDtoBean thisAnalysisWeekParamDto) {
            this.thisAnalysisWeekParamDto = thisAnalysisWeekParamDto;
        }

        public AnalysisWeekParamDtoBean getHuanbiAnalysisWeekParamDto() {
            return huanbiAnalysisWeekParamDto;
        }

        public void setHuanbiAnalysisWeekParamDto(AnalysisWeekParamDtoBean huanbiAnalysisWeekParamDto) {
            this.huanbiAnalysisWeekParamDto = huanbiAnalysisWeekParamDto;
        }

        public String getHuanbiRatio() {
            return huanbiRatio;
        }

        public void setHuanbiRatio(String huanbiRatio) {
            this.huanbiRatio = huanbiRatio;
        }

        public static class AnalysisWeekParamDtoBean {
            /**
             * weekAverage : 59.63
             * weekSum : 298.17
             * deviceSumString : ["63.91","63.86","63.81","62.27","44.32"]
             */

            private String weekAverage;
            private String weekSum;
            private List<String> deviceSumString;

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
}
