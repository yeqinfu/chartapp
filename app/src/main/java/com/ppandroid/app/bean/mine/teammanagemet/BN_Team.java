/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.bean.mine.teammanagemet;

/**
 * Created by yeqinfu on 2017/8/25.
 */

public class BN_Team {
    /**
     * id : 1
     * name : 机电部
     * employeeSum : 4
     * sonSum : 1
     */

    private int id;
    private String name;
    private int employeeSum;
    private int sonSum;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEmployeeSum() {
        return employeeSum;
    }

    public void setEmployeeSum(int employeeSum) {
        this.employeeSum = employeeSum;
    }

    public int getSonSum() {
        return sonSum;
    }

    public void setSonSum(int sonSum) {
        this.sonSum = sonSum;
    }
}
