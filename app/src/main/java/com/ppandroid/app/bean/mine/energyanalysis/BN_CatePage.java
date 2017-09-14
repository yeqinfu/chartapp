package com.ppandroid.app.bean.mine.energyanalysis;

import com.ppandroid.im.bean.BaseBody;

import java.util.List;

/**
 * Created by yeqinfu on 2017/8/31.
 */

public class BN_CatePage  extends BaseBody{

    /**
     * message : {"analysisCateParamDtoList":[{"cateName":"空调系统","cateRatio":"0%","cateKwh":"0","leaf":false},{"cateName":"制冷机","cateRatio":"100%","cateKwh":"46440","leaf":true},{"cateName":"高压冷却泵","cateRatio":"0%","cateKwh":"0","leaf":true},{"cateName":"冷冻泵","cateRatio":"0%","cateKwh":"0","leaf":true},{"cateName":"冷却塔","cateRatio":"0%","cateKwh":"0","leaf":true},{"cateName":"冷干机","cateRatio":"0%","cateKwh":"0","leaf":true},{"cateName":"高压式离心式制冷机","cateRatio":"0%","cateKwh":"0","leaf":true},{"cateName":"高压离心式制冷机","cateRatio":"0%","cateKwh":"0","leaf":true},{"cateName":"高压冷却水泵","cateRatio":"0%","cateKwh":"0","leaf":true},{"cateName":"高压寿力螺杆空压机","cateRatio":"0%","cateKwh":"0","leaf":true},{"cateName":"空调系统冷却泵","cateRatio":"0%","cateKwh":"0","leaf":true},{"cateName":"叶钦富分项","cateRatio":"0%","cateKwh":"0","leaf":true},{"cateName":"哈","cateRatio":"0%","cateKwh":"0","leaf":true},{"cateName":"哈哈","cateRatio":"0%","cateKwh":"0","leaf":true},{"cateName":"哈哈","cateRatio":"0%","cateKwh":"0","leaf":true}],"analysisCateSum":"46440"}
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
         * analysisCateParamDtoList : [{"cateName":"空调系统","cateRatio":"0%","cateKwh":"0","leaf":false},{"cateName":"制冷机","cateRatio":"100%","cateKwh":"46440","leaf":true},{"cateName":"高压冷却泵","cateRatio":"0%","cateKwh":"0","leaf":true},{"cateName":"冷冻泵","cateRatio":"0%","cateKwh":"0","leaf":true},{"cateName":"冷却塔","cateRatio":"0%","cateKwh":"0","leaf":true},{"cateName":"冷干机","cateRatio":"0%","cateKwh":"0","leaf":true},{"cateName":"高压式离心式制冷机","cateRatio":"0%","cateKwh":"0","leaf":true},{"cateName":"高压离心式制冷机","cateRatio":"0%","cateKwh":"0","leaf":true},{"cateName":"高压冷却水泵","cateRatio":"0%","cateKwh":"0","leaf":true},{"cateName":"高压寿力螺杆空压机","cateRatio":"0%","cateKwh":"0","leaf":true},{"cateName":"空调系统冷却泵","cateRatio":"0%","cateKwh":"0","leaf":true},{"cateName":"叶钦富分项","cateRatio":"0%","cateKwh":"0","leaf":true},{"cateName":"哈","cateRatio":"0%","cateKwh":"0","leaf":true},{"cateName":"哈哈","cateRatio":"0%","cateKwh":"0","leaf":true},{"cateName":"哈哈","cateRatio":"0%","cateKwh":"0","leaf":true}]
         * analysisCateSum : 46440
         */

        private String analysisCateSum;
        private List<AnalysisCateParamDtoListBean> analysisCateParamDtoList;

        public String getAnalysisCateSum() {
            return analysisCateSum;
        }

        public void setAnalysisCateSum(String analysisCateSum) {
            this.analysisCateSum = analysisCateSum;
        }

        public List<AnalysisCateParamDtoListBean> getAnalysisCateParamDtoList() {
            return analysisCateParamDtoList;
        }

        public void setAnalysisCateParamDtoList(List<AnalysisCateParamDtoListBean> analysisCateParamDtoList) {
            this.analysisCateParamDtoList = analysisCateParamDtoList;
        }

        public static class AnalysisCateParamDtoListBean {
            /**
             * cateName : 空调系统
             * cateRatio : 0%
             * cateKwh : 0
             * leaf : false
             */

            private String cateName;
            private String cateRatio;
            private String cateKwh;
            private boolean leaf;
            private long id;
            private String cateId;

            public String getCateId() {
                return cateId;
            }

            public void setCateId(String cateId) {
                this.cateId = cateId;
            }

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public String getCateName() {
                return cateName;
            }

            public void setCateName(String cateName) {
                this.cateName = cateName;
            }

            public String getCateRatio() {
                return cateRatio;
            }

            public void setCateRatio(String cateRatio) {
                this.cateRatio = cateRatio;
            }

            public String getCateKwh() {
                return cateKwh;
            }

            public void setCateKwh(String cateKwh) {
                this.cateKwh = cateKwh;
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
