package com.prometteur.wallofhumanitiy.other;

import android.content.Context;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class MemoryListResp {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("result")
    @Expose
    private List<Result> result = null;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



    public static class Result {
        public Result( Context context) {

        }


        @SerializedName("memory_id")
        @Expose
        private String memoryId;
        @SerializedName("memory_userid")
        @Expose
        private String memoryUserid;
        @SerializedName("memory_file_one")
        @Expose
        private String memoryFileOne;
        @SerializedName("memory_file_one_size")
        @Expose
        private String memoryFileOneSize;
        @SerializedName("memory_one_type")
        @Expose
        private String memoryOneType;
        @SerializedName("memory_file_two")
        @Expose
        private String memoryFileTwo;
        @SerializedName("memory_file_two_size")
        @Expose
        private String memoryFileTwoSize;
        @SerializedName("memory_two_type")
        @Expose
        private String memoryTwoType;
        @SerializedName("memory_file_three")
        @Expose
        private String memoryFileThree;
        @SerializedName("memory_file_three_size")
        @Expose
        private String memoryFileThreeSize;
        @SerializedName("memory_three_type")
        @Expose
        private String memoryThreeType;
        @SerializedName("memory_thumb_one")
        @Expose
        private String memoryThumbOne;
        @SerializedName("memory_thumb_two")
        @Expose
        private String memoryThumbTwo;
        @SerializedName("memory_thumb_three")
        @Expose
        private String memoryThumbThree;
        @SerializedName("memory_title")
        @Expose
        private String memoryTitle;
        @SerializedName("memory_location")
        @Expose
        private String memoryLocation;
        @SerializedName("memory_desc")
        @Expose
        private String memoryDesc;
        @SerializedName("memory_lat")
        @Expose
        private String memoryLat;
        @SerializedName("memory_lng")
        @Expose
        private String memoryLng;
        @SerializedName("memory_type")
        @Expose
        private String memoryType;
        @SerializedName("memory_deleted")
        @Expose
        private String memoryDeleted;
        @SerializedName("memory_create_date")
        @Expose
        private String memoryCreateDate;
        @SerializedName("memory_update_date")
        @Expose
        private String memoryUpdateDate;
        @SerializedName("memory_create_by")
        @Expose
        private String memoryCreateBy;
        @SerializedName("memory_update_by")
        @Expose
        private String memoryUpdateBy;
        @SerializedName("memory_status")
        @Expose
        private String memoryStatus;

        public String getMemoryId() {
            return memoryId;
        }

        public void setMemoryId(String memoryId) {
            this.memoryId = memoryId;
        }

        public String getMemoryUserid() {
            return memoryUserid;
        }

        public void setMemoryUserid(String memoryUserid) {
            this.memoryUserid = memoryUserid;
        }

        public String getMemoryFileOne() {
            return memoryFileOne;
        }

        public void setMemoryFileOne(String memoryFileOne) {
            this.memoryFileOne = memoryFileOne;
        }

        public String getMemoryFileOneSize() {
            return memoryFileOneSize;
        }

        public void setMemoryFileOneSize(String memoryFileOneSize) {
            this.memoryFileOneSize = memoryFileOneSize;
        }

        public String getMemoryOneType() {
            return memoryOneType;
        }

        public void setMemoryOneType(String memoryOneType) {
            this.memoryOneType = memoryOneType;
        }

        public String getMemoryFileTwo() {
            return memoryFileTwo;
        }

        public void setMemoryFileTwo(String memoryFileTwo) {
            this.memoryFileTwo = memoryFileTwo;
        }

        public String getMemoryFileTwoSize() {
            return memoryFileTwoSize;
        }

        public void setMemoryFileTwoSize(String memoryFileTwoSize) {
            this.memoryFileTwoSize = memoryFileTwoSize;
        }

        public String getMemoryTwoType() {
            return memoryTwoType;
        }

        public void setMemoryTwoType(String memoryTwoType) {
            this.memoryTwoType = memoryTwoType;
        }

        public String getMemoryFileThree() {
            return memoryFileThree;
        }

        public void setMemoryFileThree(String memoryFileThree) {
            this.memoryFileThree = memoryFileThree;
        }

        public String getMemoryFileThreeSize() {
            return memoryFileThreeSize;
        }

        public void setMemoryFileThreeSize(String memoryFileThreeSize) {
            this.memoryFileThreeSize = memoryFileThreeSize;
        }

        public String getMemoryThreeType() {
            return memoryThreeType;
        }

        public void setMemoryThreeType(String memoryThreeType) {
            this.memoryThreeType = memoryThreeType;
        }

        public String getMemoryThumbOne() {
            return memoryThumbOne;
        }

        public void setMemoryThumbOne(String memoryThumbOne) {
            this.memoryThumbOne = memoryThumbOne;
        }

        public String getMemoryThumbTwo() {
            return memoryThumbTwo;
        }

        public void setMemoryThumbTwo(String memoryThumbTwo) {
            this.memoryThumbTwo = memoryThumbTwo;
        }

        public String getMemoryThumbThree() {
            return memoryThumbThree;
        }

        public void setMemoryThumbThree(String memoryThumbThree) {
            this.memoryThumbThree = memoryThumbThree;
        }

        public String getMemoryTitle() {
            return memoryTitle;
        }

        public void setMemoryTitle(String memoryTitle) {
            this.memoryTitle = memoryTitle;
        }

        public String getMemoryLocation() {
            return memoryLocation;
        }

        public void setMemoryLocation(String memoryLocation) {
            this.memoryLocation = memoryLocation;
        }

        public String getMemoryDesc() {
            return memoryDesc;
        }

        public void setMemoryDesc(String memoryDesc) {
            this.memoryDesc = memoryDesc;
        }

        public String getMemoryLat() {
            return memoryLat;
        }

        public void setMemoryLat(String memoryLat) {
            this.memoryLat = memoryLat;
        }

        public String getMemoryLng() {
            return memoryLng;
        }

        public void setMemoryLng(String memoryLng) {
            this.memoryLng = memoryLng;
        }

        public String getMemoryType() {
            return memoryType;
        }

        public void setMemoryType(String memoryType) {
            this.memoryType = memoryType;
        }

        public String getMemoryDeleted() {
            return memoryDeleted;
        }

        public void setMemoryDeleted(String memoryDeleted) {
            this.memoryDeleted = memoryDeleted;
        }

        public String getMemoryCreateDate() {
            return memoryCreateDate;
        }

        public void setMemoryCreateDate(String memoryCreateDate) {
            this.memoryCreateDate = memoryCreateDate;
        }

        public String getMemoryUpdateDate() {
            return memoryUpdateDate;
        }

        public void setMemoryUpdateDate(String memoryUpdateDate) {
            this.memoryUpdateDate = memoryUpdateDate;
        }

        public String getMemoryCreateBy() {
            return memoryCreateBy;
        }

        public void setMemoryCreateBy(String memoryCreateBy) {
            this.memoryCreateBy = memoryCreateBy;
        }

        public String getMemoryUpdateBy() {
            return memoryUpdateBy;
        }

        public void setMemoryUpdateBy(String memoryUpdateBy) {
            this.memoryUpdateBy = memoryUpdateBy;
        }

        public String getMemoryStatus() {
            return memoryStatus;
        }

        public void setMemoryStatus(String memoryStatus) {
            this.memoryStatus = memoryStatus;
        }
    }

}