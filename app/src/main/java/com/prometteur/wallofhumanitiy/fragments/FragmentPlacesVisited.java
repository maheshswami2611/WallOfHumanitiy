package com.prometteur.wallofhumanitiy.fragments;
import android.app.Dialog;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.prometteur.wallofhumanitiy.adapters.PlaceVisitedAdapter;
import com.prometteur.wallofhumanitiy.R;
import com.prometteur.wallofhumanitiy.Singleton.SingletonVisitedPlaces;

import java.util.ArrayList;
import java.util.List;

public class FragmentPlacesVisited extends Fragment {
    ImageView imgAddVisitedPlace;

    private List<SingletonVisitedPlaces> placesVisitedList = new ArrayList<>();
    private RecyclerView recyclerView;
    private PlaceVisitedAdapter mAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_places_visited, container, false);
        imgAddVisitedPlace=view.findViewById(R.id.imgAddVisitedPlace);
        imgAddVisitedPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog openDialog = new Dialog(getActivity());
                openDialog.setContentView(R.layout.custom_add_places_dialog);
                openDialog.setTitle("");

                ImageView dialogCloseButton = openDialog.findViewById(R.id.dialogCloseButton);
                dialogCloseButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        openDialog.dismiss();
                    }
                });
                openDialog.show();
            }
        });


        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        mAdapter = new PlaceVisitedAdapter(getActivity(), placesVisitedList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        preparePlacesVisitedData();

        return view;
    }

    private void preparePlacesVisitedData() {
        SingletonVisitedPlaces singletonVisitedPlaces = new SingletonVisitedPlaces("Mad Max: Fury Road", "Action & Adventure", "20/12/2019","11:15 AM");
        placesVisitedList.add(singletonVisitedPlaces);

        singletonVisitedPlaces =new SingletonVisitedPlaces("Mad Max: Fury Road", "Action & Adventure", "20/12/2019","11:15 AM");
        placesVisitedList.add(singletonVisitedPlaces);

        singletonVisitedPlaces =new SingletonVisitedPlaces("Mad Max: Fury Road", "Action & Adventure", "20/12/2019","11:15 AM");
        placesVisitedList.add(singletonVisitedPlaces);

        singletonVisitedPlaces = new SingletonVisitedPlaces("Mad Max: Fury Road", "Action & Adventure", "20/12/2019","11:15 AM");
        placesVisitedList.add(singletonVisitedPlaces);

        singletonVisitedPlaces = new SingletonVisitedPlaces("Mad Max: Fury Road", "Action & Adventure", "20/12/2019","11:15 AM");
        placesVisitedList.add(singletonVisitedPlaces);



        mAdapter.notifyDataSetChanged();
    }
}