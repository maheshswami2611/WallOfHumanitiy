package com.prometteur.wallofhumanitiy.Singleton;

public class SingletonVisitedPlaces {
    private String placeTitle;
    private String placeLocation;
    private String placeDate;
    private String placeTime;

    public SingletonVisitedPlaces() {
    }

    public SingletonVisitedPlaces(String placeTitle, String placeLocation, String placeDate, String placeTime) {
        this.placeTitle = placeTitle;
        this.placeLocation = placeLocation;
        this.placeDate = placeDate;
        this.placeTime = placeTime;
    }

    public String getPlaceTitle() {
        return placeTitle;
    }

    public void setPlaceTitle(String placeTitle) {
        this.placeTitle = placeTitle;
    }

    public String getPlaceLocation() {
        return placeLocation;
    }

    public void setPlaceLocation(String placeLocation) {
        this.placeLocation = placeLocation;
    }

    public String getPlaceDate() {
        return placeDate;
    }

    public void setPlaceDate(String placeDate) {
        this.placeDate = placeDate;
    }

    public String getPlaceTime() {
        return placeTime;
    }

    public void setPlaceTime(String placeTime) {
        this.placeTime = placeTime;
    }
}