package com.example.administrator.commodityproject.Bean;

/**
 * 用途：
 * 作者：xuBoTao
 * 时间：2017/4/17 14:32
 */

public class GoodsBean {
    private String img;
    private String name;
    private String shop_price;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShop_price() {
        return shop_price;
    }

    public void setShop_price(String shop_price) {
        this.shop_price = shop_price;
    }

    public GoodsBean(String img, String name, String shop_price) {

        this.img = img;
        this.name = name;
        this.shop_price = shop_price;
    }
}
