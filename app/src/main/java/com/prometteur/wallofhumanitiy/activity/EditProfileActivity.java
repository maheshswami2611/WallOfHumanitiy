package com.prometteur.wallofhumanitiy.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.prometteur.wallofhumanitiy.Interface.APIInterface;
import com.prometteur.wallofhumanitiy.R;
import com.prometteur.wallofhumanitiy.Utility.CommonMethods;
import com.prometteur.wallofhumanitiy.helper.APIClient;
import com.prometteur.wallofhumanitiy.other.LoginResponce;
import com.prometteur.wallofhumanitiy.other.StatusResultResponce;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {
    private Button btnSave;
    SpotsDialog spotsDialog;
    APIInterface apiInterface;
    EditText edtFirstName, edtLastName, edtUserName,
            edtDateOfBirth, edtAboutMe, edtOccupation,
            edtEmail, edtAddress, edtPhoneNumber, edtCurrPassword,
            edtConfPassword, edtNewPassword, edtGender;
    TextView txtChangePassword;
    CommonMethods commonMethods;
    LinearLayout llChangePassword;
    boolean clicked = false;
    ScrollView sv;

    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;
    String[] genderItems;
    ArrayList<LoginResponce.User> loginData;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        spotsDialog = new SpotsDialog(this);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        context = this;
        commonMethods = new CommonMethods();
        edtFirstName = findViewById(R.id.edtFirstName);
        edtLastName = findViewById(R.id.edtLastName);
        edtUserName = findViewById(R.id.edtUserName);
        edtDateOfBirth = findViewById(R.id.edtDateOfBirth);
        edtEmail = findViewById(R.id.edtEmail);
        edtPhoneNumber = findViewById(R.id.edtPhoneNumber);
        edtCurrPassword = findViewById(R.id.edtCurrPassword);
        edtConfPassword = findViewById(R.id.edtConfPassword);
        edtOccupation = findViewById(R.id.edtOccupation);
        edtAboutMe = findViewById(R.id.edtAboutMe);
        txtChangePassword = findViewById(R.id.txtChangePassword);
        llChangePassword = findViewById(R.id.llChangePassword);
        edtNewPassword = findViewById(R.id.edtNewPassword);
        edtGender = findViewById(R.id.edtGender);
        edtAddress = findViewById(R.id.edtAddress);

        llChangePassword.setVisibility(View.GONE);

        commonMethods.removeSpace(edtFirstName);
        commonMethods.removeSpace(edtLastName);
        commonMethods.removeSpace(edtEmail);
        commonMethods.removeSpace(edtCurrPassword);
        commonMethods.removeSpace(edtConfPassword);
        sv = (ScrollView) findViewById(R.id.scrollView);
        btnSave = findViewById(R.id.btnSave);
        final Drawable imgDown = getResources().getDrawable(R.mipmap.ic_black_down_arrow);
        final Drawable imgUp = getResources().getDrawable(R.mipmap.ic_black_up_arrow);


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String user_id = preferences.getString("UserId", "");
        getUserInfo(user_id);


        txtChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!clicked) {
                    llChangePassword.setVisibility(View.VISIBLE);
                    clicked = true;
                    sv.post(new Runnable() {
                        public void run() {
                            sv.fullScroll(sv.FOCUS_DOWN);
                            txtChangePassword.setCompoundDrawablesWithIntrinsicBounds(null, null, imgUp, null);
                        }
                    });

                } else {
                    llChangePassword.setVisibility(View.GONE);
                    clicked = false;
                    txtChangePassword.setCompoundDrawablesWithIntrinsicBounds(null, null, imgDown, null);
                }
            }
        });
        genderItems = getResources().getStringArray(R.array.gender_item);
        edtGender.setFocusable(false);
        edtGender.setClickable(true);
        edtGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(EditProfileActivity.this);
                mBuilder.setTitle("Choose an item");
                mBuilder.setSingleChoiceItems(genderItems, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        edtGender.setText(genderItems[i]);
                        dialogInterface.dismiss();
                    }
                });

                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });
        edtDateOfBirth.setFocusable(false);
        edtDateOfBirth.setClickable(true);
        edtDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                datePickerDialog = new DatePickerDialog(EditProfileActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                edtDateOfBirth.setText(day + "/" + (month + 1) + "/" + year);
                            }
                        }, year, month, dayOfMonth);
