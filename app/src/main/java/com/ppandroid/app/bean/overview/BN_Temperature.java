/*
 * Created by yeqinfu on 18-1-4 上午10:41
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.bean.overview;

import com.ppandroid.im.bean.BaseBody;

import java.util.List;

/**
 * Created by yeqinfu on 2018/1/4.
 */

public class BN_Temperature extends BaseBody {

    /**
     * message : {"devicePage":{"searchText":"","pageNo":1,"pageSize":10,"orderBy":null,"orderDir":null,"orderType":null,"countTotal":true,"sort":{"orders":[]},"searchDebug":false,"result":[{"name":"温湿计量表CH01-1","temperature":"23.00℃","humidity":"45.00%","temperatureList":[{"hour":0,"temperature":23,"humidity":45},{"hour":1,"temperature":23,"humidity":45},{"hour":2,"temperature":23,"humidity":45},{"hour":3,"temperature":23,"humidity":45},{"hour":4,"temperature":23,"humidity":45},{"hour":5,"temperature":23,"humidity":45},{"hour":6,"temperature":23,"humidity":45},{"hour":7,"temperature":23,"humidity":45},{"hour":8,"temperature":23,"humidity":45},{"hour":9,"temperature":23,"humidity":45},{"hour":10,"temperature":23,"humidity":45}]}],"totalItems":1,"debugMsg":null,"prePage":1,"nextPage":1,"totalPages":1,"firstPage":true,"lastPage":true,"offset":0,"orderBySetted":false}}
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
         * devicePage : {"searchText":"","pageNo":1,"pageSize":10,"orderBy":null,"orderDir":null,"orderType":null,"countTotal":true,"sort":{"orders":[]},"searchDebug":false,"result":[{"name":"温湿计量表CH01-1","temperature":"23.00℃","humidity":"45.00%","temperatureList":[{"hour":0,"temperature":23,"humidity":45},{"hour":1,"temperature":23,"humidity":45},{"hour":2,"temperature":23,"humidity":45},{"hour":3,"temperature":23,"humidity":45},{"hour":4,"temperature":23,"humidity":45},{"hour":5,"temperature":23,"humidity":45},{"hour":6,"temperature":23,"humidity":45},{"hour":7,"temperature":23,"humidity":45},{"hour":8,"temperature":23,"humidity":45},{"hour":9,"temperature":23,"humidity":45},{"hour":10,"temperature":23,"humidity":45}]}],"totalItems":1,"debugMsg":null,"prePage":1,"nextPage":1,"totalPages":1,"firstPage":true,"lastPage":true,"offset":0,"orderBySetted":false}
         */

        private DevicePageBean devicePage;

        public DevicePageBean getDevicePage() {
            return devicePage;
        }

        public void setDevicePage(DevicePageBean devicePage) {
            this.devicePage = devicePage;
        }

        public static class DevicePageBean {
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
             * result : [{"name":"温湿计量表CH01-1","temperature":"23.00℃","humidity":"45.00%","temperatureList":[{"hour":0,"temperature":23,"humidity":45},{"hour":1,"temperature":23,"humidity":45},{"hour":2,"temperature":23,"humidity":45},{"hour":3,"temperature":23,"humidity":45},{"hour":4,"temperature":23,"humidity":45},{"hour":5,"temperature":23,"humidity":45},{"hour":6,"temperature":23,"humidity":45},{"hour":7,"temperature":23,"humidity":45},{"hour":8,"temperature":23,"humidity":45},{"hour":9,"temperature":23,"humidity":45},{"hour":10,"temperature":23,"humidity":45}]}]
             * totalItems : 1
             * debugMsg : null
             * prePage : 1
             * nextPage : 1
             * totalPages : 1
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
            private int prePage;
            private int nextPage;
            private int totalPages;
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
                 * name : 温湿计量表CH01-1
                 * temperature : 23.00℃
                 * humidity : 45.00%
                 * temperatureList : [{"hour":0,"temperature":23,"humidity":45},{"hour":1,"temperature":23,"humidity":45},{"hour":2,"temperature":23,"humidity":45},{"hour":3,"temperature":23,"humidity":45},{"hour":4,"temperature":23,"humidity":45},{"hour":5,"temperature":23,"humidity":45},{"hour":6,"temperature":23,"humidity":45},{"hour":7,"temperature":23,"humidity":45},{"hour":8,"temperature":23,"humidity":45},{"hour":9,"temperature":23,"humidity":45},{"hour":10,"temperature":23,"humidity":45}]
                 */
                private boolean isOpen=false;
                private boolean showTemp=true;//默认显示温度

                private String select;

                private String id;

                private String name;
                private String temperature;
                private String humidity;
                private List<TemperatureListBean> temperatureList;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public boolean isShowTemp() {
                    return showTemp;
                }

                public String getSelect() {
                    return select;
                }

                public void setSelect(String select) {
                    this.select = select;
                }

                public void setShowTemp(boolean showTemp) {
                    this.showTemp = showTemp;
                }
                public boolean isOpen() {
                    return isOpen;
                }

                public void setOpen(boolean open) {
                    isOpen = open;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getTemperature() {
                    return temperature;
                }

                public void setTemperature(String temperature) {
                    this.temperature = temperature;
                }

                public String getHumidity() {
                    return humidity;
                }

                public void setHumidity(String humidity) {
                    this.humidity = humidity;
                }

                public List<TemperatureListBean> getTemperatureList() {
                    return temperatureList;
                }

                public void setTemperatureList(List<TemperatureListBean> temperatureList) {
                    this.temperatureList = temperatureList;
                }

                public static class TemperatureListBean {
                    /**
                     * hour : 0
                     * temperature : 23
                     * humidity : 45
                     */

                    private int hour;
                    private int temperature;
                    private int humidity;

                    public int getHour() {
                        return hour;
                    }

                    public void setHour(int hour) {
                        this.hour = hour;
                    }

                    public int getTemperature() {
                        return temperature;
                    }

                    public void setTemperature(int temperature) {
                        this.temperature = temperature;
                    }

                    public int getHumidity() {
                        return humidity;
                    }

                    public void setHumidity(int humidity) {
                        this.humidity = humidity;
                    }
                }
            }
        }
    }
}
