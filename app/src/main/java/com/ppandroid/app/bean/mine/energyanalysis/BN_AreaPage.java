/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.bean.mine.energyanalysis;

import com.ppandroid.im.bean.BaseBody;

import java.util.List;

/**
 * Created by yeqinfu on 2017/8/31.
 */

public class BN_AreaPage extends BaseBody {


    /**
     * message : {"analysisAreaParamDtoList":[{"areaName":"高压动力站","areaRatio":"100%","areaKwh":"17","leaf":false},{"areaName":"顶级区域","areaRatio":"0%","areaKwh":"0","leaf":false}],"analysisAreaSum":"17"}
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
         * analysisAreaParamDtoList : [{"areaName":"高压动力站","areaRatio":"100%","areaKwh":"17","leaf":false},{"areaName":"顶级区域","areaRatio":"0%","areaKwh":"0","leaf":false}]
         * analysisAreaSum : 17
         */

        private String analysisAreaSum;
        private String dateString;
        private List<AnalysisAreaParamDtoListBean> analysisAreaParamDtoList;

        public String getDateString() {
            return dateString;
        }

        public void setDateString(String dateString) {
            this.dateString = dateString;
        }

        public String getAnalysisAreaSum() {
            return analysisAreaSum;
        }

        public void setAnalysisAreaSum(String analysisAreaSum) {
            this.analysisAreaSum = analysisAreaSum;
        }

        public List<AnalysisAreaParamDtoListBean> getAnalysisAreaParamDtoList() {
            return analysisAreaParamDtoList;
        }

        public void setAnalysisAreaParamDtoList(List<AnalysisAreaParamDtoListBean> analysisAreaParamDtoList) {
            this.analysisAreaParamDtoList = analysisAreaParamDtoList;
        }

        public static class AnalysisAreaParamDtoListBean {
            /**
             * areaName : 高压动力站
             * areaRatio : 100%
             * areaKwh : 17
             * leaf : false
             */

            private String areaName;
            private String areaRatio;
            private String areaKwh;
            private String areaId;
            private boolean leaf;

            public String getAreaId() {
                return areaId;
            }

            public void setAreaId(String areaId) {
                this.areaId = areaId;
            }

            public String getAreaName() {
                return areaName;
            }

            public void setAreaName(String areaName) {
                this.areaName = areaName;
            }

            public String getAreaRatio() {
                return areaRatio;
            }

            public void setAreaRatio(String areaRatio) {
                this.areaRatio = areaRatio;
            }

            public String getAreaKwh() {
                return areaKwh;
            }

            public void setAreaKwh(String areaKwh) {
                this.areaKwh = areaKwh;
            }

            public boolean isLeaf() {
                return leaf;
            }

            public void setLeaf(boolean leaf) {
                this.leaf = leaf;
            }
        }
    }
}
