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

public class BN_TongPageWeek extends BaseBody {

    /**
     * message : {"thisAnalysisWeekParamDto":{"weekAverage":"53.79","weekSum":"322.74","deviceSumString":["63.91","63.86","63.81","62.27","63.95","4.94"]},"tongbiAnalysisWeekParamDto":{"weekAverage":"523.03","weekSum":"3661.23","deviceSumString":["2104.00","1522.74","8.91","6.40","6.40","6.38","6.40"]},"tongbiRatio":"-89.72%"}
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
         * thisAnalysisWeekParamDto : {"weekAverage":"53.79","weekSum":"322.74","deviceSumString":["63.91","63.86","63.81","62.27","63.95","4.94"]}
         * tongbiAnalysisWeekParamDto : {"weekAverage":"523.03","weekSum":"3661.23","deviceSumString":["2104.00","1522.74","8.91","6.40","6.40","6.38","6.40"]}
         * tongbiRatio : -89.72%
         */

        private AnalysisWeekParamDtoBean thisAnalysisWeekParamDto;
        private AnalysisWeekParamDtoBean tongbiAnalysisWeekParamDto;
        private String tongbiRatio;

        public AnalysisWeekParamDtoBean getThisAnalysisWeekParamDto() {
            return thisAnalysisWeekParamDto;
        }

        public void setThisAnalysisWeekParamDto(AnalysisWeekParamDtoBean thisAnalysisWeekParamDto) {
            this.thisAnalysisWeekParamDto = thisAnalysisWeekParamDto;
        }

        public AnalysisWeekParamDtoBean getTongbiAnalysisWeekParamDto() {
            return tongbiAnalysisWeekParamDto;
        }

        public void setTongbiAnalysisWeekParamDto(AnalysisWeekParamDtoBean tongbiAnalysisWeekParamDto) {
            this.tongbiAnalysisWeekParamDto = tongbiAnalysisWeekParamDto;
        }

        public String getTongbiRatio() {
            return tongbiRatio;
        }

        public void setTongbiRatio(String tongbiRatio) {
            this.tongbiRatio = tongbiRatio;
        }

        public static class AnalysisWeekParamDtoBean {
            /**
             * weekAverage : 53.79
             * weekSum : 322.74
             * deviceSumString : ["63.91","63.86","63.81","62.27","63.95","4.94"]
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
