package com.prometteur.wallofhumanitiy.Singleton;

public class SingletonStore {
    private String storeTitle;
    private String storeTime;

    public SingletonStore() {
    }

    public SingletonStore( String storeTitle, String storeTime) {
        this.storeTitle = storeTitle;
        this.storeTime = storeTime;
    }

    public String getStoreTitle() {
        return storeTitle;
    }

    public void setStoreTitle(String storeTitle) {
        this.storeTitle = storeTitle;
    }

    public String getStoreTime() {
        return storeTime;
    }

    public void setStoreTime(String storeTime) {
        this.storeTime = storeTime;
    }
}