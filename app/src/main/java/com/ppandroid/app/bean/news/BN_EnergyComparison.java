/*
 * Created by yeqinfu on 17-12-26 上午11:47
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.bean.news;

import com.ppandroid.im.bean.BaseBody;

import java.util.List;

/**
 * Created by yeqinfu on 2017/12/26.
 */

public class BN_EnergyComparison extends BaseBody {

    /**
     * message : {"searchText":"","pageNo":1,"pageSize":10,"orderBy":null,"orderDir":null,"orderType":null,"countTotal":true,"sort":{"orders":[]},"searchDebug":false,"result":[{"id":13,"deleteStatus":false,"version":0,"createTime":"2017-12-26 11:41:02","createBy":null,"createById":null,"lastModifyTime":"2017-12-26 11:41:02","lastModifyBy":null,"lastModifyById":null,"isNotification":true,"title":"您好，您有一条近三日能耗对比消息","alert":"2017-12-26","extraParamJsonString":"{\"companyId\":\"1\",\"type\":\"3\"}","platform":1,"iosApns":false,"sendno":1,"timeToLive":86400,"aliasJsonString":"[]","tagsJsonString":"[\"1\"]","companyId":1,"type":3,"tags":["1"],"alias":[],"extraParamMap":{"companyId":"1","type":"3"}},{"id":14,"deleteStatus":false,"version":0,"createTime":"2017-12-26 11:41:02","createBy":null,"createById":null,"lastModifyTime":"2017-12-26 11:41:02","lastModifyBy":null,"lastModifyById":null,"isNotification":true,"title":"您好，您有一条近三日能耗对比消息","alert":"2017-12-26","extraParamJsonString":"{\"companyId\":\"1\",\"type\":\"3\"}","platform":1,"iosApns":false,"sendno":1,"timeToLive":86400,"aliasJsonString":"[]","tagsJsonString":"[\"1\"]","companyId":1,"type":3,"tags":["1"],"alias":[],"extraParamMap":{"companyId":"1","type":"3"}},{"id":9,"deleteStatus":false,"version":0,"createTime":"2017-12-26 11:37:10","createBy":null,"createById":null,"lastModifyTime":"2017-12-26 11:37:10","lastModifyBy":null,"lastModifyById":null,"isNotification":true,"title":"您好，您有一条近三日能耗对比消息","alert":"2017-12-26","extraParamJsonString":"{\"companyId\":\"1\",\"type\":\"3\"}","platform":1,"iosApns":false,"sendno":1,"timeToLive":86400,"aliasJsonString":"[]","tagsJsonString":"[\"1\"]","companyId":1,"type":3,"tags":["1"],"alias":[],"extraParamMap":{"companyId":"1","type":"3"}},{"id":7,"deleteStatus":false,"version":0,"createTime":"2017-12-26 11:36:03","createBy":null,"createById":null,"lastModifyTime":"2017-12-26 11:36:03","lastModifyBy":null,"lastModifyById":null,"isNotification":true,"title":"您好，您有一条近三日能耗对比消息","alert":"2017-12-26","extraParamJsonString":"{\"companyId\":\"1\",\"type\":\"3\"}","platform":1,"iosApns":false,"sendno":1,"timeToLive":86400,"aliasJsonString":"[]","tagsJsonString":"[\"1\"]","companyId":1,"type":3,"tags":["1"],"alias":[],"extraParamMap":{"companyId":"1","type":"3"}},{"id":5,"deleteStatus":false,"version":0,"createTime":"2017-12-26 10:36:59","createBy":null,"createById":null,"lastModifyTime":"2017-12-26 10:36:59","lastModifyBy":null,"lastModifyById":null,"isNotification":true,"title":"您好，您有一条近三日能耗对比消息","alert":"2017-12-26","extraParamJsonString":"{\"companyId\":\"1\",\"type\":\"3\"}","platform":1,"iosApns":false,"sendno":1,"timeToLive":86400,"aliasJsonString":"[]","tagsJsonString":"[\"1\"]","companyId":1,"type":3,"tags":["1"],"alias":[],"extraParamMap":{"companyId":"1","type":"3"}},{"id":3,"deleteStatus":false,"version":0,"createTime":"2017-12-26 10:11:05","createBy":null,"createById":null,"lastModifyTime":"2017-12-26 10:11:05","lastModifyBy":null,"lastModifyById":null,"isNotification":true,"title":"您好，您有一条近三日能耗对比消息","alert":"2017-12-26","extraParamJsonString":"{\"companyId\":\"1\",\"type\":\"3\"}","platform":1,"iosApns":false,"sendno":1,"timeToLive":86400,"aliasJsonString":"[\"1\"]","tagsJsonString":"[]","companyId":1,"type":3,"tags":[],"alias":["1"],"extraParamMap":{"companyId":"1","type":"3"}}],"totalItems":6,"debugMsg":null,"totalPages":1,"prePage":1,"nextPage":1,"firstPage":true,"lastPage":true,"offset":0,"orderBySetted":false}
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
         * searchText :
         * pageNo : 1
         * pageSize : 10
         * orderBy : null
         * orderDir : null
         * orderType : null
         * countTotal : true
         * sort : {"orders":[]}
         * searchDebug : false
         * result : [{"id":13,"deleteStatus":false,"version":0,"createTime":"2017-12-26 11:41:02","createBy":null,"createById":null,"lastModifyTime":"2017-12-26 11:41:02","lastModifyBy":null,"lastModifyById":null,"isNotification":true,"title":"您好，您有一条近三日能耗对比消息","alert":"2017-12-26","extraParamJsonString":"{\"companyId\":\"1\",\"type\":\"3\"}","platform":1,"iosApns":false,"sendno":1,"timeToLive":86400,"aliasJsonString":"[]","tagsJsonString":"[\"1\"]","companyId":1,"type":3,"tags":["1"],"alias":[],"extraParamMap":{"companyId":"1","type":"3"}},{"id":14,"deleteStatus":false,"version":0,"createTime":"2017-12-26 11:41:02","createBy":null,"createById":null,"lastModifyTime":"2017-12-26 11:41:02","lastModifyBy":null,"lastModifyById":null,"isNotification":true,"title":"您好，您有一条近三日能耗对比消息","alert":"2017-12-26","extraParamJsonString":"{\"companyId\":\"1\",\"type\":\"3\"}","platform":1,"iosApns":false,"sendno":1,"timeToLive":86400,"aliasJsonString":"[]","tagsJsonString":"[\"1\"]","companyId":1,"type":3,"tags":["1"],"alias":[],"extraParamMap":{"companyId":"1","type":"3"}},{"id":9,"deleteStatus":false,"version":0,"createTime":"2017-12-26 11:37:10","createBy":null,"createById":null,"lastModifyTime":"2017-12-26 11:37:10","lastModifyBy":null,"lastModifyById":null,"isNotification":true,"title":"您好，您有一条近三日能耗对比消息","alert":"2017-12-26","extraParamJsonString":"{\"companyId\":\"1\",\"type\":\"3\"}","platform":1,"iosApns":false,"sendno":1,"timeToLive":86400,"aliasJsonString":"[]","tagsJsonString":"[\"1\"]","companyId":1,"type":3,"tags":["1"],"alias":[],"extraParamMap":{"companyId":"1","type":"3"}},{"id":7,"deleteStatus":false,"version":0,"createTime":"2017-12-26 11:36:03","createBy":null,"createById":null,"lastModifyTime":"2017-12-26 11:36:03","lastModifyBy":null,"lastModifyById":null,"isNotification":true,"title":"您好，您有一条近三日能耗对比消息","alert":"2017-12-26","extraParamJsonString":"{\"companyId\":\"1\",\"type\":\"3\"}","platform":1,"iosApns":false,"sendno":1,"timeToLive":86400,"aliasJsonString":"[]","tagsJsonString":"[\"1\"]","companyId":1,"type":3,"tags":["1"],"alias":[],"extraParamMap":{"companyId":"1","type":"3"}},{"id":5,"deleteStatus":false,"version":0,"createTime":"2017-12-26 10:36:59","createBy":null,"createById":null,"lastModifyTime":"2017-12-26 10:36:59","lastModifyBy":null,"lastModifyById":null,"isNotification":true,"title":"您好，您有一条近三日能耗对比消息","alert":"2017-12-26","extraParamJsonString":"{\"companyId\":\"1\",\"type\":\"3\"}","platform":1,"iosApns":false,"sendno":1,"timeToLive":86400,"aliasJsonString":"[]","tagsJsonString":"[\"1\"]","companyId":1,"type":3,"tags":["1"],"alias":[],"extraParamMap":{"companyId":"1","type":"3"}},{"id":3,"deleteStatus":false,"version":0,"createTime":"2017-12-26 10:11:05","createBy":null,"createById":null,"lastModifyTime":"2017-12-26 10:11:05","lastModifyBy":null,"lastModifyById":null,"isNotification":true,"title":"您好，您有一条近三日能耗对比消息","alert":"2017-12-26","extraParamJsonString":"{\"companyId\":\"1\",\"type\":\"3\"}","platform":1,"iosApns":false,"sendno":1,"timeToLive":86400,"aliasJsonString":"[\"1\"]","tagsJsonString":"[]","companyId":1,"type":3,"tags":[],"alias":["1"],"extraParamMap":{"companyId":"1","type":"3"}}]
         * totalItems : 6
         * debugMsg : null
         * totalPages : 1
         * prePage : 1
         * nextPage : 1
         * firstPage : true
         * lastPage : true
         * offset : 0
         * orderBySetted : false
         */

