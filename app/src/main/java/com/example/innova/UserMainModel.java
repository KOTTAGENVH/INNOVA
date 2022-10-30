package com.example.innova;

public class UserMainModel {
    String productname,price,purl,siturl,spec;
    UserMainModel(){

    }
    public UserMainModel(String productname, String price, String purl, String siturl, String spec) {
        this.productname = productname;
        this.price = price;
        this.purl = purl;
        this.siturl = siturl;
        this.spec = spec;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }

    public String getSiturl() {
        return siturl;
    }

    public void setSiturl(String siturl) {
        this.siturl = siturl;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }
}


