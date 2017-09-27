/*
 * Created by yeqinfu on 17-9-27 上午9:35
 * Copyright (c) JXT All rights reserved.
 */

package com.ppandroid.app.home.adapter;

/**
 * Created by yeqinfu on 2017/8/8.
 */

public class BN_Ex {
    private boolean isOpen=false;
    private   String title="";
    private int iconId;

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public BN_Ex(boolean isOpen, String title) {
        this.isOpen = isOpen;
        this.title = title;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
