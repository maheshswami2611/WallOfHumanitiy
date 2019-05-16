package com.prometteur.wallofhumanitiy.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.prometteur.wallofhumanitiy.Interface.APIInterface;
import com.prometteur.wallofhumanitiy.R;
import com.prometteur.wallofhumanitiy.fragments.FriendsAdapter;
import com.prometteur.wallofhumanitiy.helper.APIClient;
import com.prometteur.wallofhumanitiy.other.FriendListResp;
import com.prometteur.wallofhumanitiy.other.StatusResultResponce;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectShareFriendsActivity extends AppCompatActivity {
    ImageView imgBack;
    EditText edtFriendSearch;
    SpotsDialog spotsDialog;
    APIInterface apiInterface;
    Context mContext;
    ImageView imgDone;

    public List<FriendListResp.Message> friendList = new ArrayList<>();
    private RecyclerView recyclerView;
    private FriendsAdapter mAdapter;
    String mainuser_id = "";
    String mainuser_session="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_select_share_friends);
        mContext =this;

        apiInterface = APIClient.getClient().create(APIInterface.class);
        spotsDialog = new SpotsDialog(this);

        imgDone = findViewById(R.id.imgDone);
        imgBack = findViewById(R.id.imgBack);
        edtFriendSearch = findViewById(R.id.edtFriendSearch);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(SelectShareFriendsActivity.this);
        mainuser_id = preferences.getString("UserId", "");
        mainuser_session= preferences.getString("UserSession", "");

        getFriendList(mainuser_id,mainuser_session);


        imgDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> selectedFreidns=new ArrayList<>();
                for(int i=0;i<friendList.size();i++){
                    if(friendList.get(i).isSelected()){
                        selectedFreidns.add(friendList.get(i).getUserId());
                      }
                }

                shareMemory(mainuser_id,
                        selectedFreidns.toString(),
                        "123",
                        mainuser_session);

            }
        });
    }



    private void getFriendList(final String bond_user_id, final String user_session) {
        spotsDialog.show();
        Call<FriendListResp> call = apiInterface.getFriendList(bond_user_id, user_session);
        call.enqueue(new Callback<FriendListResp>() {
            @Override
            public void onResponse(Call<FriendListResp> call, Response<FriendListResp> response) {

                try {

                    FriendListResp resource = response.body();


                    if (resource.getStatus().toString().equals("1")) {
                        friendList = resource.getResult();

                        for (int i=0;i<friendList.size();i++){
                            friendList.get(i).setSelected(false);
                        }



                        mAdapter = new FriendsAdapter(SelectShareFriendsActivity.this,friendList);
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                        recyclerView.setLayoutManager(mLayoutManager);
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                        recyclerView.setAdapter(mAdapter);



                    } else  {
                        Toast.makeText(mContext, "" + resource.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    if (null != spotsDialog && spotsDialog.isShowing()) {
                        spotsDialog.dismiss();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("Error", e.toString());
                }
            }

            @Override
            public void onFailure(Call<FriendListResp> call, Throwable t) {
                call.cancel();
            }
        });
    }



    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


    private void shareMemory(
            String transmission_userid ,
            String transmission_to ,
            String transmission_memory_id ,
            String user_session
    ) {
        spotsDialog.show();
        Call<StatusResultResponce> call = apiInterface.shareMemory(
                transmission_userid ,
                transmission_to ,
                transmission_memory_id ,
                user_session

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
                        Toast.makeText(SelectShareFriendsActivity.this, "Memory Shared successfully", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SelectShareFriendsActivity.this, resource.getMessage(), Toast.LENGTH_SHORT).show();
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
