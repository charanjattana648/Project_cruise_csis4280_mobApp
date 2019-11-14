package com.example.cruiseapp;

public class CruiseCabin {
    private String Type;
    private String CabinSize;
    private String ConnectedRooms;
    private String AccessibleRooms;
    private String MaximumPassengers;
    private String TotalCabins;
    private double Price;
    private String Image;

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getCabinSize() {
        return CabinSize;
    }

    public void setCabinSize(String cabinSize) {
        CabinSize = cabinSize;
    }

    public String getConnectedRooms() {
        return ConnectedRooms;
    }

    public void setConnectedRooms(String connectedRooms) {
        ConnectedRooms = connectedRooms;
    }

    public String getAccessibleRooms() {
        return AccessibleRooms;
    }

    public void setAccessibleRooms(String accessibleRooms) {
        AccessibleRooms = accessibleRooms;
    }

    public String getMaximumPassengers() {
        return MaximumPassengers;
    }

    public void setMaximumPassengers(String maximumPassengers) {
        MaximumPassengers = maximumPassengers;
    }

    public String getTotalCabins() {
        return TotalCabins;
    }

    public void setTotalCabins(String totalCabins) {
        TotalCabins = totalCabins;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
