package com.prometteur.wallofhumanitiy.Singleton;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewsListDetails {

    @SerializedName("news_id")
    @Expose
    private String newsId;
    @SerializedName("news_title")
    @Expose
    private String newsTitle;
    @SerializedName("news_image")
    @Expose
    private String newsImage;
    @SerializedName("news_detail")
    @Expose
    private String newsDetail;
    @SerializedName("news_type")
    @Expose
    private String newsType;
    @SerializedName("news_deleted")
    @Expose
    private String newsDeleted;
    @SerializedName("news_create_date")
    @Expose
    private String newsCreateDate;
    @SerializedName("news_update_date")
    @Expose
    private String newsUpdateDate;
    @SerializedName("news_create_by")
    @Expose
    private String newsCreateBy;
    @SerializedName("news_update_by")
    @Expose
    private String newsUpdateBy;
    @SerializedName("news_status")
    @Expose
    private String newsStatus;
    @SerializedName("like_status")
    @Expose
    private String likeStatus;
    @SerializedName("cnt_likes")
    @Expose
    private String cntLikes;
    @SerializedName("keep_categories")
    @Expose
    private String keepCategories;

    public NewsListDetails(String newsId, String newsTitle, String newsImage,
                           String newsDetail, String newsType, String newsDeleted,
                           String newsCreateDate, String newsUpdateDate, String newsCreateBy,
                           String newsUpdateBy, String newsStatus, String likeStatus,
                           String cntLikes, String keepCategories)
    {
        this.newsId = newsId;
        this.newsTitle = newsTitle;
        this.newsImage = newsImage;
        this.newsDetail = newsDetail;
        this.newsType = newsType;
        this.newsDeleted = newsDeleted;
        this.newsCreateDate = newsCreateDate;
        this.newsUpdateDate = newsUpdateDate;
        this.newsCreateBy = newsCreateBy;
        this.newsUpdateBy = newsUpdateBy;
        this.newsStatus = newsStatus;
        this.likeStatus = likeStatus;
        this.cntLikes = cntLikes;
        this.keepCategories = keepCategories;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsImage() {
        return newsImage;
    }

    public void setNewsImage(String newsImage) {
        this.newsImage = newsImage;
    }

    public String getNewsDetail() {
        return newsDetail;
    }

    public void setNewsDetail(String newsDetail) {
        this.newsDetail = newsDetail;
    }

    public String getNewsType() {
        return newsType;
    }

    public void setNewsType(String newsType) {
        this.newsType = newsType;
    }

    public String getNewsDeleted() {
        return newsDeleted;
    }

    public void setNewsDeleted(String newsDeleted) {
        this.newsDeleted = newsDeleted;
    }

    public String getNewsCreateDate() {
        return newsCreateDate;
    }

    public void setNewsCreateDate(String newsCreateDate) {
        this.newsCreateDate = newsCreateDate;
    }

    public String getNewsUpdateDate() {
        return newsUpdateDate;
    }

    public void setNewsUpdateDate(String newsUpdateDate) {
        this.newsUpdateDate = newsUpdateDate;
    }

    public String getNewsCreateBy() {
        return newsCreateBy;
    }

    public void setNewsCreateBy(String newsCreateBy) {
        this.newsCreateBy = newsCreateBy;
    }

    public String getNewsUpdateBy() {
        return newsUpdateBy;
    }

    public void setNewsUpdateBy(String newsUpdateBy) {
        this.newsUpdateBy = newsUpdateBy;
    }

    public String getNewsStatus() {
        return newsStatus;
    }

    public void setNewsStatus(String newsStatus) {
        this.newsStatus = newsStatus;
    }

    public String getLikeStatus() {
        return likeStatus;
    }

    public void setLikeStatus(String likeStatus) {
        this.likeStatus = likeStatus;
    }

    public String getCntLikes() {
        return cntLikes;
    }

    public void setCntLikes(String cntLikes) {
        this.cntLikes = cntLikes;
    }

    public String getKeepCategories() {
        return keepCategories;
    }

    public void setKeepCategories(String keepCategories) {
        this.keepCategories = keepCategories;
    }
}
