package com.prometteur.wallofhumanitiy.fragments;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prometteur.wallofhumanitiy.adapters.NotificationAdapter;
import com.prometteur.wallofhumanitiy.R;
import com.prometteur.wallofhumanitiy.Singleton.SingletonNotification;

import java.util.ArrayList;
import java.util.List;

public class FragmentBottomNotification extends Fragment {
    private List<SingletonNotification> notificationList = new ArrayList<>();
    private RecyclerView recyclerView;
    private NotificationAdapter mAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_notification, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        mAdapter = new NotificationAdapter(getActivity(),notificationList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareNotificationData();
        return view;
    }

    private void prepareNotificationData() {
        int[] covers = new int[]{
                R.drawable.album1,
                R.drawable.album3,
                R.drawable.album5};

        SingletonNotification notification = new SingletonNotification("Invetation De H B P L",  "29 Minutes ago",covers[0]);
        notificationList.add(notification);

        notification = new SingletonNotification("Inside Out Animation, Kids & Family", "1 Minutes ago",covers[2]);
        notificationList.add(notification);

        notification = new SingletonNotification("Star Wars: Episode VII - The Force Awakens Action", "1 Hour ago",covers[1]);
        notificationList.add(notification);

        mAdapter.notifyDataSetChanged();
    }
}