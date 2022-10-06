package com.zzz.petstore.model;

public class Activity {
    int id;
    String name;
    double discount;
    /**
     * 0猫
     * 1狗
     * 2所有
     */
    int type;
    /**
     * 0开启
     * 1关闭
     */
    int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

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

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
