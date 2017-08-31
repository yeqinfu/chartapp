package com.ppandroid.app.bean.mine.energyanalysis;

import com.ppandroid.im.bean.BaseBody;

import java.util.List;

/**
 * Created by yeqinfu on 2017/8/31.
 */

public class BN_InstrumentPage extends BaseBody {

    /**
     * message : {"analysisInstrumentParamDtoList":[{"instrumentName":"CH01-1约克离心式制冷机","instrumentRatio":"50%","instrumentKwh":"23202"},{"instrumentName":"CH01-2约克离心式制冷机","instrumentRatio":"50%","instrumentKwh":"23243"},{"instrumentName":"哈哈","instrumentRatio":"50%","instrumentKwh":"23202"},{"instrumentName":"哈哈","instrumentRatio":"50%","instrumentKwh":"23202"},{"instrumentName":"哈哈","instrumentRatio":"50%","instrumentKwh":"23202"},{"instrumentName":"我很6","instrumentRatio":"0%","instrumentKwh":"0"}],"analysisDeviceSum":"46445"}
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
         * analysisInstrumentParamDtoList : [{"instrumentName":"CH01-1约克离心式制冷机","instrumentRatio":"50%","instrumentKwh":"23202"},{"instrumentName":"CH01-2约克离心式制冷机","instrumentRatio":"50%","instrumentKwh":"23243"},{"instrumentName":"哈哈","instrumentRatio":"50%","instrumentKwh":"23202"},{"instrumentName":"哈哈","instrumentRatio":"50%","instrumentKwh":"23202"},{"instrumentName":"哈哈","instrumentRatio":"50%","instrumentKwh":"23202"},{"instrumentName":"我很6","instrumentRatio":"0%","instrumentKwh":"0"}]
         * analysisDeviceSum : 46445
         */

        private String analysisDeviceSum;
        private List<AnalysisInstrumentParamDtoListBean> analysisInstrumentParamDtoList;

        public String getAnalysisDeviceSum() {
            return analysisDeviceSum;
        }

        public void setAnalysisDeviceSum(String analysisDeviceSum) {
            this.analysisDeviceSum = analysisDeviceSum;
        }

        public List<AnalysisInstrumentParamDtoListBean> getAnalysisInstrumentParamDtoList() {
            return analysisInstrumentParamDtoList;
        }

        public void setAnalysisInstrumentParamDtoList(List<AnalysisInstrumentParamDtoListBean> analysisInstrumentParamDtoList) {
            this.analysisInstrumentParamDtoList = analysisInstrumentParamDtoList;
        }

        public static class AnalysisInstrumentParamDtoListBean {
            /**
             * instrumentName : CH01-1约克离心式制冷机
             * instrumentRatio : 50%
             * instrumentKwh : 23202
             */

            private String instrumentName;
            private String instrumentRatio;
            private String instrumentKwh;

            public String getInstrumentName() {
                return instrumentName;
            }

            public void setInstrumentName(String instrumentName) {
                this.instrumentName = instrumentName;
            }

            public String getInstrumentRatio() {
                return instrumentRatio;
            }

            public void setInstrumentRatio(String instrumentRatio) {
                this.instrumentRatio = instrumentRatio;
            }

            public String getInstrumentKwh() {
                return instrumentKwh;
            }

            public void setInstrumentKwh(String instrumentKwh) {
                this.instrumentKwh = instrumentKwh;
            }
        }
    }
}
