package com.prometteur.wallofhumanitiy.fragments;
import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Rect;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.button.MaterialButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.prometteur.wallofhumanitiy.activity.MainActivity;
import com.prometteur.wallofhumanitiy.adapters.MemoriesAdapter;
import com.prometteur.wallofhumanitiy.R;
import com.prometteur.wallofhumanitiy.Singleton.SingletonMemories;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.app.Activity.RESULT_OK;

public class FragmentMainMemories extends Fragment {
    ImageView imgBack;
    private RecyclerView recyclerView;
    private MemoriesAdapter adapter;
    private List<SingletonMemories> singletonMemoriesList;
    MainActivity mainActivity;
    Context mContext;
    TextView txtSelectedFile;
    final int PERMISSION_REQUEST_CODE = 200;
    private static final int PICK_IMAGE_ID = 234; // the number doesn't matter

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_memories, container, false);
        mainActivity=new MainActivity(getActivity().getApplicationContext());
        mContext=(MainActivity)getActivity();
        imgBack = view.findViewById(R.id.imgBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).pushFragment(new FragmentBottomMemories());
            }
        });


        recyclerView = (RecyclerView)view. findViewById(R.id.recycler_view);

        singletonMemoriesList = new ArrayList<>();
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new MemoriesAdapter(getActivity(), singletonMemoriesList, new MemoriesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(SingletonMemories item) {

                final Dialog openDialog = new Dialog(mContext);
                openDialog.setContentView(R.layout.custom_add_memory_dialog);
                openDialog.setTitle("");

                ImageView dialogCloseButton = openDialog.findViewById(R.id.dialogCloseButton);
                dialogCloseButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        openDialog.dismiss();
                    }
                });

                final Button btnAddPhotoVideo = openDialog.findViewById(R.id.btnAddPhotoVideo);
                final Button btnSaveMemory = openDialog.findViewById(R.id.btnSaveMemory);
                final Button btnShareMemory = openDialog.findViewById(R.id.btnShareMemory);
                final EditText edtMemoryLocation = openDialog.findViewById(R.id.edtMemoryLocation);
                final EditText edtMemoryTitle = openDialog.findViewById(R.id.edtMemoryTitle);
                final EditText edtMemoryDescription = openDialog.findViewById(R.id.edtMemoryDescription);
                final View viewTextTab = openDialog.findViewById(R.id.viewTextTab);
                final View viewPhotoAndVideoTab = openDialog.findViewById(R.id.viewPhotoAndVideoTab);
                final TextView tabText = openDialog.findViewById(R.id.txtTextTab);
                txtSelectedFile = openDialog.findViewById(R.id.txtSelectedFile);
                final TextView tabPhotoAndVideo = openDialog.findViewById(R.id.txtPhotoAndVideoTab);
                tabText.setTextColor(mContext.getResources().getColor(R.color.colorDarkGreen));
                viewTextTab.setBackgroundColor(mContext.getResources().getColor(R.color.colorDarkGreen));
                btnAddPhotoVideo.setVisibility(View.GONE);


                if (checkPermission()) {
                    find_Location(edtMemoryLocation);
                } else {
                    requestPermission();
                }



                tabText.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tabText.setTextColor(mContext.getResources().getColor(R.color.colorDarkGreen));
                        viewTextTab.setBackgroundColor(mContext.getResources().getColor(R.color.colorDarkGreen));
                        tabPhotoAndVideo.setTextColor(mContext.getResources().getColor(R.color.album_title));
                        viewPhotoAndVideoTab.setBackgroundColor(mContext.getResources().getColor(R.color.album_title));
                        btnAddPhotoVideo.setVisibility(View.GONE);

                    }
                });
                tabPhotoAndVideo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tabPhotoAndVideo.setTextColor(mContext.getResources().getColor(R.color.colorDarkGreen));
                        viewPhotoAndVideoTab.setBackgroundColor(mContext.getResources().getColor(R.color.colorDarkGreen));
                        tabText.setTextColor(mContext.getResources().getColor(R.color.album_title));
                        viewTextTab.setBackgroundColor(mContext.getResources().getColor(R.color.album_title));
                        btnAddPhotoVideo.setVisibility(View.VISIBLE);
                    }
                });

                btnAddPhotoVideo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent pickIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        pickIntent.setType("image/* video/*");
                        startActivityForResult(pickIntent, 0);
                    }
                });
                openDialog.show();


            }
        });
        recyclerView.setAdapter(adapter);




        prepareAlbums();
        return view;
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission((MainActivity)getActivity(), ACCESS_FINE_LOCATION);
        /* int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA);
         */
        return result == PackageManager.PERMISSION_GRANTED /*&& result1 == PackageManager.PERMISSION_GRANTED*/;
    }

    private void requestPermission() {

        ActivityCompat.requestPermissions((MainActivity)getActivity(), new String[]{ACCESS_FINE_LOCATION/*, CAMERA*/}, PERMISSION_REQUEST_CODE);

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0) {

                    boolean locationAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    // boolean cameraAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                    if (locationAccepted /*&& cameraAccepted*/)
                        Toast.makeText((MainActivity)getActivity(), "Permission Granted.", Toast.LENGTH_SHORT).show();
                    else {
                        Toast.makeText((MainActivity)getActivity(), "Permission Denied.", Toast.LENGTH_SHORT).show();

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (shouldShowRequestPermissionRationale(ACCESS_FINE_LOCATION)) {
                                showMessageOKCancel("You need to allow access to both the permissions",
                                        new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                    requestPermissions(new String[]{ACCESS_FINE_LOCATION/*, CAMERA*/},
                                                            PERMISSION_REQUEST_CODE);
                                                }
                                            }
                                        });
                                return;
                            }
                        }

                    }
                }


                break;
        }
    }


    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder((MainActivity)getActivity())
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            Uri selectedMediaUri = data.getData();
            String uriString=selectedMediaUri.toString();
            if (uriString.contains("image")) {
                txtSelectedFile.setText(txtSelectedFile+", "+selectedMediaUri.toString());
            } else if (uriString.contains("video")) {
                txtSelectedFile.setText(txtSelectedFile+", "+selectedMediaUri.toString());
            }
        }
    }



    public void onClickCalled() {
        Toast.makeText(((MainActivity)getActivity()).getApplicationContext(), "Yes Clicked", Toast.LENGTH_SHORT).show();
    }
    private void prepareAlbums() {
        int[] covers = new int[]{
                R.drawable.album1,
                R.drawable.album3,
                R.drawable.album5,
                R.drawable.album6};

        SingletonMemories a = new SingletonMemories("True Romance", "15", "1", covers[0]);
        singletonMemoriesList.add(a);

        a = new SingletonMemories("Xscpae", "55", "30", covers[1]);
        singletonMemoriesList.add(a);

        a = new SingletonMemories("Maroon 5", "23", "100", covers[2]);
        singletonMemoriesList.add(a);


        a = new SingletonMemories("", "", "", covers[2]);
        singletonMemoriesList.add(a);


        adapter.notifyDataSetChanged();
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    public void find_Location(EditText edtMemoryLocation) {
        try {

        Toast.makeText(((MainActivity)getActivity()), "Fetching location. Please wait.", Toast.LENGTH_SHORT).show();
        String location_context = Context.LOCATION_SERVICE;
        LocationManager locationManager = (LocationManager) ((MainActivity)getActivity()).getSystemService(location_context);
        List<String> providers = locationManager.getProviders(true);
        for (String provider : providers) {
            if (ActivityCompat.checkSelfPermission(((MainActivity)getActivity()), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(((MainActivity)getActivity()), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            locationManager.requestLocationUpdates(provider, 1000, 0,
                    new LocationListener() {

                        public void onLocationChanged(Location location) {
                        }

                        public void onProviderDisabled(String provider) {
                        }

                        public void onProviderEnabled(String provider) {
                        }

                        public void onStatusChanged(String provider, int status,
                                                    Bundle extras) {
                        }
                    });
            Location location = locationManager.getLastKnownLocation(provider);
            if (location != null) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();


                Geocoder geocoder;
                List<Address> addresses;
                geocoder = new Geocoder(((MainActivity)getActivity()), Locale.getDefault());

                try {
                    addresses = geocoder.getFromLocation(latitude, longitude, 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                    String address = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                    String city = addresses.get(0).getLocality();
                    String state = addresses.get(0).getAdminArea();
                    String country = addresses.get(0).getCountryName();
                    String postalCode = addresses.get(0).getPostalCode();
                    String knownName = addresses.get(0).getFeatureName();
                    String thoroughfare = addresses.get(0).getThoroughfare();
                    String subLocality = addresses.get(0).getSubLocality();

                    edtMemoryLocation.setText(address);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
                Toast.makeText(((MainActivity)getActivity()), "Unable to fetch your location", Toast.LENGTH_SHORT).show();
            }
        }

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}