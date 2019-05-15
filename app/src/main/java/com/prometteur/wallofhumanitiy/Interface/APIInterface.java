package com.prometteur.wallofhumanitiy.Interface;


import com.prometteur.wallofhumanitiy.other.ChangePasswordResp;
import com.prometteur.wallofhumanitiy.other.EmailExistResp;
import com.prometteur.wallofhumanitiy.other.FriendListResp;
import com.prometteur.wallofhumanitiy.other.LoginResponce;
import com.prometteur.wallofhumanitiy.other.MemoryListResp;
import com.prometteur.wallofhumanitiy.other.StatusResultResponce;
import com.prometteur.wallofhumanitiy.other.StoreResponce;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by prometteur on 4/4/18.
 */

public interface APIInterface {


    @FormUrlEncoded
    @POST("mLogin")
    Call<LoginResponce> login(@Field("user_name") String str_username, @Field("user_password") String str_password);

    @FormUrlEncoded
    @POST("mForgetPass")
    Call<EmailExistResp> emailExist(@Field("forget_email") String forget_email);

    @FormUrlEncoded
    @POST("mForget")
    Call<ChangePasswordResp> changePassword(@Field("user_email") String user_email, @Field("user_password_new") String user_password_new);


    @FormUrlEncoded
    @POST("madd/register")
    Call<StatusResultResponce> signup(
            @Field(" user_fname ") String user_fname,
            @Field(" user_lname ") String user_lname,
            @Field(" user_email ") String user_email,
            @Field(" user_password ") String user_password,
            @Field(" user_username") String user_username,
            @Field(" user_phone ") String user_phone,
            @Field(" user_dob ") String user_dob,
            @Field(" user_profile_img ") String user_profile_img,
            @Field(" user_banner_img ") String user_banner_img,
            @Field(" user_occupation ") String user_occupation,
            @Field(" user_gender") String user_gender,
            @Field(" user_aboutme ") String user_aboutme,
            @Field(" user_location") String user_location,
            @Field(" user_address ") String user_address,
            @Field(" user_fcm_key ") String user_fcm_key,
            @Field(" user_type ") String user_type,
            @Field(" user_session ") String user_session,
            @Field(" user_forget_status") String user_forget_status,
            @Field(" user_forget_code ") String user_forget_code,
            @Field(" user_create_date ") String user_create_date,
            @Field(" user_create_by ") String user_create_by,
            @Field(" user_update_date ") String user_update_date,
            @Field(" user_update_by ") String user_update_by,
            @Field(" user_status") String user_status
    );

    @FormUrlEncoded
    @POST("mAdd/user")
    Call<StatusResultResponce> updateUser(
            @Field("user_id") String user_id,
            @Field("user_fname") String user_fname,
            @Field("user_lname") String user_lname,
            @Field("user_phone") String user_phone,
            @Field("user_password ") String user_password,
            @Field("user_dob") String user_dob,
            @Field("user_profile_img") String user_profile_img,
            @Field("user_banner_img") String user_banner_img,
            @Field("user_occupation") String user_occupation,
            @Field("user_gender") String user_gender,
            @Field("user_aboutme") String user_aboutme,
            @Field("user_location") String user_location,
            @Field("user_address") String user_address,
            @Field("user_username") String user_username
    );


    @FormUrlEncoded
    @POST("fields/user")
    Call<LoginResponce> getUserInfo(@Field("user_id") String user_id);


    @Multipart
    @POST("mAdd/memory")
    Call<StatusResultResponce> addNewMemory(
            @Part("memory_userid") RequestBody memory_userid,
            @Part("memory_title") RequestBody memory_title,
            @Part("memory_desc") RequestBody memory_desc,
            @Part("memory_location") RequestBody memory_location,

            @Part MultipartBody.Part memory_file_one,
            @Part("memory_one_type") RequestBody memory_one_type,

            @Part MultipartBody.Part memory_file_two,
            @Part("memory_two_type") RequestBody memory_two_type,

            @Part MultipartBody.Part memory_file_three,
            @Part("memory_three_type") RequestBody memory_three_type,

            @Part MultipartBody.Part memory_thumb_one,
            @Part MultipartBody.Part memory_thumb_two,
            @Part MultipartBody.Part memory_thumb_three,


            @Part("memory_lat") RequestBody memory_lat,
            @Part("memory_lng") RequestBody memory_lng,
            @Part("memory_type") RequestBody memory_type,
            @Part("user_session") RequestBody user_session

    );



    @FormUrlEncoded
    @POST("fields/memory")
    Call<MemoryListResp> getMemoryList(
            @Field("memory_userid") String memory_userid,
            @Field("memory_type") String memory_type,
            @Field("user_session") String user_session
    );

    @FormUrlEncoded
    @POST("fields/friend_list")
    Call<FriendListResp> getFriendList(
            @Field("bond_user_id") String bond_user_id,
            @Field("user_session") String user_session
    );



    @FormUrlEncoded
    @POST("mAdd/transmission")
    Call<StatusResultResponce> shareMemory(
            @Field("transmission_userid") String transmission_userid,
            @Field("transmission_to") String transmission_to,
            @Field("transmission_memory_id") String transmission_memory_id,
            @Field("user_session") String user_session
    );



    @FormUrlEncoded
    @POST("fields/store")
    Call<StoreResponce> getStoreDataList(
            @Field("store_userid") String store_userid,
            @Field("user_session") String user_session
    );





}
