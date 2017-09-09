package com.ppandroid.app.http;

import com.ppandroid.im.FG_Mine;

import org.junit.Before;
import org.junit.Test;

/**
 * Created by yeqinfu on 2017/9/9.
 */
public class HttpTest {
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void get() throws Exception {

        String url="user/personal/get.json";
        FG_Mine fg=new FG_Mine();
        fg.loadContent();

    }

    @Test
    public void post() throws Exception {

    }

    @Test
    public void getTokenByMD5() throws Exception {

    }

    @Test
    public void parseJson() throws Exception {

    }

    @Test
    public void parseErrorBody() throws Exception {

    }

    @Test
    public void isGoodJson() throws Exception {

    }

    @Test
    public void addBaseParams() throws Exception {
    }

}