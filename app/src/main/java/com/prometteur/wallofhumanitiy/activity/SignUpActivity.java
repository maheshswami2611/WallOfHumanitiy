package com.prometteur.wallofhumanitiy.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.prometteur.wallofhumanitiy.Interface.APIInterface;
import com.prometteur.wallofhumanitiy.R;
import com.prometteur.wallofhumanitiy.Utility.CommonMethods;
import com.prometteur.wallofhumanitiy.helper.APIClient;
import com.prometteur.wallofhumanitiy.other.StatusResultResponce;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {
    private Button btnSignUpTabLogin, btnSignUp;
    SpotsDialog spotsDialog;
    APIInterface apiInterface;
    EditText edtSignUpFirstName, edtSignUpLastName, edtSignUpEmail, edtSignUpPassword, edtSignUpConfPassword;

    CommonMethods commonMethods;
Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        spotsDialog = new SpotsDialog(this);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        context=this;
        commonMethods = new CommonMethods();
        btnSignUp = findViewById(R.id.btnSignUp);
        edtSignUpFirstName = findViewById(R.id.edtSignUpFirstName);
        edtSignUpLastName = findViewById(R.id.edtSignUpLastName);
        edtSignUpEmail = findViewById(R.id.edtSignUpEmail);
        edtSignUpPassword = findViewById(R.id.edtSignUpPassword);
        edtSignUpConfPassword = findViewById(R.id.edtSignUpConfPassword);


        commonMethods.removeSpace(edtSignUpFirstName);
        commonMethods.removeSpace(edtSignUpLastName);
        commonMethods.removeSpace(edtSignUpEmail);
        commonMethods.removeSpace(edtSignUpPassword);
        commonMethods.removeSpace(edtSignUpConfPassword);

        btnSignUpTabLogin = findViewById(R.id.btnSignUpTabLogin);
        btnSignUpTabLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();

            }
        });


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_fname = edtSignUpFirstName.getText().toString();
                String user_lname = edtSignUpLastName.getText().toString();
                String user_dob = "";
                String user_email = edtSignUpEmail.getText().toString();
                String user_password = edtSignUpPassword.getText().toString();


                if (user_fname.length() > 0) {
                    if (user_lname.length() > 0) {
                        if (commonMethods.isValidEmail(user_email)) {
                            if (commonMethods.isValidPassword(user_password)) {
                                signupApi(user_fname, user_lname, user_email, user_password);

                            } else {
                                edtSignUpPassword.setError("Enter valid Password");
                            }
                        } else {
                            edtSignUpEmail.setError("Enter valid Email Id");
                        }

                    } else {
                        edtSignUpLastName.setError("Enter your last name");
                    }
                } else {
                    edtSignUpFirstName.setError("Enter your first name");
                }


            }
        });

    }


    private void
    signupApi(String user_fname, String user_lname, String user_email, String user_password) {
        spotsDialog.show();
        Call<StatusResultResponce> call = apiInterface.signup(
                user_fname ,
                user_lname ,
                user_email ,
                user_password

        );
        call.enqueue(new Callback<StatusResultResponce>() {
            @Override
            public void onResponse(Call<StatusResultResponce> call, Response<StatusResultResponce> response) {

                if (null != spotsDialog && spotsDialog.isShowing()) {
                    spotsDialog.dismiss();

                }

                StatusResultResponce resource = response.body();

                if(null!=resource  && null!=resource.getStatus())
                if(resource.getStatus().equalsIgnoreCase("1")){
                    Toast.makeText(SignUpActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(SignUpActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(SignUpActivity.this, resource.getMsg(), Toast.LENGTH_SHORT).show();
                }



            }

            @Override
            public void onFailure(Call<StatusResultResponce> call, Throwable t) {
                call.cancel();
            }
        });
    }
}
