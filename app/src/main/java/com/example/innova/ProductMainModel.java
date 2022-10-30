package com.example.innova;

public class ProductMainModel {
    String productname,spec,purl,price,siturl;


    ProductMainModel(){

    }
    public ProductMainModel(String productname, String spec, String price, String purl,String siturl) {
        this.productname = productname;
        this.spec = spec;
        this.purl = purl;
        this.price = price;
        this.siturl = siturl;

    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }


    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }

    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }

    public String getsiturl() {
        return siturl;
    }
    public void setsiturl(String siturl) {
        this.siturl = siturl;
    }


}