        private String searchText;
        private int pageNo;
        private int pageSize;
        private Object orderBy;
        private Object orderDir;
        private Object orderType;
        private boolean countTotal;
        private SortBean sort;
        private boolean searchDebug;
        private int totalItems;
        private Object debugMsg;
        private int totalPages;
        private int prePage;
        private int nextPage;
        private boolean firstPage;
        private boolean lastPage;
        private int offset;
        private boolean orderBySetted;
        private List<ResultBean> result;

        public String getSearchText() {
            return searchText;
        }

        public void setSearchText(String searchText) {
            this.searchText = searchText;
        }

        public int getPageNo() {
            return pageNo;
        }

        public void setPageNo(int pageNo) {
            this.pageNo = pageNo;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public Object getOrderBy() {
            return orderBy;
        }

        public void setOrderBy(Object orderBy) {
            this.orderBy = orderBy;
        }

        public Object getOrderDir() {
            return orderDir;
        }

        public void setOrderDir(Object orderDir) {
            this.orderDir = orderDir;
        }

        public Object getOrderType() {
            return orderType;
        }

        public void setOrderType(Object orderType) {
            this.orderType = orderType;
        }

        public boolean isCountTotal() {
            return countTotal;
        }

        public void setCountTotal(boolean countTotal) {
            this.countTotal = countTotal;
        }

        public SortBean getSort() {
            return sort;
        }

        public void setSort(SortBean sort) {
            this.sort = sort;
        }

        public boolean isSearchDebug() {
            return searchDebug;
        }

        public void setSearchDebug(boolean searchDebug) {
            this.searchDebug = searchDebug;
        }

        public int getTotalItems() {
            return totalItems;
        }

        public void setTotalItems(int totalItems) {
            this.totalItems = totalItems;
        }

        public Object getDebugMsg() {
            return debugMsg;
        }

        public void setDebugMsg(Object debugMsg) {
            this.debugMsg = debugMsg;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public int getPrePage() {
            return prePage;
        }

        public void setPrePage(int prePage) {
            this.prePage = prePage;
        }

        public int getNextPage() {
            return nextPage;
        }

        public void setNextPage(int nextPage) {
            this.nextPage = nextPage;
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

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public boolean isOrderBySetted() {
            return orderBySetted;
        }

        public void setOrderBySetted(boolean orderBySetted) {
            this.orderBySetted = orderBySetted;
        }

        public List<ResultBean> getResult() {
            return result;
        }

        public void setResult(List<ResultBean> result) {
            this.result = result;
        }

        public static class SortBean {
            private List<?> orders;

            public List<?> getOrders() {
                return orders;
            }

            public void setOrders(List<?> orders) {
                this.orders = orders;
            }
        }

        public static class ResultBean {
            /**
             * id : 13
             * deleteStatus : false
             * version : 0
             * createTime : 2017-12-26 11:41:02
             * createBy : null
             * createById : null
             * lastModifyTime : 2017-12-26 11:41:02
             * lastModifyBy : null
             * lastModifyById : null
             * isNotification : true
             * title : 您好，您有一条近三日能耗对比消息
             * alert : 2017-12-26
             * extraParamJsonString : {"companyId":"1","type":"3"}
             * platform : 1
             * iosApns : false
             * sendno : 1
             * timeToLive : 86400
             * aliasJsonString : []
             * tagsJsonString : ["1"]
             * companyId : 1
             * type : 3
             * tags : ["1"]
             * alias : []
             * extraParamMap : {"companyId":"1","type":"3"}
             */

            private int id;
            private boolean deleteStatus;
            private int version;
            private String createTime;
            private Object createBy;
            private Object createById;
            private String lastModifyTime;
            private Object lastModifyBy;
            private Object lastModifyById;
            private boolean isNotification;
            private String title;
            private String alert;
            private String extraParamJsonString;
            private int platform;
            private boolean iosApns;
            private int sendno;
            private int timeToLive;
            private String aliasJsonString;
            private String tagsJsonString;
            private int companyId;
            private int type;
            private ExtraParamMapBean extraParamMap;
            private List<String> tags;
            private List<?> alias;

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

            public boolean isIsNotification() {
                return isNotification;
            }

            public void setIsNotification(boolean isNotification) {
                this.isNotification = isNotification;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getAlert() {
                return alert;
            }

            public void setAlert(String alert) {
                this.alert = alert;
            }

            public String getExtraParamJsonString() {
                return extraParamJsonString;
            }

            public void setExtraParamJsonString(String extraParamJsonString) {
                this.extraParamJsonString = extraParamJsonString;
            }

            public int getPlatform() {
                return platform;
            }

            public void setPlatform(int platform) {
                this.platform = platform;
            }

            public boolean isIosApns() {
                return iosApns;
            }

            public void setIosApns(boolean iosApns) {
                this.iosApns = iosApns;
            }

            public int getSendno() {
                return sendno;
            }

            public void setSendno(int sendno) {
                this.sendno = sendno;
            }

            public int getTimeToLive() {
                return timeToLive;
            }

            public void setTimeToLive(int timeToLive) {
                this.timeToLive = timeToLive;
            }

            public String getAliasJsonString() {
                return aliasJsonString;
            }

            public void setAliasJsonString(String aliasJsonString) {
                this.aliasJsonString = aliasJsonString;
            }

            public String getTagsJsonString() {
                return tagsJsonString;
            }

            public void setTagsJsonString(String tagsJsonString) {
                this.tagsJsonString = tagsJsonString;
            }

            public int getCompanyId() {
                return companyId;
            }

            public void setCompanyId(int companyId) {
                this.companyId = companyId;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public ExtraParamMapBean getExtraParamMap() {
                return extraParamMap;
            }

            public void setExtraParamMap(ExtraParamMapBean extraParamMap) {
                this.extraParamMap = extraParamMap;
            }

            public List<String> getTags() {
                return tags;
            }

            public void setTags(List<String> tags) {
                this.tags = tags;
            }

            public List<?> getAlias() {
                return alias;
            }

            public void setAlias(List<?> alias) {
                this.alias = alias;
            }

            public static class ExtraParamMapBean {
                /**
                 * companyId : 1
                 * type : 3
                 */

                private String companyId;
                private String type;

                public String getCompanyId() {
                    return companyId;
                }

                public void setCompanyId(String companyId) {
                    this.companyId = companyId;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }
            }
        }
    }
}
