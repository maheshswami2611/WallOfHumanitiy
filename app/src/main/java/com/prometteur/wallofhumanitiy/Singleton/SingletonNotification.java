package com.prometteur.wallofhumanitiy.Singleton;

public class SingletonNotification {
    private int notifThumbnail;
    private String notifTitle;
    private String notifTime;

    public SingletonNotification() {
    }

    public SingletonNotification(String notifTitle, String notifTime, int notifThumbnail) {
       this.notifTime=notifTime;
       this.notifTitle=notifTitle;
       this.notifThumbnail=notifThumbnail;
    }

    public int getNotifThumbnail() {
        return notifThumbnail;
    }

    public void setNotifThumbnail(int notifThumbnail) {
        this.notifThumbnail = notifThumbnail;
    }

    public String getNotifTitle() {
        return notifTitle;
    }

    public void setNotifTitle(String notifTitle) {
        this.notifTitle = notifTitle;
    }

    public String getNotifTime() {
        return notifTime;
    }

    public void setNotifTime(String notifTime) {
        this.notifTime = notifTime;
    }
}