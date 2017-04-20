package com.example.administrator.commodityproject.Bean;

/**
 * 用途：
 * 作者：xuBoTao
 * 时间：2017/4/13 14:43
 */

public class Ab5Bean {
    private String title;
    private String img;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Ab5Bean(String title, String img, String url) {
        this.title = title;
        this.img = img;
        this.url = url;
    }
}
