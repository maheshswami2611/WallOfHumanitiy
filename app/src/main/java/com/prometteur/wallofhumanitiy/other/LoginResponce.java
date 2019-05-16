package com.prometteur.wallofhumanitiy.other;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponce {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("user")
    @Expose
    private ArrayList<User> user = null;
    @SerializedName("album")
    @Expose
    private ArrayList<Object> album = null;
    @SerializedName("video")
    @Expose
    private ArrayList<Object> video = null;
    @SerializedName("places")
    @Expose
    private ArrayList<Object> places = null;
    @SerializedName("friend_list")
    @Expose
    private Object friendList;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<User> getUser() {
        return user;
    }

    public void setUser(ArrayList<User> user) {
        this.user = user;
    }

    public List<Object> getAlbum() {
        return album;
    }

    public void setAlbum(ArrayList<Object> album) {
        this.album = album;
    }

    public List<Object> getVideo() {
        return video;
    }

    public void setVideo(ArrayList<Object> video) {
        this.video = video;
    }

    public List<Object> getPlaces() {
        return places;
    }

    public void setPlaces(ArrayList<Object> places) {
        this.places = places;
    }

    public Object getFriendList() {
        return friendList;
    }

    public void setFriendList(Object friendList) {
        this.friendList = friendList;
    }



    public class User implements Serializable {

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
        @SerializedName("user_profile_img_size")
        @Expose
        private String userProfileImgSize;
        @SerializedName("user_banner_img")
        @Expose
        private String userBannerImg;
        @SerializedName("user_banner_img_size")
        @Expose
        private String userBannerImgSize;
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
        @SerializedName("user_search_engine_setting")
        @Expose
        private String userSearchEngineSetting;
        @SerializedName("user_active_camera_setting")
        @Expose
        private String userActiveCameraSetting;
        @SerializedName("user_notification_setting")
        @Expose
        private String userNotificationSetting;
        @SerializedName("user_video_setting")
        @Expose
        private String userVideoSetting;
        @SerializedName("user_location_setting")
        @Expose
        private String userLocationSetting;
        @SerializedName("user_language_setting")
        @Expose
        private String userLanguageSetting;
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

        public String getUserProfileImgSize() {
            return userProfileImgSize;
        }

        public void setUserProfileImgSize(String userProfileImgSize) {
            this.userProfileImgSize = userProfileImgSize;
        }

        public String getUserBannerImg() {
            return userBannerImg;
        }

        public void setUserBannerImg(String userBannerImg) {
            this.userBannerImg = userBannerImg;
        }

        public String getUserBannerImgSize() {
            return userBannerImgSize;
        }

        public void setUserBannerImgSize(String userBannerImgSize) {
            this.userBannerImgSize = userBannerImgSize;
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

        public String getUserSearchEngineSetting() {
            return userSearchEngineSetting;
        }

        public void setUserSearchEngineSetting(String userSearchEngineSetting) {
            this.userSearchEngineSetting = userSearchEngineSetting;
        }

        public String getUserActiveCameraSetting() {
            return userActiveCameraSetting;
        }

        public void setUserActiveCameraSetting(String userActiveCameraSetting) {
            this.userActiveCameraSetting = userActiveCameraSetting;
        }

        public String getUserNotificationSetting() {
            return userNotificationSetting;
        }

        public void setUserNotificationSetting(String userNotificationSetting) {
            this.userNotificationSetting = userNotificationSetting;
        }

        public String getUserVideoSetting() {
            return userVideoSetting;
        }

        public void setUserVideoSetting(String userVideoSetting) {
            this.userVideoSetting = userVideoSetting;
        }

        public String getUserLocationSetting() {
            return userLocationSetting;
        }

        public void setUserLocationSetting(String userLocationSetting) {
            this.userLocationSetting = userLocationSetting;
        }

        public String getUserLanguageSetting() {
            return userLanguageSetting;
        }

        public void setUserLanguageSetting(String userLanguageSetting) {
            this.userLanguageSetting = userLanguageSetting;
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