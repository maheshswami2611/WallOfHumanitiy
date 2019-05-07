package com.prometteur.wallofhumanitiy.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.prometteur.wallofhumanitiy.Interface.APIInterface;
import com.prometteur.wallofhumanitiy.R;
import com.prometteur.wallofhumanitiy.Utility.CommonMethods;
import com.prometteur.wallofhumanitiy.helper.APIClient;
import com.prometteur.wallofhumanitiy.other.ChangePasswordResp;
import com.prometteur.wallofhumanitiy.other.EmailExistResp;
import com.prometteur.wallofhumanitiy.other.LoginResponce;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private Button btnLogin, btnLoginTabSignUp, btnLoginTabLogin;
    APIInterface apiInterface;
    ArrayList<LoginResponce.Result> loginData;
    SpotsDialog spotsDialog;
    EditText edtUserName;
    TextInputEditText edtPassword;
    CommonMethods commonMethods;
    TextView tvForgetPassword;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        spotsDialog = new SpotsDialog(this);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        context = this;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String status = preferences.getString("Login", "");
        String UserId = preferences.getString("UserId", "");
        if(status.equalsIgnoreCase("True"))
        {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("UserId", UserId);
            startActivity(intent);
            finish();
        }else {
            commonMethods = new CommonMethods();
            edtUserName = findViewById(R.id.edtUserName);
            edtPassword = findViewById(R.id.edtPassword);
            tvForgetPassword = findViewById(R.id.tvForgetPassword);

            commonMethods.removeSpace(edtUserName);
            commonMethods.removeSpace(edtPassword);


            btnLogin = findViewById(R.id.btnLogin);
            btnLoginTabSignUp = findViewById(R.id.btnLoginTabSignUp);
            btnLoginTabLogin = findViewById(R.id.btnLoginTabLogin);


            tvForgetPassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Dialog dialog = new Dialog(context);
                    dialog.setContentView(R.layout.dialog_forget_password);
                    dialog.setTitle("Title...");

                    final LinearLayout llEnterEmail, llCorrectEmail;
                    final EditText edtForgetEmail, edtEnterOtp, edtNewPass, edtNewPassConf;
                    final TextView txtErrorEmail;
                    final ProgressBar progressEdit;
                    final ImageView imgRightEmail;
                    final Button btnDone;


                    llEnterEmail = dialog.findViewById(R.id.llEnterEmail);
                    llCorrectEmail = dialog.findViewById(R.id.llCorrectEmail);
                    edtForgetEmail = dialog.findViewById(R.id.edtForgetEmail);
                    edtEnterOtp = dialog.findViewById(R.id.edtEnterOtp);
                    edtNewPass = dialog.findViewById(R.id.edtNewPass);
                    edtNewPassConf = dialog.findViewById(R.id.edtNewPassConf);
                    txtErrorEmail = dialog.findViewById(R.id.txtErrorEmail);
                    progressEdit = dialog.findViewById(R.id.progressEdit);
                    imgRightEmail = dialog.findViewById(R.id.imgRightEmail);
                    btnDone = dialog.findViewById(R.id.btnDone);

                    imgRightEmail.setVisibility(View.GONE);
                    progressEdit.setVisibility(View.GONE);
                    txtErrorEmail.setVisibility(View.GONE);
                    btnDone.setEnabled(false);
                    edtForgetEmail.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {


                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            if (s.toString().length() > 0) {
                                progressEdit.setVisibility(View.VISIBLE);
                                forgetPasswordAPI(dialog,s.toString(), txtErrorEmail, imgRightEmail, llEnterEmail, llCorrectEmail, progressEdit,edtEnterOtp,edtNewPass,edtNewPassConf,btnDone);
                            } else {
                                progressEdit.setVisibility(View.GONE);
                                imgRightEmail.setVisibility(View.GONE);
                            }
                        }
                    });

                    dialog.show();

                }
            });

            btnLoginTabSignUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                    startActivity(intent);
                    finish();
                }
            });

            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String userName = edtUserName.getText().toString();
                    String userPassword = edtPassword.getText().toString();

                    if (commonMethods.isValidEmail(userName)) {
                        if (commonMethods.isValidPassword(userPassword)) {
                            loginapi(userName, userPassword);

                        } else {
                            edtPassword.setError("Enter valid Password");
                        }
                    } else {
                        edtUserName.setError("Enter valid Email Id");
                    }


                }
            });
        }



    }


    private void loginapi(String str_username, String str_password) {
        spotsDialog.show();
        Call<LoginResponce> call = apiInterface.login(str_username, str_password);
        call.enqueue(new Callback<LoginResponce>() {
            @Override
            public void onResponse(Call<LoginResponce> call, Response<LoginResponce> response) {

                if (null != spotsDialog && spotsDialog.isShowing()) {
                    spotsDialog.dismiss();

                }

                LoginResponce resource = response.body();

                if (null != resource && null != resource.getStatus())
                    if (resource.getStatus() == 1) {
                        if(null!=response.body().getResult()){
                            loginData = response.body().getResult();


                            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("Login","True");
                            editor.putString("UserId",loginData.get(0).getUserId());
                            editor.apply();

                            Toast.makeText(LoginActivity.this, "Successfully logged in...", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }


                    } else {
                        Toast.makeText(LoginActivity.this, resource.getMessage(), Toast.LENGTH_SHORT).show();
                    }


            }

            @Override
            public void onFailure(Call<LoginResponce> call, Throwable t) {
                call.cancel();
            }
        });
    }


    private void forgetPasswordAPI(final Dialog dialog, final String forget_email, final TextView txtErrorEmail,
                                   final ImageView imgRightEmail, final LinearLayout llEnterEmail,
                                   final LinearLayout llCorrectEmail, final ProgressBar progressEdit,
                                   final EditText edtEnterOtp, final EditText edtNewPass,
                                   final EditText edtNewPassConf, final Button btnDone) {
        progressEdit.setVisibility(View.VISIBLE);
        Call<EmailExistResp> call = apiInterface.emailExist(forget_email);
        call.enqueue(new Callback<EmailExistResp>() {
            @Override
            public void onResponse(Call<EmailExistResp> call, Response<EmailExistResp> response) {

                final EmailExistResp resource = response.body();

                if (null != resource && null != resource.getStatus())
                    if (resource.getStatus().equalsIgnoreCase("1")) {
                        txtErrorEmail.setVisibility(View.GONE);
                        imgRightEmail.setVisibility(View.VISIBLE);
                        progressEdit.setVisibility(View.GONE);
                        btnDone.setEnabled(true);

                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                llCorrectEmail.setVisibility(View.VISIBLE);
                            }
                        }, 300);

                        btnDone.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String otp=edtEnterOtp.getText().toString().trim();
                                if(otp.equals(resource.getResult())){
                                    if(edtNewPass.getText().toString().trim().equals(edtNewPassConf.getText().toString().trim())){
                                        changePassword(dialog,forget_email,edtNewPass.getText().toString().trim());
                                    }else {
                                        edtNewPassConf.setError("Password must be same");
                                    }
                                }else {
                                    edtEnterOtp.setError("Enter correct OTP");
                                }
                            }
                        });


                    } else {
                        btnDone.setEnabled(false);
                        txtErrorEmail.setVisibility(View.VISIBLE);
                        progressEdit.setVisibility(View.GONE);
                        imgRightEmail.setVisibility(View.GONE);

                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                llCorrectEmail.setVisibility(View.GONE);
                            }
                        }, 300);
                    }


            }

            @Override
            public void onFailure(Call<EmailExistResp> call, Throwable t) {
                call.cancel();
            }
        });
    }




    private void changePassword(final Dialog dialog, String user_email, String user_password_new) {
        spotsDialog.show();
        Call<ChangePasswordResp> call = apiInterface.changePassword(user_email, user_password_new);
        call.enqueue(new Callback<ChangePasswordResp>() {
            @Override
            public void onResponse(Call<ChangePasswordResp> call, Response<ChangePasswordResp> response) {

                if (null != spotsDialog && spotsDialog.isShowing()) {
                    spotsDialog.dismiss();

                }

                ChangePasswordResp resource = response.body();

                if (null != resource && null != resource.getStatus())
                    if (resource.getStatus().equals("1")) {

                        Toast.makeText(LoginActivity.this, "Password changed successfully", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();

                    } else {
                        Toast.makeText(LoginActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }


            }

            @Override
            public void onFailure(Call<ChangePasswordResp> call, Throwable t) {
                call.cancel();
            }
        });
    }

}
