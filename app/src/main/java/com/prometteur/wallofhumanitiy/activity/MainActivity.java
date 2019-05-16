package com.prometteur.wallofhumanitiy.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.prometteur.wallofhumanitiy.Interface.APIInterface;
import com.prometteur.wallofhumanitiy.R;
import com.prometteur.wallofhumanitiy.Singleton.CountOfRecordsData;
import com.prometteur.wallofhumanitiy.Singleton.CountOfRecordsDetails;
import com.prometteur.wallofhumanitiy.Utility.Constant;
import com.prometteur.wallofhumanitiy.fragments.FragmentBottomCenter;
import com.prometteur.wallofhumanitiy.fragments.FragmentBottomMemories;
import com.prometteur.wallofhumanitiy.fragments.FragmentBottomNews;
import com.prometteur.wallofhumanitiy.fragments.FragmentBottomNotification;
import com.prometteur.wallofhumanitiy.fragments.FragmentBottomStore;
import com.prometteur.wallofhumanitiy.fragments.FragmentLegacy;
import com.prometteur.wallofhumanitiy.fragments.FragmentPlacesVisited;
import com.prometteur.wallofhumanitiy.fragments.FragmentProfile;
import com.prometteur.wallofhumanitiy.fragments.FragmentSettings;
import com.prometteur.wallofhumanitiy.fragments.FragmentTransmission;
import com.prometteur.wallofhumanitiy.helper.APIClient;
import com.prometteur.wallofhumanitiy.other.LoginResponce;

