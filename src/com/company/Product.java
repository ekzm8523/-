package com.company;

public class Product {

    private int prCode;
    private String prName;
    private int price;
    private String manufacture;

    public void setPrCode(int prCode) {
        this.prCode = prCode;
    }

    public void setPrName(String prName) {
        this.prName = prName;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public int getPrCode() {
        return prCode;
    }

    public String getPrName() {
        return prName;
    }

    public int getPrice() {
        return price;
    }

    public String getManufacture() {
        return manufacture;
    }

}
