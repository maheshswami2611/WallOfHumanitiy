package com.prometteur.wallofhumanitiy.Singleton;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CountOfRecordsDetails {

    @SerializedName("total_photoes")
    @Expose
    private String totalPhotoes;
    @SerializedName("total_videos")
    @Expose
    private String totalVideos;
    @SerializedName("total_places_visited")
    @Expose
    private String totalPlacesVisited;
    @SerializedName("total_transmission")
    @Expose
    private String totalTransmission;
    @SerializedName("total_legacy")
    @Expose
    private String totalLegacy;

    public String getTotalPhotoes() {
        return totalPhotoes;
    }

    public void setTotalPhotoes(String totalPhotoes) {
        this.totalPhotoes = totalPhotoes;
    }

    public String getTotalVideos() {
        return totalVideos;
    }

    public void setTotalVideos(String totalVideos) {
        this.totalVideos = totalVideos;
    }

    public String getTotalPlacesVisited() {
        return totalPlacesVisited;
    }

    public void setTotalPlacesVisited(String totalPlacesVisited) {
        this.totalPlacesVisited = totalPlacesVisited;
    }

    public String getTotalTransmission() {
        return totalTransmission;
    }

    public void setTotalTransmission(String totalTransmission) {
        this.totalTransmission = totalTransmission;
    }

    public String getTotalLegacy() {
        return totalLegacy;
    }

    public void setTotalLegacy(String totalLegacy) {
        this.totalLegacy = totalLegacy;
    }
}
