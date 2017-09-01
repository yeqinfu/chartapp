package com.ppandroid.app.bean.news;

import com.ppandroid.im.bean.BaseBody;

import java.util.List;

/**
 * Created by yeqinfu on 2017/9/1.
 */

public class BN_EnergyList extends BaseBody {

    /**
     * message : {"content":[{"id":2,"deleteStatus":false,"version":0,"createTime":null,"createBy":null,"createById":null,"lastModifyTime":null,"lastModifyBy":null,"lastModifyById":null,"recordDate":1503384300000,"totalKwh":"32677","type":1,"companyId":1},{"id":1,"deleteStatus":false,"version":0,"createTime":null,"createBy":null,"createById":null,"lastModifyTime":null,"lastModifyBy":null,"lastModifyById":null,"recordDate":1503308689000,"totalKwh":"98796","type":1,"companyId":1}],"last":true,"totalPages":1,"firstPage":true,"lastPage":true,"totalElements":2,"size":10,"number":0,"sort":[{"direction":"DESC","property":"id","ignoreCase":false,"nullHandling":"NATIVE","ascending":false}],"first":true,"numberOfElements":2}
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
         * content : [{"id":2,"deleteStatus":false,"version":0,"createTime":null,"createBy":null,"createById":null,"lastModifyTime":null,"lastModifyBy":null,"lastModifyById":null,"recordDate":1503384300000,"totalKwh":"32677","type":1,"companyId":1},{"id":1,"deleteStatus":false,"version":0,"createTime":null,"createBy":null,"createById":null,"lastModifyTime":null,"lastModifyBy":null,"lastModifyById":null,"recordDate":1503308689000,"totalKwh":"98796","type":1,"companyId":1}]
         * last : true
         * totalPages : 1
         * firstPage : true
         * lastPage : true
         * totalElements : 2
         * size : 10
         * number : 0
         * sort : [{"direction":"DESC","property":"id","ignoreCase":false,"nullHandling":"NATIVE","ascending":false}]
         * first : true
         * numberOfElements : 2
         */

        private boolean last;
        private int totalPages;
        private boolean firstPage;
        private boolean lastPage;
        private int totalElements;
        private int size;
        private int number;
        private boolean first;
        private int numberOfElements;
        private List<ContentBean> content;
        private List<SortBean> sort;

        public boolean isLast() {
            return last;
        }

        public void setLast(boolean last) {
            this.last = last;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public boolean isFirstPage() {
            return firstPage;
        }

        public void setFirstPage(boolean firstPage) {
            this.firstPage = firstPage;
        }

        public boolean isLastPage() {
            return lastPage;
        }

        public void setLastPage(boolean lastPage) {
            this.lastPage = lastPage;
        }

        public int getTotalElements() {
            return totalElements;
        }

        public void setTotalElements(int totalElements) {
            this.totalElements = totalElements;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public boolean isFirst() {
            return first;
        }

        public void setFirst(boolean first) {
            this.first = first;
        }

        public int getNumberOfElements() {
            return numberOfElements;
        }

        public void setNumberOfElements(int numberOfElements) {
            this.numberOfElements = numberOfElements;
        }

        public List<ContentBean> getContent() {
            return content;
        }

        public void setContent(List<ContentBean> content) {
            this.content = content;
        }

        public List<SortBean> getSort() {
            return sort;
        }

        public void setSort(List<SortBean> sort) {
            this.sort = sort;
        }

        public static class ContentBean {
            /**
             * id : 2
             * deleteStatus : false
             * version : 0
             * createTime : null
             * createBy : null
             * createById : null
             * lastModifyTime : null
             * lastModifyBy : null
             * lastModifyById : null
             * recordDate : 1503384300000
             * totalKwh : 32677
             * type : 1
             * companyId : 1
             */

            private int id;
            private boolean deleteStatus;
            private int version;
            private String createTime;
            private Object createBy;
            private Object createById;
            private Object lastModifyTime;
            private Object lastModifyBy;
            private Object lastModifyById;
            private long recordDate;
            private String totalKwh;
            private int type;
            private int companyId;

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

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
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

            public Object getLastModifyTime() {
                return lastModifyTime;
            }

            public void setLastModifyTime(Object lastModifyTime) {
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

            public long getRecordDate() {
                return recordDate;
            }

            public void setRecordDate(long recordDate) {
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

            public int getCompanyId() {
                return companyId;
            }

            public void setCompanyId(int companyId) {
                this.companyId = companyId;
            }
        }

        public static class SortBean {
            /**
             * direction : DESC
             * property : id
             * ignoreCase : false
             * nullHandling : NATIVE
             * ascending : false
             */

            private String direction;
            private String property;
            private boolean ignoreCase;
            private String nullHandling;
            private boolean ascending;

            public String getDirection() {
                return direction;
            }

            public void setDirection(String direction) {
                this.direction = direction;
            }

            public String getProperty() {
                return property;
            }

            public void setProperty(String property) {
                this.property = property;
            }

            public boolean isIgnoreCase() {
                return ignoreCase;
            }

            public void setIgnoreCase(boolean ignoreCase) {
                this.ignoreCase = ignoreCase;
            }

            public String getNullHandling() {
                return nullHandling;
            }

            public void setNullHandling(String nullHandling) {
                this.nullHandling = nullHandling;
            }

            public boolean isAscending() {
                return ascending;
            }

            public void setAscending(boolean ascending) {
                this.ascending = ascending;
            }
        }
    }
}
