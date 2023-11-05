package com.example.myloginpage;

public class UserAmbulance { // ambulance user
    private  String amName,amLocation,amNumber;

    public UserAmbulance(String amName, String amLocation, String amNumber) {
        this.amName = amName;
        this.amLocation = amLocation;
        this.amNumber = amNumber;
    }

    public String getAmName() {
        return amName;
    }

    public void setAmName(String amName) {
        this.amName = amName;
    }

    public String getAmLoaction() {
        return amLocation;
    }

    public void setAmLoaction(String amLoaction) {
        this.amLocation = amLoaction;
    }

    public String getAmNumber() {
        return amNumber;
    }

    public void setAmNumber(String amNumber) {
        this.amNumber = amNumber;
    }


}