/*
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
*/
                datePickerDialog.show();
            }
        });


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_fname = edtFirstName.getText().toString().trim();
                String user_lname = edtLastName.getText().toString().trim();
                String user_email = edtEmail.getText().toString().trim();
                String user_password = edtCurrPassword.getText().toString().trim();
                String user_id = loginData.get(0).getUserId();
                String user_username = edtUserName.getText().toString().trim();
                String user_phone = edtPhoneNumber.getText().toString().trim();
                String user_dob = edtDateOfBirth.getText().toString().trim();
                String user_profile_img = loginData.get(0).getUserProfileImg();
                String user_banner_img = loginData.get(0).getUserBannerImg();
                String user_occupation = edtOccupation.getText().toString().trim();
                String user_gender = edtGender.getText().toString();
                if(user_gender.equalsIgnoreCase("Male")){
                    user_gender="0";
                }else if(user_gender.equalsIgnoreCase("Female")){
                    user_gender="1";

                }
                String user_aboutme = edtAboutMe.getText().toString().trim();
                String user_location = loginData.get(0).getUserLocation();
                String user_address = edtAddress.getText().toString();
                String user_fcm_key = loginData.get(0).getUserFcmKey();
                String user_type = loginData.get(0).getUserType();
                String user_session = loginData.get(0).getUserSession();
                String user_forget_status = loginData.get(0).getUserForgetStatus();
                String user_forget_code = loginData.get(0).getUserForgetCode();
                String user_create_date = loginData.get(0).getUserCreateDate();
                String user_create_by = loginData.get(0).getUserCreateBy();
                String user_update_date = loginData.get(0).getUserUpdateDate();
                String user_update_by = loginData.get(0).getUserUpdateBy();
                String user_status = loginData.get(0).getUserStatus();


                if (user_address.length() > 0) {
                    if (user_occupation.length() > 0) {
                        if (user_username.length() > 0) {
                            if (user_fname.length() > 0) {
                                if (user_lname.length() > 0) {
                                    if (commonMethods.isValidEmail(user_email)) {
                                        updateProfileApi(
                                                user_id,
                                                user_fname,
                                                user_lname,
                                                user_phone,
                                                user_password,
                                                user_dob,
                                                user_profile_img,
                                                user_banner_img,
                                                user_occupation,
                                                user_gender,
                                                user_aboutme,
                                                user_location,
                                                user_address,
                                                user_username

                                        );


                                    } else {
                                        edtEmail.setError("Enter valid Email Id");
                                    }

                                } else {
                                    edtLastName.setError("Enter your last name");
                                }
                            } else {
                                edtFirstName.setError("Enter your first name");
                            }
                        } else {
                            edtUserName.setError("Enter your user name");
                        }
                    } else {
                        edtOccupation.setError("Enter you occupation");
                    }
                } else {
                    edtAddress.setError("Enter your address");
                }


            }
        });

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
                    if (resource.getStatus().equalsIgnoreCase("1")) {
                        if (null != response.body().getUser()) {
                            loginData = response.body().getUser();

                            try {
                                if (null != loginData) {
                                    edtFirstName.setText(loginData.get(0).getUserFname());
                                    edtLastName.setText(loginData.get(0).getUserLname());
                                    edtUserName.setText(loginData.get(0).getUserUsername());
                                    edtEmail.setText(loginData.get(0).getUserEmail());
                                    edtPhoneNumber.setText(loginData.get(0).getUserPhone());
                                    edtDateOfBirth.setText(loginData.get(0).getUserDob());
                                    edtOccupation.setText(loginData.get(0).getUserOccupation());
                                    if(loginData.get(0).getUserGender().equalsIgnoreCase("0")){
                                        edtGender.setText("Male");
                                    }else if(loginData.get(0).getUserGender().equalsIgnoreCase("1")){
                                        edtGender.setText("Female");

                                    }
                                    edtAddress.setText(loginData.get(0).getUserAddress());
                                    edtAboutMe.setText(loginData.get(0).getUserAboutme());


                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }

                    } else {
                        Toast.makeText(EditProfileActivity.this, resource.getMessage(), Toast.LENGTH_SHORT).show();
                    }


            }

            @Override
            public void onFailure(Call<LoginResponce> call, Throwable t) {
                call.cancel();
            }
        });
    }


    private void updateProfileApi(
            String user_id,
            String user_fname,
            String user_lname,
            String user_phone,
            String user_password,
            String user_dob,
            String user_profile_img,
            String user_banner_img,
            String user_occupation,
            String user_gender,
            String user_aboutme,
            String user_location,
            String user_address,
            String user_username
    ) {
        spotsDialog.show();
        Call<StatusResultResponce> call = apiInterface.updateUser(
                user_id,
                user_fname,
                user_lname,
                user_phone,
                user_password,
                user_dob,
                user_profile_img,
                user_banner_img,
                user_occupation,
                user_gender,
                user_aboutme,
                user_location,
                user_address,
                user_username

        );
        call.enqueue(new Callback<StatusResultResponce>() {
            @Override
            public void onResponse(Call<StatusResultResponce> call, Response<StatusResultResponce> response) {

                if (null != spotsDialog && spotsDialog.isShowing()) {
                    spotsDialog.dismiss();

                }

                StatusResultResponce resource = response.body();

                if (null != resource && null != resource.getStatus())
                    if (resource.getStatus().equalsIgnoreCase("1")) {
                        Toast.makeText(EditProfileActivity.this, "Profile updated successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(EditProfileActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(EditProfileActivity.this, resource.getMsg(), Toast.LENGTH_SHORT).show();
                    }


            }

            @Override
            public void onFailure(Call<StatusResultResponce> call, Throwable t) {
                call.cancel();
                if (null != spotsDialog && spotsDialog.isShowing()) {
                    spotsDialog.dismiss();

                }
            }
        });
    }


}
