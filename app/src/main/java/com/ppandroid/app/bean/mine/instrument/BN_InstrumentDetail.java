/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.bean.mine.instrument;

import com.ppandroid.im.bean.BaseBody;

/**
 * Created by yeqinfu on 2017/8/23.
 */

public class BN_InstrumentDetail extends BaseBody {

    /**
     * message : {"id":1,"deleteStatus":false,"version":0,"createTime":null,"createBy":null,"createById":null,"lastModifyTime":"2017-08-09 17:13:11","lastModifyBy":null,"lastModifyById":null,"companyId":1,"deviceId":1,"recordTime":1502701985000,"ia":12,"ib":0,"ic":0,"uab":12,"ubc":0,"uca":0,"ps":1,"psa":1,"psb":1,"psc":1,"qs":1,"qsa":1,"qsb":1,"qsc":1,"pFs":0,"pFsa":1,"pFsb":0,"pFsc":0,"pKwh":241830,"qKwh":543.6,"frequency":1}
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
         * id : 1
         * deleteStatus : false
         * version : 0
         * createTime : null
         * createBy : null
         * createById : null
         * lastModifyTime : 2017-08-09 17:13:11
         * lastModifyBy : null
         * lastModifyById : null
         * companyId : 1
         * deviceId : 1
         * recordTime : 1502701985000
         * ia : 12.0
         * ib : 0.0
         * ic : 0.0
         * uab : 12.0
         * ubc : 0.0
         * uca : 0.0
         * ps : 1.0
         * psa : 1.0
         * psb : 1.0
         * psc : 1.0
         * qs : 1.0
         * qsa : 1.0
         * qsb : 1.0
         * qsc : 1.0
         * pFs : 0.0
         * pFsa : 1.0
         * pFsb : 0.0
         * pFsc : 0.0
         * pKwh : 241830.0
         * qKwh : 543.6
         * frequency : 1.0
         */

        private int id;
        private boolean deleteStatus;
        private int version;
        private Object createTime;
        private Object createBy;
        private Object createById;
        private String lastModifyTime;
        private Object lastModifyBy;
        private Object lastModifyById;
        private int companyId;
        private int deviceId;
        private long recordTime;
        private double ia;
        private double ib;
        private double ic;
        private double uab;
        private double ubc;
        private double uca;
        private double ps;
        private double psa;
        private double psb;
        private double psc;
        private double qs;
        private double qsa;
        private double qsb;
        private double qsc;
        private double pFs;
        private double pFsa;
        private double pFsb;
        private double pFsc;
        private double pKwh;
        private double qKwh;
        private double frequency;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean isDeleteStatus() {
            return deleteStatus;
        }

        public void setDeleteStatus(boolean deleteStatus) {
            this.deleteStatus = deleteStatus;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        public Object getCreateBy() {
            return createBy;
        }

        public void setCreateBy(Object createBy) {
            this.createBy = createBy;
        }

        public Object getCreateById() {
            return createById;
        }

        public void setCreateById(Object createById) {
            this.createById = createById;
        }

        public String getLastModifyTime() {
            return lastModifyTime;
        }

        public void setLastModifyTime(String lastModifyTime) {
            this.lastModifyTime = lastModifyTime;
        }

        public Object getLastModifyBy() {
            return lastModifyBy;
        }

        public void setLastModifyBy(Object lastModifyBy) {
            this.lastModifyBy = lastModifyBy;
        }

        public Object getLastModifyById() {
            return lastModifyById;
        }

        public void setLastModifyById(Object lastModifyById) {
            this.lastModifyById = lastModifyById;
        }

        public int getCompanyId() {
            return companyId;
        }

        public void setCompanyId(int companyId) {
            this.companyId = companyId;
        }

        public int getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(int deviceId) {
            this.deviceId = deviceId;
        }

        public long getRecordTime() {
            return recordTime;
        }

        public void setRecordTime(long recordTime) {
            this.recordTime = recordTime;
        }

        public double getIa() {
            return ia;
        }

        public void setIa(double ia) {
            this.ia = ia;
        }

        public double getIb() {
            return ib;
        }

        public void setIb(double ib) {
            this.ib = ib;
        }

        public double getIc() {
            return ic;
        }

        public void setIc(double ic) {
            this.ic = ic;
        }

        public double getUab() {
            return uab;
        }

        public void setUab(double uab) {
            this.uab = uab;
        }

        public double getUbc() {
            return ubc;
        }

        public void setUbc(double ubc) {
            this.ubc = ubc;
        }

        public double getUca() {
            return uca;
        }

        public void setUca(double uca) {
            this.uca = uca;
        }

        public double getPs() {
            return ps;
        }

        public void setPs(double ps) {
            this.ps = ps;
        }

        public double getPsa() {
            return psa;
        }

        public void setPsa(double psa) {
            this.psa = psa;
        }

        public double getPsb() {
            return psb;
        }

        public void setPsb(double psb) {
            this.psb = psb;
        }

        public double getPsc() {
            return psc;
        }

        public void setPsc(double psc) {
            this.psc = psc;
        }

        public double getQs() {
            return qs;
        }

        public void setQs(double qs) {
            this.qs = qs;
        }

        public double getQsa() {
            return qsa;
        }

        public void setQsa(double qsa) {
            this.qsa = qsa;
        }

        public double getQsb() {
            return qsb;
        }

        public void setQsb(double qsb) {
            this.qsb = qsb;
        }

        public double getQsc() {
            return qsc;
        }

        public void setQsc(double qsc) {
            this.qsc = qsc;
        }

        public double getPFs() {
            return pFs;
        }

        public void setPFs(double pFs) {
            this.pFs = pFs;
        }

        public double getPFsa() {
            return pFsa;
        }

        public void setPFsa(double pFsa) {
            this.pFsa = pFsa;
        }

        public double getPFsb() {
            return pFsb;
        }

        public void setPFsb(double pFsb) {
            this.pFsb = pFsb;
        }

        public double getPFsc() {
            return pFsc;
        }

        public void setPFsc(double pFsc) {
            this.pFsc = pFsc;
        }

        public double getPKwh() {
            return pKwh;
        }

        public void setPKwh(double pKwh) {
            this.pKwh = pKwh;
        }

        public double getQKwh() {
            return qKwh;
        }

        public void setQKwh(double qKwh) {
            this.qKwh = qKwh;
        }

        public double getFrequency() {
            return frequency;
        }

        public void setFrequency(double frequency) {
            this.frequency = frequency;
        }
    }
}
