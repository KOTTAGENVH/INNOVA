package com.example.innova;

public class MainModel {
    String name,product,email,phone,surl;

    MainModel(){

    }


    public MainModel(String name, String product, String email, String phone, String surl) {
        this.name = name;
        this.product = product;
        this.email = email;
        this.phone = phone;
        this.surl = surl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSurl() {
        return surl;
    }

    public void setSurl(String surl) {
        this.surl = surl;
    }
}