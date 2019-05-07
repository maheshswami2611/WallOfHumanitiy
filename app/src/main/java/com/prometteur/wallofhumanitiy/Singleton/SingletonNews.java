package com.prometteur.wallofhumanitiy.Singleton;

public class SingletonNews {
    private int newsThumbnail;
    private String newsTitle;
    private String newsTime;

    public SingletonNews() {
    }

    public SingletonNews(int newsThumbnail, String newsTitle, String newsTime) {
        this.newsThumbnail = newsThumbnail;
        this.newsTitle = newsTitle;
        this.newsTime = newsTime;
    }

    public int getNewsThumbnail() {
        return newsThumbnail;
    }

    public void setNewsThumbnail(int newsThumbnail) {
        this.newsThumbnail = newsThumbnail;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsTime() {
        return newsTime;
    }

    public void setNewsTime(String newsTime) {
        this.newsTime = newsTime;
    }
}