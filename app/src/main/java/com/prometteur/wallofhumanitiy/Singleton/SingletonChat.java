package com.prometteur.wallofhumanitiy.Singleton;

public class SingletonChat {
    private String messageDate;
    private String senderName;
    private int senderThumbnail;
    private String message;

    public SingletonChat() {
    }

    public SingletonChat(int senderThumbnail, String messageDate, String senderName, String message) {
        this.senderThumbnail = senderThumbnail;
        this.messageDate = messageDate;
        this.senderName = senderName;
        this.message = message;
    }

  public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(String messageDate) {
        this.messageDate = messageDate;
    }

    public int getSenderThumbnail() {
        return senderThumbnail;
    }

    public void setSenderThumbnail(int senderThumbnail) {
        this.senderThumbnail = senderThumbnail;
    }
}