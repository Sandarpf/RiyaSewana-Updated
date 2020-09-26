package com.example.riyasewana.Models;

public class UsersModel {

    private String userName,customerName,email,mobileNumber;

    public UsersModel() {
    }

    public UsersModel(String userName, String customerName, String email,String mobileNumber) {
        this.userName = userName;
        this.customerName = customerName;
        this.email = email;
        this.mobileNumber = mobileNumber;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
}
