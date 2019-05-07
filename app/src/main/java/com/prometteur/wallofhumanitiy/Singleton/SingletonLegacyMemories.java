package com.prometteur.wallofhumanitiy.Singleton;

public class SingletonLegacyMemories {
    private String memoTitle;
    private String memoNumOfComment;
    private String memoNumOfShare;
    private int memoThumbnail;

    public SingletonLegacyMemories() {
    }

    public SingletonLegacyMemories(String memoTitle, String memoNumOfComment, String memoNumOfShare, int memoThumbnail) {
        this.memoTitle = memoTitle;
        this.memoNumOfComment = memoNumOfComment;
        this.memoThumbnail = memoThumbnail;
        this.memoNumOfShare = memoNumOfShare;
    }

    public String getMemoTitle() {
        return memoTitle;
    }

    public void setMemoTitle(String memoTitle) {
        this.memoTitle = memoTitle;
    }

    public String getMemoNumOfComment() {
        return memoNumOfComment;
    }

    public void setMemoNumOfComment(String memoNumOfComment) {
        this.memoNumOfComment = memoNumOfComment;
    }

    public int getMemoThumbnail() {
        return memoThumbnail;
    }

    public void setMemoThumbnail(int memoThumbnail) {
        this.memoThumbnail = memoThumbnail;
    }

    public String getMemoNumOfShare() {
        return memoNumOfShare;
    }

    public void setMemoNumOfShare(String memoNumOfShare) {
        this.memoNumOfShare = memoNumOfShare;
    }
}