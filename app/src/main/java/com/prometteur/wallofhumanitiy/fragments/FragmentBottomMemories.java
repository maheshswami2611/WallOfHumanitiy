package com.prometteur.wallofhumanitiy.fragments;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.prometteur.wallofhumanitiy.Utility.Constant;
import com.prometteur.wallofhumanitiy.activity.MainActivity;
import com.prometteur.wallofhumanitiy.R;
import com.prometteur.wallofhumanitiy.activity.ActivityDesires;
import com.prometteur.wallofhumanitiy.activity.ActivityMoods;
import com.prometteur.wallofhumanitiy.activity.ActivityOpinions;

public class FragmentBottomMemories extends Fragment {

    CardView cvMemories, cvOpinions, cvMoods, cvDesires;

    private String[] activityTitles;
    private boolean shouldLoadHomeFragOnBackPress = true;
    private Handler mHandler;
    ImageView imgNavIcon;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_memories, container, false);
        cvMemories = (CardView) view.findViewById(R.id.cvMemories);
        cvOpinions = (CardView) view.findViewById(R.id.cvOpinions);
        cvMoods = (CardView)view. findViewById(R.id.cvMoods);
        cvDesires = (CardView) view.findViewById(R.id.cvDesires);
        imgNavIcon = view.findViewById(R.id.imgNavIcon);

        imgNavIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constant.pressedFromFragment=true;
                ((MainActivity) getActivity()).loadNavHeader();
            }
        });
        cvMemories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* Intent intent = new Intent(getActivity(), ActivityMemories.class);
                startActivity(intent);
*/
                ((MainActivity)getActivity()).pushFragment(new FragmentMainMemories());
            }
        });

        cvOpinions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityOpinions.class);
                startActivity(intent);
            }
        });

        cvMoods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityMoods.class);
                startActivity(intent);
            }
        });

        cvDesires.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityDesires.class);
                startActivity(intent);
            }
        });
        return view;
    }
}