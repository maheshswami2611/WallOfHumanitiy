package com.prometteur.wallofhumanitiy.other;

import java.io.Serializable;
import java.util.ArrayList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class LoginResponce implements Serializable{

    @SerializedName("result")
    @Expose
    private ArrayList<Result> result = null;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("message")
    @Expose
    private String message;

    public ArrayList<Result> getResult() {
        return result;
    }

    public void setResult(ArrayList<Result> result) {
        this.result = result;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public class Result implements Serializable {

        @SerializedName("user_id")
        @Expose
        private String userId;
        @SerializedName("user_fname")
        @Expose
        private String userFname;
        @SerializedName("user_lname")
        @Expose
        private String userLname;
        @SerializedName("user_username")
        @Expose
        private String userUsername;
        @SerializedName("user_email")
        @Expose
        private String userEmail;
        @SerializedName("user_phone")
        @Expose
        private String userPhone;
        @SerializedName("user_password")
        @Expose
        private String userPassword;
        @SerializedName("user_dob")
        @Expose
        private String userDob;
        @SerializedName("user_profile_img")
        @Expose
        private String userProfileImg;
        @SerializedName("user_banner_img")
        @Expose
        private String userBannerImg;
        @SerializedName("user_occupation")
        @Expose
        private String userOccupation;
        @SerializedName("user_gender")
        @Expose
        private String userGender;
        @SerializedName("user_aboutme")
        @Expose
        private String userAboutme;
        @SerializedName("user_location")
        @Expose
        private String userLocation;
        @SerializedName("user_address")
        @Expose
        private String userAddress;
        @SerializedName("user_fcm_key")
        @Expose
        private String userFcmKey;
        @SerializedName("user_type")
        @Expose
        private String userType;
        @SerializedName("user_session")
        @Expose
        private String userSession;
        @SerializedName("user_forget_status")
        @Expose
        private String userForgetStatus;
        @SerializedName("user_forget_code")
        @Expose
        private String userForgetCode;
        @SerializedName("user_create_date")
        @Expose
        private String userCreateDate;
        @SerializedName("user_update_date")
        @Expose
        private String userUpdateDate;
        @SerializedName("user_create_by")
        @Expose
        private String userCreateBy;
        @SerializedName("user_update_by")
        @Expose
        private String userUpdateBy;
        @SerializedName("user_status")
        @Expose
        private String userStatus;




        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserFname() {
            return userFname;
        }

        public void setUserFname(String userFname) {
            this.userFname = userFname;
        }

        public String getUserLname() {
            return userLname;
        }

        public void setUserLname(String userLname) {
            this.userLname = userLname;
        }

        public String getUserUsername() {
            return userUsername;
        }

        public void setUserUsername(String userUsername) {
            this.userUsername = userUsername;
        }

        public String getUserEmail() {
            return userEmail;
        }

        public void setUserEmail(String userEmail) {
            this.userEmail = userEmail;
        }

        public String getUserPhone() {
            return userPhone;
        }

        public void setUserPhone(String userPhone) {
            this.userPhone = userPhone;
        }

        public String getUserPassword() {
            return userPassword;
        }

        public void setUserPassword(String userPassword) {
            this.userPassword = userPassword;
        }

        public String getUserDob() {
            return userDob;
        }

        public void setUserDob(String userDob) {
            this.userDob = userDob;
        }

        public String getUserProfileImg() {
            return userProfileImg;
        }

        public void setUserProfileImg(String userProfileImg) {
            this.userProfileImg = userProfileImg;
        }

        public String getUserBannerImg() {
            return userBannerImg;
        }

        public void setUserBannerImg(String userBannerImg) {
            this.userBannerImg = userBannerImg;
        }

        public String getUserOccupation() {
            return userOccupation;
        }

        public void setUserOccupation(String userOccupation) {
            this.userOccupation = userOccupation;
        }

        public String getUserGender() {
            return userGender;
        }

        public void setUserGender(String userGender) {
            this.userGender = userGender;
        }

        public String getUserAboutme() {
            return userAboutme;
        }

        public void setUserAboutme(String userAboutme) {
            this.userAboutme = userAboutme;
        }

        public String getUserLocation() {
            return userLocation;
        }

        public void setUserLocation(String userLocation) {
            this.userLocation = userLocation;
        }

        public String getUserAddress() {
            return userAddress;
        }

        public void setUserAddress(String userAddress) {
            this.userAddress = userAddress;
        }

        public String getUserFcmKey() {
            return userFcmKey;
        }

        public void setUserFcmKey(String userFcmKey) {
            this.userFcmKey = userFcmKey;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

        public String getUserSession() {
            return userSession;
        }

        public void setUserSession(String userSession) {
            this.userSession = userSession;
        }

        public String getUserForgetStatus() {
            return userForgetStatus;
        }

        public void setUserForgetStatus(String userForgetStatus) {
            this.userForgetStatus = userForgetStatus;
        }

        public String getUserForgetCode() {
            return userForgetCode;
        }

        public void setUserForgetCode(String userForgetCode) {
            this.userForgetCode = userForgetCode;
        }

        public String getUserCreateDate() {
            return userCreateDate;
        }

        public void setUserCreateDate(String userCreateDate) {
            this.userCreateDate = userCreateDate;
        }

        public String getUserUpdateDate() {
            return userUpdateDate;
        }

        public void setUserUpdateDate(String userUpdateDate) {
            this.userUpdateDate = userUpdateDate;
        }

        public String getUserCreateBy() {
            return userCreateBy;
        }

        public void setUserCreateBy(String userCreateBy) {
            this.userCreateBy = userCreateBy;
        }

        public String getUserUpdateBy() {
            return userUpdateBy;
        }

        public void setUserUpdateBy(String userUpdateBy) {
            this.userUpdateBy = userUpdateBy;
        }

        public String getUserStatus() {
            return userStatus;
        }

        public void setUserStatus(String userStatus) {
            this.userStatus = userStatus;
        }


    }

}