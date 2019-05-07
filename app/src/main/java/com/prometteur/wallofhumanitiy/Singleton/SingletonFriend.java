package com.prometteur.wallofhumanitiy.Singleton;

public class SingletonFriend {
    private int friendThumbnail;
    private String friendName;
    private String friendSubName;

    public SingletonFriend() {
    }

    public SingletonFriend(int friendThumbnail, String friendName, String friendSubName) {
        this.friendThumbnail = friendThumbnail;
        this.friendName = friendName;
        this.friendSubName = friendSubName;
    }

    public int getFriendThumbnail() {
        return friendThumbnail;
    }

    public void setFriendThumbnail(int friendThumbnail) {
        this.friendThumbnail = friendThumbnail;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public String getFriendSubName() {
        return friendSubName;
    }

    public void setFriendSubName(String friendSubName) {
        this.friendSubName = friendSubName;
    }
}