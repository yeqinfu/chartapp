/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.bean.login;

import com.ppandroid.im.bean.BaseBody;

/**
 * Created by yeqinfu on 2017/8/11.
 */

public class LoginBody extends BaseBody {

    /**
     * message : {"id":1,"md5":"5ffe586d341799eb679fad774b82d500","tokenDto":{"token":"7252BEDC6091CD3CDD4DFEFF00B31776","expires_in":7200},"role":"administrator"}
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
         * md5 : 5ffe586d341799eb679fad774b82d500
         * tokenDto : {"token":"7252BEDC6091CD3CDD4DFEFF00B31776","expires_in":7200}
         * role : administrator
         */

        private int id;
        private String md5;
        private TokenDtoBean tokenDto;
        private String role;
        private String companyName;
        private String companyId;

        public String getCompanyId() {
            return companyId;
        }

        public void setCompanyId(String companyId) {
            this.companyId = companyId;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

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

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public static class TokenDtoBean {
            /**
             * token : 7252BEDC6091CD3CDD4DFEFF00B31776
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
