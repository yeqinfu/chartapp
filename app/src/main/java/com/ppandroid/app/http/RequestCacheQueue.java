/*
 * Created by yeqinfu on 17-12-27 上午9:20
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.http;

import java.util.UUID;

/**
 * Created by yeqinfu on 2017/12/27.
 * 请求缓存队列，针对Token失效后，刷新了Token，前一次的请求要重发
 */

public class RequestCacheQueue {
    /**
     * 随机数
     * @return
     */
    public static int getRandomCode(){
       return  UUID.randomUUID().hashCode();
    }

}
