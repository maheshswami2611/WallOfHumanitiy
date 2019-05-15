
package com.prometteur.wallofhumanitiy.other;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StoreResponce {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("total_photo_size")
    @Expose
    private String totalPhotoSize;
    @SerializedName("total_video_size")
    @Expose
    private String totalVideoSize;
    @SerializedName("total_plans_gb")
    @Expose
    private List<TotalPlansGb> totalPlansGb = null;
    @SerializedName("total_plans_month")
    @Expose
    private List<TotalPlansMonth> totalPlansMonth = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTotalPhotoSize() {
        return totalPhotoSize;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setTotalPhotoSize(String totalPhotoSize) {
        this.totalPhotoSize = totalPhotoSize;
    }

    public String getTotalVideoSize() {
        return totalVideoSize;
    }

    public void setTotalVideoSize(String totalVideoSize) {
        this.totalVideoSize = totalVideoSize;
    }

    public List<TotalPlansGb> getTotalPlansGb() {
        return totalPlansGb;
    }

    public void setTotalPlansGb(List<TotalPlansGb> totalPlansGb) {
        this.totalPlansGb = totalPlansGb;
    }

    public List<TotalPlansMonth> getTotalPlansMonth() {
        return totalPlansMonth;
    }

    public void setTotalPlansMonth(List<TotalPlansMonth> totalPlansMonth) {
        this.totalPlansMonth = totalPlansMonth;
    }




    public class TotalPlansGb {

        @SerializedName("plans_gb_id")
        @Expose
        private String plansGbId;
        @SerializedName("plans_gb_value")
        @Expose
        private String plansGbValue;
        @SerializedName("plans_gb_rupee")
        @Expose
        private String plansGbRupee;
        @SerializedName("plans_gb_allocate_userid")
        @Expose
        private String plansGbAllocateUserid;
        @SerializedName("plans_gb_deleted")
        @Expose
        private String plansGbDeleted;
        @SerializedName("plans_gb_create_date")
        @Expose
        private String plansGbCreateDate;
        @SerializedName("plans_gb_update_date")
        @Expose
        private String plansGbUpdateDate;
        @SerializedName("plans_gb_create_by")
        @Expose
        private String plansGbCreateBy;
        @SerializedName("plans_gb_update_by")
        @Expose
        private String plansGbUpdateBy;
        @SerializedName("plans_gb_status")
        @Expose
        private String plansGbStatus;
        @SerializedName("user_plan_status")
        @Expose
        private String userPlanStatus;

        public String getPlansGbId() {
            return plansGbId;
        }

        public void setPlansGbId(String plansGbId) {
            this.plansGbId = plansGbId;
        }

        public String getPlansGbValue() {
            return plansGbValue;
        }

        public void setPlansGbValue(String plansGbValue) {
            this.plansGbValue = plansGbValue;
        }

        public String getPlansGbRupee() {
            return plansGbRupee;
        }

        public void setPlansGbRupee(String plansGbRupee) {
            this.plansGbRupee = plansGbRupee;
        }

        public String getPlansGbAllocateUserid() {
            return plansGbAllocateUserid;
        }

        public void setPlansGbAllocateUserid(String plansGbAllocateUserid) {
            this.plansGbAllocateUserid = plansGbAllocateUserid;
        }

        public String getPlansGbDeleted() {
            return plansGbDeleted;
        }

        public void setPlansGbDeleted(String plansGbDeleted) {
            this.plansGbDeleted = plansGbDeleted;
        }

        public String getPlansGbCreateDate() {
            return plansGbCreateDate;
        }

        public void setPlansGbCreateDate(String plansGbCreateDate) {
            this.plansGbCreateDate = plansGbCreateDate;
        }

        public String getPlansGbUpdateDate() {
            return plansGbUpdateDate;
        }

        public void setPlansGbUpdateDate(String plansGbUpdateDate) {
            this.plansGbUpdateDate = plansGbUpdateDate;
        }

        public String getPlansGbCreateBy() {
            return plansGbCreateBy;
        }

        public void setPlansGbCreateBy(String plansGbCreateBy) {
            this.plansGbCreateBy = plansGbCreateBy;
        }

        public String getPlansGbUpdateBy() {
            return plansGbUpdateBy;
        }

        public void setPlansGbUpdateBy(String plansGbUpdateBy) {
            this.plansGbUpdateBy = plansGbUpdateBy;
        }

        public String getPlansGbStatus() {
            return plansGbStatus;
        }

        public void setPlansGbStatus(String plansGbStatus) {
            this.plansGbStatus = plansGbStatus;
        }

        public String getUserPlanStatus() {
            return userPlanStatus;
        }

        public void setUserPlanStatus(String userPlanStatus) {
            this.userPlanStatus = userPlanStatus;
        }
    }
    public class TotalPlansMonth {

        @SerializedName("plans_month_id")
        @Expose
        private String plansMonthId;
        @SerializedName("plans_month_value")
        @Expose
        private String plansMonthValue;
        @SerializedName("plans_month_rupee")
        @Expose
        private String plansMonthRupee;
        @SerializedName("plans_month_allocate_userid")
        @Expose
        private String plansMonthAllocateUserid;
        @SerializedName("plans_month_deleted")
        @Expose
        private String plansMonthDeleted;
        @SerializedName("plans_month_create_date")
        @Expose
        private String plansMonthCreateDate;
        @SerializedName("plans_month_update_date")
        @Expose
        private String plansMonthUpdateDate;
        @SerializedName("plans_month_create_by")
        @Expose
        private String plansMonthCreateBy;
        @SerializedName("plans_month_update_by")
        @Expose
        private String plansMonthUpdateBy;
        @SerializedName("plans_month_status")
        @Expose
        private String plansMonthStatus;
        @SerializedName("user_plan_status")
        @Expose
        private String userPlanStatus;

        public String getPlansMonthId() {
            return plansMonthId;
        }

        public void setPlansMonthId(String plansMonthId) {
            this.plansMonthId = plansMonthId;
        }

        public String getPlansMonthValue() {
            return plansMonthValue;
        }

        public void setPlansMonthValue(String plansMonthValue) {
            this.plansMonthValue = plansMonthValue;
        }

        public String getPlansMonthRupee() {
            return plansMonthRupee;
        }

        public void setPlansMonthRupee(String plansMonthRupee) {
            this.plansMonthRupee = plansMonthRupee;
        }

        public String getPlansMonthAllocateUserid() {
            return plansMonthAllocateUserid;
        }

        public void setPlansMonthAllocateUserid(String plansMonthAllocateUserid) {
            this.plansMonthAllocateUserid = plansMonthAllocateUserid;
        }

        public String getPlansMonthDeleted() {
            return plansMonthDeleted;
        }

        public void setPlansMonthDeleted(String plansMonthDeleted) {
            this.plansMonthDeleted = plansMonthDeleted;
        }

        public String getPlansMonthCreateDate() {
            return plansMonthCreateDate;
        }

        public void setPlansMonthCreateDate(String plansMonthCreateDate) {
            this.plansMonthCreateDate = plansMonthCreateDate;
        }

        public String getPlansMonthUpdateDate() {
            return plansMonthUpdateDate;
        }

        public void setPlansMonthUpdateDate(String plansMonthUpdateDate) {
            this.plansMonthUpdateDate = plansMonthUpdateDate;
        }

        public String getPlansMonthCreateBy() {
            return plansMonthCreateBy;
        }

        public void setPlansMonthCreateBy(String plansMonthCreateBy) {
            this.plansMonthCreateBy = plansMonthCreateBy;
        }

        public String getPlansMonthUpdateBy() {
            return plansMonthUpdateBy;
        }

        public void setPlansMonthUpdateBy(String plansMonthUpdateBy) {
            this.plansMonthUpdateBy = plansMonthUpdateBy;
        }

        public String getPlansMonthStatus() {
            return plansMonthStatus;
        }

        public void setPlansMonthStatus(String plansMonthStatus) {
            this.plansMonthStatus = plansMonthStatus;
        }

        public String getUserPlanStatus() {
            return userPlanStatus;
        }

        public void setUserPlanStatus(String userPlanStatus) {
            this.userPlanStatus = userPlanStatus;
        }
    }

    }