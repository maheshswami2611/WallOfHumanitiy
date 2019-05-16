
package com.prometteur.wallofhumanitiy.other;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FriendListResp {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose
    private String message = null;


    @SerializedName("result")
    @Expose
    private List<Message> result = null;

    public List<Message> getResult() {
        return result;
    }

    public void setResult(List<Message> result) {
        this.result = result;
    }

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



    public class Message {

        @SerializedName("isSelected")
        @Expose
        private boolean isSelected;
        @SerializedName("user_id")
        @Expose
        private String userId;
        @SerializedName("user_fname")
        @Expose
        private String userFname;
        @SerializedName("user_lname")
        @Expose
        private String userLname;
        @SerializedName("user_profile_img")
        @Expose
        private String userProfileImg;
        @SerializedName("bond_friend_status")
        @Expose
        private String bondFriendStatus;

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

        public String getUserProfileImg() {
            return userProfileImg;
        }

        public void setUserProfileImg(String userProfileImg) {
            this.userProfileImg = userProfileImg;
        }

        public String getBondFriendStatus() {
            return bondFriendStatus;
        }

        public void setBondFriendStatus(String bondFriendStatus) {
            this.bondFriendStatus = bondFriendStatus;
        }


        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }
    }

}