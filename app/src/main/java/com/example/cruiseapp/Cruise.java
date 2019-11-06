package com.example.cruiseapp;

import java.lang.reflect.Array;

public class Cruise {
    private String companyName;
    private String cruiseName;
    private int guests;
    private int crew;
    private int launched;
    private String[] departs_from;
    private String[] sails_to;
    private String cruiseImage;

    public String getCruiseImage() {
        return cruiseImage;
    }

    public void setCruiseImage(String cruiseImage) {
        this.cruiseImage = cruiseImage;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCruiseName() {
        return cruiseName;
    }

    public void setCruiseName(String cruiseName) {
        this.cruiseName = cruiseName;
    }

    public int getGuests() {
        return guests;
    }

    public void setGuests(int guests) {
        this.guests = guests;
    }

    public int getCrew() {
        return crew;
    }

    public void setCrew(int crew) {
        this.crew = crew;
    }

    public int getLaunched() {
        return launched;
    }

    public void setLaunched(int launched) {
        this.launched = launched;
    }

    public String[] getDeparts_from() {
        return departs_from;
    }

    public void setDeparts_from(String[] departs_from) {
        this.departs_from = departs_from;
    }

    public String[] getSails_to() {
        return sails_to;
    }

    public void setSails_to(String[] sails_to) {
        this.sails_to = sails_to;
    }
}
