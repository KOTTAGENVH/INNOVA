package com.example.innova;

public class MainRiderModel {
    String name,nic,vehicleNo,email,rurl;

    MainRiderModel(){

    }

    public MainRiderModel(String name, String nic, String vehicleNo, String email, String rurl) {
        this.name = name;
        this.nic = nic;
        this.vehicleNo = vehicleNo;
        this.email = email;
        this.rurl = rurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRurl() {
        return rurl;
    }

    public void setRurl(String rurl) {
        this.rurl = rurl;
    }
}

