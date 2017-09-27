/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.bean.login;

import com.ppandroid.im.bean.BaseBody;

/**
 * Created by yeqinfu on 2017/4/29.
 */

public class MD5Body extends BaseBody {

    /**
     * message : {"id":25574,"md5":"3e8fdee3b53ca2ae5c650a08e652ffef","tokenDto":{"token":"03C43AD3380C737807DA819B1D4B91D5","expires_in":7200},"huangxingpwd":"a911ba4afd293b01e52ece907cd6d36e","role":"","huangxingNickname":"null"}
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
         * id : 25574
         * md5 : 3e8fdee3b53ca2ae5c650a08e652ffef
         * tokenDto : {"token":"03C43AD3380C737807DA819B1D4B91D5","expires_in":7200}
         * huangxingpwd : a911ba4afd293b01e52ece907cd6d36e
         * role :
         * huangxingNickname : null
         */

        private int id;
        private String md5;
        private TokenDtoBean tokenDto;
        private String huangxingpwd;
        private String role;
        private String huangxingNickname;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMd5() {
            return md5;
        }

        public void setMd5(String md5) {
            this.md5 = md5;
        }

        public TokenDtoBean getTokenDto() {
            return tokenDto;
        }

        public void setTokenDto(TokenDtoBean tokenDto) {
            this.tokenDto = tokenDto;
        }

        public String getHuangxingpwd() {
            return huangxingpwd;
        }

        public void setHuangxingpwd(String huangxingpwd) {
            this.huangxingpwd = huangxingpwd;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getHuangxingNickname() {
            return huangxingNickname;
        }

        public void setHuangxingNickname(String huangxingNickname) {
            this.huangxingNickname = huangxingNickname;
        }

        public static class TokenDtoBean {
            /**
             * token : 03C43AD3380C737807DA819B1D4B91D5
             * expires_in : 7200
             */

            private String token;
            private int expires_in;

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }

            public int getExpires_in() {
                return expires_in;
            }

            public void setExpires_in(int expires_in) {
                this.expires_in = expires_in;
            }
        }
    }
}
