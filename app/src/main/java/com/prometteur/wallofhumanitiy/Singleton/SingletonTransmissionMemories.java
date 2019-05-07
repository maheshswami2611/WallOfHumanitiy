package com.prometteur.wallofhumanitiy.Singleton;

public class SingletonTransmissionMemories {
    private String memoTitle;
    private int memoThumbnail;

    public SingletonTransmissionMemories() {
    }

    public SingletonTransmissionMemories(String memoTitle, int memoThumbnail) {
        this.memoTitle = memoTitle;
        this.memoThumbnail = memoThumbnail;
    }

    public String getMemoTitle() {
        return memoTitle;
    }

    public void setMemoTitle(String memoTitle) {
        this.memoTitle = memoTitle;
    }


    public int getMemoThumbnail() {
        return memoThumbnail;
    }

    public void setMemoThumbnail(int memoThumbnail) {
        this.memoThumbnail = memoThumbnail;
    }


}