package com.prometteur.wallofhumanitiy.fragments;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import com.prometteur.wallofhumanitiy.HorizontalImagesList;
import com.prometteur.wallofhumanitiy.Interface.APIInterface;
import com.prometteur.wallofhumanitiy.MediaFilePath;
import com.prometteur.wallofhumanitiy.activity.MainActivity;
import com.prometteur.wallofhumanitiy.adapters.MemoriesAdapter;
import com.prometteur.wallofhumanitiy.R;
import com.prometteur.wallofhumanitiy.helper.APIClient;
import com.prometteur.wallofhumanitiy.other.MemoryListResp;
import com.prometteur.wallofhumanitiy.other.StatusResultResponce;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import dmax.dialog.SpotsDialog;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.app.Activity.RESULT_OK;

public class FragmentMainMemories extends Fragment {
    ImageView imgBack;
    private RecyclerView recyclerView;
    private MemoriesAdapter adapter;
    private List<MemoryListResp.Result> singletonMemoriesList;
    MainActivity mainActivity;
    TextView txtSelectedFile;
    final int PERMISSION_REQUEST_CODE = 200;
    private static final int PICK_IMAGE_ID = 234; // the number doesn't matter
    SpotsDialog spotsDialog;
    APIInterface apiInterface;
    Context mContext;
    RecyclerView imageToUpload;
    double latitude;
    double longitude;

    private final int REQUEST_IMAGE_FROM_GALLERY = 200;
    private ProgressDialog progressDialog;
    private String encodedString;
    private String filename;
    private long timeBeforeUpload;
    private long timeAfterUpload;
    private long fileSize;
    File imgFile;
    String user_img = "";
    Bitmap myBitmap;

