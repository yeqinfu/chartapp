package com.ppandroid.app.utils;

import android.test.AndroidTestCase;

import org.junit.Test;

/**
 * Created by yeqinfu on 2017/9/11.
 */
public class Utils_CommonTest extends AndroidTestCase{

    @Test
    public void getRandomColor() throws Exception {
        System.out.print("=========="+Utils_Common.getRandomColor());
    }

    @Test
    public void findNumberFromStr() throws Exception {
        String s="50.59万kwh";
        s=Utils_Common.findNumberFromStr(s);
        System.out.print("=========="+s);

    }


}