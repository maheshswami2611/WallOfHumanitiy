package com.prometteur.wallofhumanitiy.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.prometteur.wallofhumanitiy.R;

public class FragmentFriendReqDetails extends Fragment {
    ImageView imgProfile;
    TextView tabAbout, tabPhotos, tabVideos, tabPlacesVisited, tabFriends, tabFollowers;
    private static final String urlProfileImg = "https://lh3.googleusercontent.com/eCtE_G34M9ygdkmOpYvCag1vBARCmZwnVS6rS5t4JLzJ6QgQSBquM0nuTsCpLhYbKljoyS-txg";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friend_request_detail, container, false);
        imgProfile = view.findViewById(R.id.imgProfile);
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
}