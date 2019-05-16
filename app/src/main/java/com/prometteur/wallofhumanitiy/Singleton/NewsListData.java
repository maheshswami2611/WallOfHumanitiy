package com.prometteur.wallofhumanitiy.Singleton;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsListData {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private List<NewsListDetails> message = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<NewsListDetails> getMessage() {
        return message;
    }

    public void setMessage(List<NewsListDetails> message) {
        this.message = message;
    }
}
