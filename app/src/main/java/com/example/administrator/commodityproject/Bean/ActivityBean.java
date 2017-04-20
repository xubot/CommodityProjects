package com.example.administrator.commodityproject.Bean;

/**
 * 用途：
 * 作者：xuBoTao
 * 时间：2017/4/20 19:41
 */

public class ActivityBean {
    private String title;
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ActivityBean(String title, String description) {

        this.title = title;
        this.description = description;
    }
}
