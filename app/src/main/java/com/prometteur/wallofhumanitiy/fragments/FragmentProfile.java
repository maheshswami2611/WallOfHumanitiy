package com.prometteur.wallofhumanitiy.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.prometteur.wallofhumanitiy.Interface.APIInterface;
import com.prometteur.wallofhumanitiy.R;
import com.prometteur.wallofhumanitiy.Utility.Constant;
import com.prometteur.wallofhumanitiy.activity.EditProfileActivity;
import com.prometteur.wallofhumanitiy.activity.MainActivity;
import com.prometteur.wallofhumanitiy.helper.APIClient;
import com.prometteur.wallofhumanitiy.other.LoginResponce;

import java.util.ArrayList;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentProfile extends Fragment {
    ImageView imgProfile,imgEditProfile;
    TextView tabAbout, tabPhotos, tabVideos, tabPlacesVisited, tabFriends, tabFollowers;
    private static final String urlProfileImg = "https://lh3.googleusercontent.com/eCtE_G34M9ygdkmOpYvCag1vBARCmZwnVS6rS5t4JLzJ6QgQSBquM0nuTsCpLhYbKljoyS-txg";
    ArrayList<LoginResponce.Result> loginData;
    SpotsDialog spotsDialog;
    APIInterface apiInterface;
    TextView txtName,txtAboutMe, txtDOB, txtMobileNumber, txtGender, txtAddress, txtCity, txtCountry, txtPostCode;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        imgProfile = view.findViewById(R.id.imgProfile);


        loginData = (ArrayList<LoginResponce.Result>) getActivity().getIntent().getSerializableExtra("mylist");

        txtName = view.findViewById(R.id.txtName);
        txtAboutMe = view.findViewById(R.id.txtAboutMe);
        txtDOB = view.findViewById(R.id.txtDOB);
        txtMobileNumber = view.findViewById(R.id.txtMobileNumber);
        txtGender = view.findViewById(R.id.txtGender);
        txtAddress = view.findViewById(R.id.txtAddress);
        txtCity = view.findViewById(R.id.txtCity);
        txtCountry = view.findViewById(R.id.txtCountry);
        txtPostCode = view.findViewById(R.id.txtPostCode);
        imgEditProfile = view.findViewById(R.id.imgEditProfile);



        spotsDialog = new SpotsDialog(getActivity());
        apiInterface = APIClient.getClient().create(APIInterface.class);


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String user_id = preferences.getString("UserId", "");


        getUserInfo(user_id);





        imgEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), EditProfileActivity.class);
                startActivity(intent);
            }
        });




        Glide.with(getActivity())
                .load(urlProfileImg)
                .apply(RequestOptions.circleCropTransform())
                .into(imgProfile);


        tabAbout = view.findViewById(R.id.tabAbout);
        tabPhotos = view.findViewById(R.id.tabPhotos);
        tabVideos = view.findViewById(R.id.tabVideos);
        tabPlacesVisited = view.findViewById(R.id.tabPlacesVisited);
        tabFriends = view.findViewById(R.id.tabFriends);
        tabFollowers = view.findViewById(R.id.tabFollowers);


        tabAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "tabAbout", Toast.LENGTH_SHORT).show();

            }
        });

        tabPhotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "tabPhotos", Toast.LENGTH_SHORT).show();

            }
        });

        tabVideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "tabVideos", Toast.LENGTH_SHORT).show();

            }
        });

        tabPlacesVisited.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "tabPlacesVisited", Toast.LENGTH_SHORT).show();

            }
        });

        tabFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "tabFriends", Toast.LENGTH_SHORT).show();

            }
        });

        tabFollowers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "tabFollowers", Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imgProfile.bringToFront();

    }



    private void getUserInfo(String user_id) {
        spotsDialog.show();
        Call<LoginResponce> call = apiInterface.getUserInfo(user_id);
        call.enqueue(new Callback<LoginResponce>() {
            @Override
            public void onResponse(Call<LoginResponce> call, Response<LoginResponce> response) {

                if (null != spotsDialog && spotsDialog.isShowing()) {
                    spotsDialog.dismiss();

                }

                LoginResponce resource = response.body();

                if (null != resource && null != resource.getStatus())
                    if (resource.getStatus() == 1) {
                        if (null != response.body().getResult()) {
                            loginData = response.body().getResult();


                            if (null != loginData) {
                                if(null!=loginData)
                                    if(loginData.size()>0){
                                        String user_id=loginData.get(0).getUserId();
                                        String user_fname=loginData.get(0).getUserFname();
                                        String user_lname=loginData.get(0).getUserLname();
                                        String user_username=loginData.get(0).getUserUsername();
                                        String user_email=loginData.get(0).getUserEmail();
                                        String user_phone=loginData.get(0).getUserPhone();
                                        String user_password=loginData.get(0).getUserPassword();
                                        String user_dob=loginData.get(0).getUserDob();
                                        String user_profile_img=loginData.get(0).getUserProfileImg();
                                        String user_banner_img=loginData.get(0).getUserBannerImg();
                                        String user_occupation=loginData.get(0).getUserOccupation();
                                        String user_gender=loginData.get(0).getUserGender();
                                        String user_aboutme=loginData.get(0).getUserAboutme();
                                        String user_location=loginData.get(0).getUserLocation();
                                        String user_address=loginData.get(0).getUserAddress();
                                        String user_fcm_key=loginData.get(0).getUserFcmKey();
                                        String user_type=loginData.get(0).getUserFname();
                                        String user_session=loginData.get(0).getUserSession();
                                        String user_forget_status=loginData.get(0).getUserForgetStatus();
                                        String user_forget_code=loginData.get(0).getUserForgetCode();
                                        String user_create_date=loginData.get(0).getUserCreateDate();
                                        String user_create_by=loginData.get(0).getUserCreateBy();
                                        String user_update_date=loginData.get(0).getUserUpdateDate();
                                        String user_update_by=loginData.get(0).getUserUpdateBy();
                                        String user_status=loginData.get(0).getUserStatus();


                                        txtName.setText(user_fname);
                                        txtAboutMe.setText(user_aboutme);
                                        txtDOB.setText(user_dob);
                                        txtMobileNumber.setText(user_phone);
                                        txtGender.setText(user_gender);
                                        txtAddress.setText(user_address);
            /*txtCity.setText(loginData.get(0).getUserLocation());
            txtCountry.setText(loginData.get(0).getUserLocation());
            txtPostCode.setText(loginData.get(0).getUserLocation());*/
                                    }
                                Glide.with(getActivity())
                                        .load(urlProfileImg)
                                        .apply(RequestOptions.circleCropTransform())
                                        .into(imgProfile);


                            }
                        }

                    } else {
                        Toast.makeText(getActivity(), resource.getMessage(), Toast.LENGTH_SHORT).show();
                    }


            }

            @Override
            public void onFailure(Call<LoginResponce> call, Throwable t) {
                call.cancel();
            }
        });
    }


}