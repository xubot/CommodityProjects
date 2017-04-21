package com.example.administrator.commodityproject.Bean;

/**
 * 用途：
 * 作者：xuBoTao
 * 时间：2017/4/21 09:18
 */

public class RegisterdBean {
    private String dataStr;
    private int id;

    public RegisterdBean(String dataStr, int id) {
        this.dataStr = dataStr;
        this.id = id;
    }

    public String getDataStr() {
        return dataStr;
    }

    public void setDataStr(String dataStr) {
        this.dataStr = dataStr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
