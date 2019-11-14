package com.example.cruiseapp;

import java.util.Date;

public class CruisePackage {
    private String PackageName;
    private String CruiseName;
    private String DeptDate;
    private int Num_Days;
    private String Departs_from;
    private String Sails_to;
    private String RouteMap;

    public String getPackageName() {
        return PackageName;
    }

    public void setPackageName(String packageName) {
        PackageName = packageName;
    }

    public String getCruiseName() {
        return CruiseName;
    }

    public void setCruiseName(String cruiseName) {
        CruiseName = cruiseName;
    }

    public String getDeptDate() {
        return DeptDate;
    }

    public void setDeptDate(String deptDate) {
        DeptDate = deptDate;
    }

    public int getNum_Days() {
        return Num_Days;
    }

    public void setNum_Days(int num_Days) {
        Num_Days = num_Days;
    }

    public String getDeparts_from() {
        return Departs_from;
    }

    public void setDeparts_from(String departs_from) {
        Departs_from = departs_from;
    }

    public String getSails_to() {
        return Sails_to;
    }

    public void setSails_to(String sails_to) {
        Sails_to = sails_to;
    }

    public String getRouteMap() {
        return RouteMap;
    }

    public void setRouteMap(String routeMap) {
        RouteMap = routeMap;
    }
}
