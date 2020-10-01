package com.example.riyasewana.Models;

public class VehicleModel {

    int vehicle_img;
    String vehicle_name, vehicle_price , vehicle_mileage , vehicle_type , vehicle_location;

    public VehicleModel(int vehicle_img, String vehicle_name, String vehicle_price, String vehicle_mileage, String vehicle_type, String vehicle_location) {
        this.vehicle_img = vehicle_img;
        this.vehicle_name = vehicle_name;
        this.vehicle_price = vehicle_price;
        this.vehicle_mileage = vehicle_mileage;
        this.vehicle_type = vehicle_type;
        this.vehicle_location = vehicle_location;
    }

    public int getVehicle_img() {
        return vehicle_img;
    }

    public void setVehicle_img(int vehicle_img) {
        this.vehicle_img = vehicle_img;
    }

    public String getVehicle_name() {
        return vehicle_name;
    }

    public void setVehicle_name(String vehicle_name) {
        this.vehicle_name = vehicle_name;
    }

    public String getVehicle_price() {
        return vehicle_price;
    }

    public void setVehicle_price(String vehicle_price) {
        this.vehicle_price = vehicle_price;
    }

    public String getVehicle_mileage() {
        return vehicle_mileage;
    }

    public void setVehicle_mileage(String vehicle_mileage) {
        this.vehicle_mileage = vehicle_mileage;
    }

    public String getVehicle_type() {
        return vehicle_type;
    }

    public void setVehicle_type(String vehicle_type) {
        this.vehicle_type = vehicle_type;
    }

    public String getVehicle_location() {
        return vehicle_location;
    }

    public void setVehicle_location(String vehicle_location) {
        this.vehicle_location = vehicle_location;
    }
}