import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    SpotsDialog spotsDialog;
    APIInterface apiInterface;
    private NavigationView navigationView;
    private DrawerLayout drawer;
    private View navHeader;
    private ImageView imgProfile, navSettings, navLogout;
    private TextView txtName;
    LinearLayout llAlbumPhotos, llVideoAlbum, llPlacesVisited, llTransmission, llLegacy;

    private static final String urlProfileImg = "https://lh3.googleusercontent.com/eCtE_G34M9ygdkmOpYvCag1vBARCmZwnVS6rS5t4JLzJ6QgQSBquM0nuTsCpLhYbKljoyS-txg";

    private String[] activityTitles;
    private boolean shouldLoadHomeFragOnBackPress = true;
    private Handler mHandler;
    ArrayList<LoginResponce.User> loginData;

    private List<CountOfRecordsDetails> countOfRecordsDetails=new ArrayList<>();

    public MainActivity(Context Context) {

    }

    public MainActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupNavigationView();
        spotsDialog = new SpotsDialog(this);
        loadNavHeader();
        apiInterface = APIClient.getClient().create(APIInterface.class);


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String user_id = preferences.getString("UserId", "");

        getUserInfo(user_id);

        getCountOfAllRecord();

        llAlbumPhotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.closeDrawers();

                Intent intent = new Intent(MainActivity.this, AlbumPhotosActivity.class);
                startActivity(intent);
            }

        });

        llVideoAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.closeDrawers();
                Intent intent = new Intent(MainActivity.this, VideoAlbumActivity.class);
                startActivity(intent);
            }

        });

        llPlacesVisited.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushFragment(new FragmentPlacesVisited());

                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawers();
                }

            }

        });
        llLegacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushFragment(new FragmentLegacy());

                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawers();
                }

            }

        });
        llTransmission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushFragment(new FragmentTransmission());

                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawers();
                }

            }

        });

        navSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushFragment(new FragmentSettings());

                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawers();
                }
            }
        });
        navLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawers();
                }

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("Login", "False");
                editor.putString("UserId", "");
                editor.apply();

                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                Toast.makeText(MainActivity.this, "Logged out successfully...", Toast.LENGTH_SHORT).show();
            }
        });

        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawer.isDrawerOpen(GravityCompat.START)) {
                    drawer.closeDrawers();
                }

                Bundle bundle = new Bundle();
                bundle.putSerializable("mylist", loginData);

                FragmentProfile fragmentProfile = new FragmentProfile();
                fragmentProfile.setArguments(bundle);
                pushFragment(fragmentProfile);
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
                    if (resource.getStatus() .equalsIgnoreCase( "1")) {
                        if (null != response.body().getUser()) {
                            loginData = response.body().getUser();


                            if (null != loginData) {
                                txtName.setText(loginData.get(0).getUserFname());

                                Glide.with(MainActivity.this)
                                        .load(urlProfileImg)
                                        .apply(RequestOptions.circleCropTransform())
                                        .into(imgProfile);


                            }
                        }

                    } else {
                        Toast.makeText(MainActivity.this, resource.getMessage(), Toast.LENGTH_SHORT).show();
                    }


            }

            @Override
            public void onFailure(Call<LoginResponce> call, Throwable t) {
                call.cancel();
            }
        });
    }


    public void loadNavHeader() {
        mHandler = new Handler();

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        navHeader = navigationView.getHeaderView(0);
        txtName = (TextView) navHeader.findViewById(R.id.name);
        llAlbumPhotos = (LinearLayout) navHeader.findViewById(R.id.llAlbumPhotos);
        llVideoAlbum = (LinearLayout) navHeader.findViewById(R.id.llVideoAlbum);
        llPlacesVisited = (LinearLayout) navHeader.findViewById(R.id.llPlacesVisited);
        llTransmission = (LinearLayout) navHeader.findViewById(R.id.llTransmission);
        llLegacy = (LinearLayout) navHeader.findViewById(R.id.llLegacy);
        imgProfile = (ImageView) navHeader.findViewById(R.id.img_profile);
        navSettings = (ImageView) navHeader.findViewById(R.id.navSettings);
        navLogout = (ImageView) navHeader.findViewById(R.id.navLogout);
        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, R.string.openDrawer, R.string.closeDrawer) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        drawer.setDrawerListener(actionBarDrawerToggle);


        actionBarDrawerToggle.syncState();

        if (Constant.pressedFromFragment)
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawers();
            } else {
                drawer.openDrawer(GravityCompat.START);

            }

    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
            return;
        }


        super.onBackPressed();
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    private void setupNavigationView() {
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        if (bottomNavigationView != null) {

            // Select first menu item by default and show Fragment accordingly.
            Menu menu = bottomNavigationView.getMenu();
            selectFragment(menu.getItem(0));

            // Set action to perform when any menu-item is selected.
            bottomNavigationView.setOnNavigationItemSelectedListener(
                    new BottomNavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                            selectFragment(item);
                            return false;
                        }
                    });
        }
    }

    /**
     * Perform action when any item is selected.
     *
     * @param item Item that is selected.
     */
    protected void selectFragment(MenuItem item) {

        item.setChecked(true);

        switch (item.getItemId()) {
            case R.id.action_memories:
                pushFragment(new FragmentBottomMemories());
                break;
            case R.id.action_notification:
                pushFragment(new FragmentBottomNotification());
                break;
            case R.id.action_center:
                pushFragment(new FragmentBottomCenter());
                break;
            case R.id.action_news:
                pushFragment(new FragmentBottomNews());
                break;
            case R.id.action_store:
                pushFragment(new FragmentBottomStore());
                break;
        }
    }


    public void pushFragment(Fragment fragment) {
        if (fragment == null)
            return;

        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager != null) {
            FragmentTransaction ft = fragmentManager.beginTransaction();
            if (ft != null) {
                ft.replace(R.id.rootLayout, fragment);
                ft.commit();
            }
        }
    }

    private void getCountOfAllRecord()
    {
        countOfRecordsDetails = new ArrayList<>();
        countOfRecordsDetails.clear();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        String user_id = preferences.getString("UserId", "");
        String user_session = preferences.getString("UserSession", "");
        Log.i("Selected_Params","\t"+user_id+"\t"+user_session);

        Call<CountOfRecordsData> call = apiInterface.getCountOfRecords(user_id, user_session);

        call.enqueue(new Callback<CountOfRecordsData>() {
            @Override
            public void onResponse(Call<CountOfRecordsData> call, Response<CountOfRecordsData> response) {

                if(response.isSuccessful())
                {
                    if(response.body().getStatus().equals("1")){
                    countOfRecordsDetails = response.body().getResult();
                        if(!countOfRecordsDetails.isEmpty())
                        {
                            String str1 = countOfRecordsDetails.get(0).getTotalPhotoes();
                            String str2 = countOfRecordsDetails.get(1).getTotalVideos();
                            String str3 = countOfRecordsDetails.get(2).getTotalPlacesVisited();
                            String str4 = countOfRecordsDetails.get(3).getTotalTransmission();
                            String str5 = countOfRecordsDetails.get(4).getTotalLegacy();

                            TextView photosCount = (TextView)navHeader.findViewById(R.id.photos_count_text);
                            TextView videoCount = (TextView)navHeader.findViewById(R.id.videos_count_text);
                            TextView placesVisitedCount = (TextView)navHeader.findViewById(R.id.placesvisited_count_text);
                            TextView tranmissionCount = (TextView)navHeader.findViewById(R.id.transmission_count_text);
                            TextView legacyCount = (TextView)navHeader.findViewById(R.id.legacy_count_text);

                            photosCount.setText(str1);
                            videoCount.setText(str2);
                            placesVisitedCount.setText(str3);
                            tranmissionCount.setText(str4);
                            legacyCount.setText(str5);
                        }
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, ""+getResources().getString(R.string.data_not_available), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<CountOfRecordsData> call, Throwable t) {
                Toast.makeText(MainActivity.this, ""+getResources().getString(R.string.failed_to_connect_to_server), Toast.LENGTH_SHORT).show();
                Log.i("Response_News_list_2","\t"+countOfRecordsDetails.size());
            }
        });

    }
}

/*
                     String gson = new Gson().toJson(response.body());
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(gson);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    try {
                        JSONArray jsonArray = jsonObject.getJSONArray("message");
                        for(int i = 0; i < jsonArray.length(); i++)
                        {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                            String str1 = jsonObject1.getString("");

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

 */