    List<Bitmap> bitmapas = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_memories, container, false);
        mainActivity = new MainActivity(getActivity().getApplicationContext());
        mContext = (MainActivity) getActivity();

        apiInterface = APIClient.getClient().create(APIInterface.class);
        spotsDialog = new SpotsDialog(getActivity());  imgBack = view.findViewById(R.id.imgBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).pushFragment(new FragmentBottomMemories());
            }
        });



        getMemoryList("110", "1", "914787215");

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        //prepareAlbums();
        return view;
    }


    private void selectImage() {


        startActivityForResult(getPickImageChooserIntent(), 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {

                String filePath = getImageFilePath(data);
                if (filePath != null) {
                    Bitmap selectedImage = BitmapFactory.decodeFile(filePath);
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize = 8;
                    selectedImage = BitmapFactory.decodeFile(filePath, options);
                    String fileNameSegments[] = filePath.split("/");
                    filename = fileNameSegments[fileNameSegments.length - 1];

                    user_img = getImageFilePath(data);
                    imgFile = new File(filePath);


                    if (imgFile.exists()) {
                        imgFile = new File(filePath.toString());
                        if (imgFile.exists()) {
                            // myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                            BitmapFactory.Options options1 = new BitmapFactory.Options();
                            options.inSampleSize = 8;
                            myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath(), options1);
                        }

                    }

                    bitmapas.add(selectedImage);
                    final String[] reviewImageType = {""};
                    try {

                        imageToUpload.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.HORIZONTAL));
                        HorizontalImagesList horizontalImagesList = new HorizontalImagesList(bitmapas, getActivity());
                        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                        imageToUpload.setLayoutManager(horizontalLayoutManager);
                        imageToUpload.setAdapter(horizontalImagesList);


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (data != null) {
                    Uri selectedUri = data.getData();
                    String[] columns = {MediaStore.Images.Media.DATA,
                            MediaStore.Images.Media.MIME_TYPE};

                    Cursor cursor = mContext.getContentResolver().query(selectedUri, columns, null, null, null);
                    cursor.moveToFirst();

                    int pathColumnIndex = cursor.getColumnIndex(columns[0]);
                    int mimeTypeColumnIndex = cursor.getColumnIndex(columns[1]);

                    String contentPath = cursor.getString(pathColumnIndex);
                    contentPath = MediaFilePath.getPath(mContext, selectedUri);
                    cursor.close();

                        if (contentPath != null) {
                            Bitmap selectedImage = BitmapFactory.decodeFile(contentPath);
                            BitmapFactory.Options options = new BitmapFactory.Options();
                            options.inSampleSize = 8;
                            selectedImage = BitmapFactory.decodeFile(contentPath, options);
                            String fileNameSegments[] = contentPath.split("/");
                            filename = fileNameSegments[fileNameSegments.length - 1];

                            user_img = getImageFilePath(data);
                            imgFile = new File(contentPath);


                            Bitmap bMap = ThumbnailUtils.createVideoThumbnail(contentPath, MediaStore.Video.Thumbnails.MICRO_KIND);

                            bitmapas.add(bMap);
                            final String[] reviewImageType = {""};
                            try {

                                imageToUpload.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.HORIZONTAL));
                                HorizontalImagesList horizontalImagesList = new HorizontalImagesList(bitmapas, getActivity());
                                LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                                imageToUpload.setLayoutManager(horizontalLayoutManager);
                                imageToUpload.setAdapter(horizontalImagesList);


                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        //It's an image
                    }
                }

            }
        }


    private String getImageFromFilePath(Intent data) {
        boolean isCamera = data == null || data.getData() == null;

        if (isCamera) return getCaptureImageOutputUri().getPath();
        else return getPathFromURI(data.getData());

    }

    public String getImageFilePath(Intent data) {
        return getImageFromFilePath(data);
    }


    private String getPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Audio.Media.DATA};
        Cursor cursor = getActivity().getContentResolver().query(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }


    public Intent getPickImageChooserIntent() {

        Uri outputFileUri = getCaptureImageOutputUri();

        List<Intent> allIntents = new ArrayList<>();
        PackageManager packageManager = getActivity().getPackageManager();

        Intent captureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
        for (ResolveInfo res : listCam) {
            Intent intent = new Intent(captureIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(res.activityInfo.packageName);
            if (outputFileUri != null) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            }
            allIntents.add(intent);
        }
        Intent galleryIntent = new Intent();

        if (Build.VERSION.SDK_INT < 19) {
            galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
            galleryIntent.setType("image/* video/*");
        } else {
            galleryIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            galleryIntent.addCategory(Intent.CATEGORY_OPENABLE);
            galleryIntent.setType("image/*");
            galleryIntent.putExtra(Intent.EXTRA_MIME_TYPES, new String[]{"image/*", "video/*"});
        }


        // Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // galleryIntent.setType("image/* video/*");
        List<ResolveInfo> listGallery = packageManager.queryIntentActivities(galleryIntent, 0);
        for (ResolveInfo res : listGallery) {
            Intent intent = new Intent(galleryIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(res.activityInfo.packageName);
            allIntents.add(intent);
        }

        Intent mainIntent = allIntents.get(allIntents.size() - 1);
        for (Intent intent : allIntents) {
            if (intent.getComponent().getClassName().equals(getActivity().getPackageName())) {
                mainIntent = intent;
                break;
            }
        }
        allIntents.remove(mainIntent);

        Intent chooserIntent = Intent.createChooser(mainIntent, "Select source");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, allIntents.toArray(new Parcelable[allIntents.size()]));

        return chooserIntent;
    }


    private Uri getCaptureImageOutputUri() {
        Uri outputFileUri = null;
        File getImage = getActivity().getExternalFilesDir("");
        if (getImage != null) {
            outputFileUri = Uri.fromFile(new File(getImage.getPath(), "profile.png"));
        }
        return outputFileUri;
    }


    private void addNewMemory(final Dialog dialog, String revimg_hotel_id1,
                               String memory_title1,
                               String memory_desc1,
                               String memory_location1
    ) {
        spotsDialog.show();



        RequestBody dumm = RequestBody.create(MediaType.parse("text/plain"), "110");
        RequestBody memory_title = RequestBody.create(MediaType.parse("text/plain"), memory_title1);
        RequestBody memory_desc = RequestBody.create(MediaType.parse("text/plain"), memory_desc1);
        RequestBody memory_location = RequestBody.create(MediaType.parse("text/plain"), memory_location1);
        RequestBody memory_lat = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(latitude));
        RequestBody memory_lng = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(longitude));
        RequestBody user_session = RequestBody.create(MediaType.parse("text/plain"), "914787215");
        RequestBody memType = RequestBody.create(MediaType.parse("text/plain"), "1");


        RequestBody user_img = null;
        RequestBody user_vid = null;
        MultipartBody.Part multipartBody1 = null;
        MultipartBody.Part multipartBody4 = null;
        MultipartBody.Part multipartBody2 = null;
        MultipartBody.Part multipartBody5 = null;
        MultipartBody.Part multipartBody3 = null;
        MultipartBody.Part multipartBody6 = null;




        if (bitmapas.size() > 0)
            for (int i = 0; i < bitmapas.size(); i++) {
                boolean isImage=false;
                if (String.valueOf(imgFile).contains("IMG")||String.valueOf(imgFile).contains(".png")||String.valueOf(imgFile).contains("jpeg")){
                    isImage=true;
                    user_img = RequestBody.create(MediaType.parse("image/*"), imgFile);
                }
                if (String.valueOf(imgFile).contains("VID")){
                    isImage=false;
                    MediaType MEDIA_TYPE = MediaType.parse("video/*");
                    user_vid = RequestBody.create(MEDIA_TYPE, imgFile);

                }




                if (i == 0) {
                    if(isImage){
                        multipartBody1 = MultipartBody.Part.createFormData("memory_file_one", filename, user_img);
                        multipartBody4 = MultipartBody.Part.createFormData("memory_thumb_one", filename, user_img);

                    }else {
                        multipartBody1 = MultipartBody.Part.createFormData("memory_file_one", filename, user_vid);
                        multipartBody4 = MultipartBody.Part.createFormData("memory_thumb_one", filename, user_vid);

                    }

                }
                if (i == 1) {
                    if(isImage) {

                        multipartBody2 = MultipartBody.Part.createFormData("memory_file_two", filename, user_img);
                        multipartBody5 = MultipartBody.Part.createFormData("memory_thumb_two", filename, user_img);
                    }else {
                        multipartBody2 = MultipartBody.Part.createFormData("memory_file_two", filename, user_vid);
                        multipartBody5 = MultipartBody.Part.createFormData("memory_thumb_two", filename, user_vid);

                    }
                }
                if (i == 2) {
                    if(isImage) {
                        multipartBody3 = MultipartBody.Part.createFormData("memory_file_three", filename, user_img);
                        multipartBody6 = MultipartBody.Part.createFormData("memory_thumb_three", filename, user_img);
                    }else {
                        multipartBody3 = MultipartBody.Part.createFormData("memory_file_three", filename, user_vid);
                        multipartBody6 = MultipartBody.Part.createFormData("memory_thumb_three", filename, user_vid);

                    }
                }

            }


        Call<StatusResultResponce> call = apiInterface.addNewMemory(
                dumm,
                memory_title,
                memory_desc,
                memory_location,

                multipartBody1,
                dumm,

                multipartBody2,
                dumm,

                multipartBody3,
                dumm,


                multipartBody4,
                multipartBody5,
                multipartBody6,

                memory_lat,
                memory_lng,
                memType,
                user_session);
        call.enqueue(new Callback<StatusResultResponce>() {
            @Override
            public void onResponse(Call<StatusResultResponce> call, Response<StatusResultResponce> response) {

                if (null != spotsDialog && spotsDialog.isShowing()) {
                    spotsDialog.dismiss();

                }

                StatusResultResponce resource = response.body();
                if (resource.getStatus().toString().equals("1")) {
                    dialog.dismiss();
                    Toast.makeText(mContext, "Memory Added Successfully", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(mContext, "Memory Upload Fail", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call<StatusResultResponce> call, Throwable t) {
                call.cancel();
                if (null != spotsDialog && spotsDialog.isShowing()) {
                    spotsDialog.dismiss();
                    Toast.makeText(mContext, "Something went wrong...", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission((MainActivity) getActivity(), ACCESS_FINE_LOCATION);
        /* int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA);
         */
        return result == PackageManager.PERMISSION_GRANTED /*&& result1 == PackageManager.PERMISSION_GRANTED*/;
    }

    private void requestPermission() {

        ActivityCompat.requestPermissions((MainActivity) getActivity(), new String[]{ACCESS_FINE_LOCATION/*, CAMERA*/}, PERMISSION_REQUEST_CODE);

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0) {

                    boolean locationAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    // boolean cameraAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                    if (locationAccepted /*&& cameraAccepted*/)
                        Toast.makeText((MainActivity) getActivity(), "Permission Granted.", Toast.LENGTH_SHORT).show();
                    else {
                        Toast.makeText((MainActivity) getActivity(), "Permission Denied.", Toast.LENGTH_SHORT).show();

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
        new AlertDialog.Builder((MainActivity) getActivity())
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }


    /*public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            Uri selectedMediaUri = data.getData();
            String uriString = selectedMediaUri.toString();
            if (uriString.contains("image")) {
                txtSelectedFile.setText(txtSelectedFile + ", " + selectedMediaUri.toString());
            } else if (uriString.contains("video")) {
                txtSelectedFile.setText(txtSelectedFile + ", " + selectedMediaUri.toString());
            }
        }
    }*/


    public void onClickCalled() {
        Toast.makeText(((MainActivity) getActivity()).getApplicationContext(), "Yes Clicked", Toast.LENGTH_SHORT).show();
    }

  /*  private void prepareAlbums() {
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
    }*/

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

            String location_context = Context.LOCATION_SERVICE;
            LocationManager locationManager = (LocationManager) ((MainActivity) getActivity()).getSystemService(location_context);
            List<String> providers = locationManager.getProviders(true);
            for (String provider : providers) {
                if (ActivityCompat.checkSelfPermission(((MainActivity) getActivity()), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(((MainActivity) getActivity()), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();


                    Geocoder geocoder;
                    List<Address> addresses;
                    geocoder = new Geocoder(((MainActivity) getActivity()), Locale.getDefault());

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

                        edtMemoryLocation.setText(subLocality + ", " + city + ", " + postalCode);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


    private void getMemoryList(final String memory_userid, final String memory_type, final String user_session) {
        spotsDialog.show();
        Call<MemoryListResp> call = apiInterface.getMemoryList(memory_userid, memory_type, user_session);
        call.enqueue(new Callback<MemoryListResp>() {
            @Override
            public void onResponse(Call<MemoryListResp> call, Response<MemoryListResp> response) {

                try {

                    MemoryListResp resource = response.body();


                    if (resource.getStatus().toString().equals("1")) {
                        singletonMemoriesList = resource.getResult();

                        if(singletonMemoriesList.size()==0){
                            Toast.makeText(mContext, "No Data Found", Toast.LENGTH_SHORT).show();
                        }


                        MemoryListResp.Result res=new MemoryListResp.Result(mContext);
                        singletonMemoriesList.add(res);
                        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
                        recyclerView.setLayoutManager(mLayoutManager);
                        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                        adapter = new MemoriesAdapter(getActivity(), singletonMemoriesList, new MemoriesAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(MemoryListResp.Result item) {

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
                                imageToUpload = openDialog.findViewById(R.id.imgToUpload);
                                final View viewPhotoAndVideoTab = openDialog.findViewById(R.id.viewPhotoAndVideoTab);
                                final TextView tabText = openDialog.findViewById(R.id.txtTextTab);
                                txtSelectedFile = openDialog.findViewById(R.id.txtSelectedFile);
                                final TextView tabPhotoAndVideo = openDialog.findViewById(R.id.txtPhotoAndVideoTab);
                                tabText.setTextColor(mContext.getResources().getColor(R.color.colorDarkGreen));
                                viewTextTab.setBackgroundColor(mContext.getResources().getColor(R.color.colorDarkGreen));
                                btnAddPhotoVideo.setVisibility(View.GONE);


                                btnSaveMemory.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (!edtMemoryLocation.getText().toString().equalsIgnoreCase("")) {
                                            if (edtMemoryLocation.getText().toString().trim().length() > 0) {

                                                if (!edtMemoryTitle.getText().toString().trim().equalsIgnoreCase("")) {

                                                    if (!edtMemoryDescription.getText().toString().trim().equalsIgnoreCase("")) {
                                                        Toast.makeText(mContext, "Save", Toast.LENGTH_SHORT).show();

                                                    } else {
                                                        edtMemoryTitle.setError("Enter Memory Description");
                                                    }
                                                } else {
                                                    edtMemoryTitle.setError("Enter Memory Title");
                                                }
                                            } else {
                                                Toast.makeText(mContext, "Please wait while fetching location", Toast.LENGTH_SHORT).show();

                                            }
                                        } else {
                                            Toast.makeText(mContext, "Please wait while fetching location", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                                btnShareMemory.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (!edtMemoryLocation.getText().toString().equalsIgnoreCase("")) {
                                            if (edtMemoryLocation.getText().toString().trim().length() > 0) {

                                                if (!edtMemoryTitle.getText().toString().trim().equalsIgnoreCase("")) {

                                                    if (!edtMemoryDescription.getText().toString().trim().equalsIgnoreCase("")) {
                                                        spotsDialog.show();
                                                        addNewMemory(openDialog, "8", edtMemoryTitle.getText().toString(), edtMemoryDescription.getText().toString(),edtMemoryLocation.getText().toString());
                                                    } else {
                                                        edtMemoryTitle.setError("Enter Memory Description");
                                                    }
                                                } else {
                                                    edtMemoryTitle.setError("Enter Memory Title");
                                                }
                                            } else {
                                                Toast.makeText(mContext, "Please wait while fetching location", Toast.LENGTH_SHORT).show();

                                            }
                                        } else {
                                            Toast.makeText(mContext, "Please wait while fetching location", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });


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
                                        selectImage();
                                    }
                                });
                                openDialog.show();


                            }
                        });
                        recyclerView.setAdapter(adapter);

                    } else if (resource.getStatus().toString().equals("2")) {
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
            public void onFailure(Call<MemoryListResp> call, Throwable t) {
                call.cancel();
            }
        });
    }

}