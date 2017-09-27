/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.bean.overview;

import com.ppandroid.im.bean.BaseBody;

import java.util.List;

/**
 * Created by yeqinfu on 2017/8/14.
 */

public class BN_OverViewConfig extends BaseBody {

    /**
     * message : {"choosed":[{"id":2,"photo":null,"name":"能耗信息"}],"noChoosed":[{"id":3,"photo":null,"name":"仪表用能统计图"},{"id":4,"photo":null,"name":"重点设备用能统计图"}]}
     */

    private MessageBean message;

    public MessageBean getMessage() {
        return message;
    }

    public void setMessage(MessageBean message) {
        this.message = message;
    }

    public static class MessageBean {
        private List<ChoosedBean> choosed;
        private List<ChoosedBean> noChoosed;

        public List<ChoosedBean> getChoosed() {
            return choosed;
        }

        public void setChoosed(List<ChoosedBean> choosed) {
            this.choosed = choosed;
        }

        public List<ChoosedBean> getNoChoosed() {
            return noChoosed;
        }

        public void setNoChoosed(List<ChoosedBean> noChoosed) {
            this.noChoosed = noChoosed;
        }

        public static class ChoosedBean {
            /**
             * id : 2
             * photo : null
             * name : 能耗信息
             */

            private int id;
            private Object photo;
            private String name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public Object getPhoto() {
                return photo;
            }

            public void setPhoto(Object photo) {
                this.photo = photo;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

      
    }
}
