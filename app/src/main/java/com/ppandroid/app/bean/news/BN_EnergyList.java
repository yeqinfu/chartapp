package com.ppandroid.app.bean.news;

import com.ppandroid.im.bean.BaseBody;

import java.util.List;

/**
 * Created by yeqinfu on 2017/9/1.
 */

public class BN_EnergyList extends BaseBody {


    /**
     * message : {"energyConsumptionStatisticsDtoList":[{"id":44,"recordDate":"2017-09-07 12:00:01","totalKwh":"7","type":1,"datePeriod":"2017-09-06"},{"id":43,"recordDate":"2017-09-06 12:00:01","totalKwh":"7","type":1,"datePeriod":"2017-09-05"},{"id":42,"recordDate":"2017-09-05 12:00:01","totalKwh":"7","type":1,"datePeriod":"2017-09-04"},{"id":41,"recordDate":"2017-09-04 12:00:01","totalKwh":"0","type":2,"datePeriod":"2017-08-27~2017-09-03"},{"id":40,"recordDate":"2017-09-04 12:00:01","totalKwh":"7","type":1,"datePeriod":"2017-09-03"},{"id":39,"recordDate":"2017-09-03 12:00:01","totalKwh":"7","type":1,"datePeriod":"2017-09-02"},{"id":38,"recordDate":"2017-09-02 12:00:01","totalKwh":"11","type":1,"datePeriod":"2017-09-01"},{"id":37,"recordDate":"2017-09-01 12:00:01","totalKwh":"46471","type":3,"datePeriod":"2017-08-01~2017-08-31"},{"id":36,"recordDate":"2017-09-01 12:00:01","totalKwh":"17","type":1,"datePeriod":"2017-08-31"},{"id":35,"recordDate":"2017-08-31 12:00:01","totalKwh":"17","type":1,"datePeriod":"2017-08-30"}],"pageNumber":1,"size":10,"totalPages":2}
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
         * energyConsumptionStatisticsDtoList : [{"id":44,"recordDate":"2017-09-07 12:00:01","totalKwh":"7","type":1,"datePeriod":"2017-09-06"},{"id":43,"recordDate":"2017-09-06 12:00:01","totalKwh":"7","type":1,"datePeriod":"2017-09-05"},{"id":42,"recordDate":"2017-09-05 12:00:01","totalKwh":"7","type":1,"datePeriod":"2017-09-04"},{"id":41,"recordDate":"2017-09-04 12:00:01","totalKwh":"0","type":2,"datePeriod":"2017-08-27~2017-09-03"},{"id":40,"recordDate":"2017-09-04 12:00:01","totalKwh":"7","type":1,"datePeriod":"2017-09-03"},{"id":39,"recordDate":"2017-09-03 12:00:01","totalKwh":"7","type":1,"datePeriod":"2017-09-02"},{"id":38,"recordDate":"2017-09-02 12:00:01","totalKwh":"11","type":1,"datePeriod":"2017-09-01"},{"id":37,"recordDate":"2017-09-01 12:00:01","totalKwh":"46471","type":3,"datePeriod":"2017-08-01~2017-08-31"},{"id":36,"recordDate":"2017-09-01 12:00:01","totalKwh":"17","type":1,"datePeriod":"2017-08-31"},{"id":35,"recordDate":"2017-08-31 12:00:01","totalKwh":"17","type":1,"datePeriod":"2017-08-30"}]
         * pageNumber : 1
         * size : 10
         * totalPages : 2
         */

        private int pageNumber;
        private int size;
        private int totalPages;
        private List<EnergyConsumptionStatisticsDtoListBean> energyConsumptionStatisticsDtoList;

        public int getPageNumber() {
            return pageNumber;
        }

        public void setPageNumber(int pageNumber) {
            this.pageNumber = pageNumber;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public List<EnergyConsumptionStatisticsDtoListBean> getEnergyConsumptionStatisticsDtoList() {
            return energyConsumptionStatisticsDtoList;
        }

        public void setEnergyConsumptionStatisticsDtoList(List<EnergyConsumptionStatisticsDtoListBean> energyConsumptionStatisticsDtoList) {
            this.energyConsumptionStatisticsDtoList = energyConsumptionStatisticsDtoList;
        }

        public static class EnergyConsumptionStatisticsDtoListBean {
            /**
             * id : 44
             * recordDate : 2017-09-07 12:00:01
             * totalKwh : 7
             * type : 1
             * datePeriod : 2017-09-06
             */

            private int id;
            private String recordDate;
            private String totalKwh;
            private int type;
            private String datePeriod;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getRecordDate() {
                return recordDate;
            }

            public void setRecordDate(String recordDate) {
                this.recordDate = recordDate;
            }

            public String getTotalKwh() {
                return totalKwh;
            }

            public void setTotalKwh(String totalKwh) {
                this.totalKwh = totalKwh;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getDatePeriod() {
                return datePeriod;
            }

            public void setDatePeriod(String datePeriod) {
                this.datePeriod = datePeriod;
            }
        }
    }
}
