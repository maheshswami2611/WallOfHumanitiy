package com.prometteur.wallofhumanitiy.Singleton;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CountOfRecordsData {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message = null;


    @SerializedName("result")
    @Expose
    private List<CountOfRecordsDetails> result = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<CountOfRecordsDetails> getResult() {
        return result;
    }

    public void setResult(List<CountOfRecordsDetails> result) {
        this.result = result;
    }
}
